package test;

import controller.BrakeController;
import diagnostics.BrakeLogger;
import hardware.SimulatedBrakeActuator;
import sensors.CameraSystem;
import sensors.LidarSensor;
import sensors.RadarSensor;
import sensors.SimulatedSensorProvider;
import sensors.WheelSpeedSensor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple test class to verify that the brake control process is working as expected.
 */
public class BrakeControllerTest {

    public static void main(String[] args) {
        // Initializing Dependency Modules
        RadarSensor radar = new RadarSensor("R1");
        LidarSensor lidar = new LidarSensor("L1");
        CameraSystem camera = new CameraSystem("C1");
        WheelSpeedSensor frontWheelSpeed = new WheelSpeedSensor("FW1");
        WheelSpeedSensor rearWheelSpeed = new WheelSpeedSensor("RW1");
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider(radar,lidar,camera,frontWheelSpeed,rearWheelSpeed);
        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();

        // Building the Controller
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // execute a test
        boolean result = controller.engageEmergencyBraking();

        if (result) {
            System.out.println("[TEST] Emergency braking succeeded.");
        } else {
            System.out.println("[TEST] Emergency braking failed after retries.");
        }
    }

    @Test
    void TC_02_actuatorFailsTwice_thenSucceeds() {
        // Arrange
        RadarSensor radar = new RadarSensor("R1");
        LidarSensor lidar = new LidarSensor("L1");
        CameraSystem camera = new CameraSystem("C1");
        WheelSpeedSensor frontWheelSpeed = new WheelSpeedSensor("FW1");
        WheelSpeedSensor rearWheelSpeed = new WheelSpeedSensor("RW1");

        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider(radar, lidar, camera, frontWheelSpeed, rearWheelSpeed);
        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        brakeActuator.setFailTimes(2); // Fail first 2 attempts

        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertTrue(result);
        assertEquals(3, brakeActuator.getAttemptCount());
        assertFalse(logger.containsError());
    }

    @Test
    void TC_03_actuatorFailsThreeTimes_triggersAlert() {
        // Arrange
        RadarSensor radar = new RadarSensor("R1");
        LidarSensor lidar = new LidarSensor("L1");
        CameraSystem camera = new CameraSystem("C1");
        WheelSpeedSensor frontWheelSpeed = new WheelSpeedSensor("FW1");
        WheelSpeedSensor rearWheelSpeed = new WheelSpeedSensor("RW1");

        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider(radar, lidar, camera, frontWheelSpeed, rearWheelSpeed);
        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        brakeActuator.setFailTimes(3); // Fail all 3 attempts

        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertFalse(result, "Braking should fail after 3 failed attempts");
        assertEquals(3, brakeActuator.getAttemptCount(), "Should try exactly 3 times");
        assertTrue(logger.containsError(), "An error should be logged when braking fails");
        assertTrue(logger.containsAlert(), "Alert to driver should be triggered on failure");
    }

    @Test
    void TC_04_sensorSpike_shouldNotTriggerBrake() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider(true); // true = inject noise or spike
        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();

        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertFalse(result, "Brake should not engage due to invalid spike input");
        assertEquals(0, brakeActuator.getAttemptCount(), "Should not send any brake command");
        assertTrue(logger.containsWarning(), "Spike should be detected and logged as warning");
    }

    @Test
    void TC_05_accelerationWithinTolerance_brakeShouldSucceed() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
        sensorProvider.setControlledDeceleration(0.95); // Assume expected = 1.0, this is within -5%

        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertTrue(result, "Should accept deceleration within ±5% tolerance");
        assertEquals(1, brakeActuator.getAttemptCount());
        assertFalse(logger.containsError(), "No error should occur within tolerance range");
    }

    @Test
    void TC_06_sensorReportsZeroAcceleration_shouldLogWarning() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
        sensorProvider.setControlledDeceleration(0.0); // Simulate faulty sensor reporting no deceleration

        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertFalse(result, "Braking should fail due to invalid zero deceleration");
        assertTrue(logger.containsWarning(), "Unexpected zero acceleration should trigger a warning log");
    }

    @Test
    void TC_07_actuatorDelayExceeds100ms_shouldLogPerformanceWarning() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        brakeActuator.setResponseDelay(120); // milliseconds

        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        long startTime = System.currentTimeMillis();
        boolean result = controller.engageEmergencyBraking();
        long duration = System.currentTimeMillis() - startTime;

        // Assert
        assertTrue(result, "Braking should still succeed, but be slow");
        assertTrue(duration >= 100, "Execution time should exceed 100ms due to artificial delay");
        assertTrue(logger.containsWarning(), "Delay beyond 100ms should be logged");
    }

    @Test
    void TC_08_sensorReportsNotOperational_shouldAbortBraking() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
        sensorProvider.setOperationalStatus(false); // Simulate sensor fault

        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertFalse(result, "Braking should not proceed if sensors are not operational");
        assertEquals(0, brakeActuator.getAttemptCount(), "No brake command should be sent");
        assertTrue(logger.containsError(), "Sensor fault should be logged as error");
    }

    @Test
    void TC_09_accelerationOutsideTolerance_shouldTriggerRetry() {
        // Arrange
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
        sensorProvider.setControlledDeceleration(0.88); // Expected = 1.0, outside 5% tolerance

        SimulatedBrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Act
        boolean result = controller.engageEmergencyBraking();

        // Assert
        assertFalse(result, "Braking should fail after retries if tolerance not met");
        assertEquals(3, brakeActuator.getAttemptCount(), "Should retry 3 times before giving up");
        assertTrue(logger.containsAlert(), "Driver should be alerted after final failure");
    }

    @Test
    void TC_10_uiTriggerShouldCorrectlyEngageBraking() {
        // Arrange
        SimulatedUI ui = new SimulatedUI(); // You may need to define this mock class
        BrakeController controller = ui.getBoundController(); // Assume UI holds or creates controller instance

        // Act
        boolean result = ui.simulateUserEmergencyButtonPress();

        // Assert
        assertTrue(result, "Braking should be triggered correctly through UI interface");
        assertTrue(controller.wasBrakingEngaged(), "Controller should have logged that braking was triggered");
    }


}
