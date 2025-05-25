package test;

import controller.BrakeController;
import diagnostics.BrakeLogger;
import hardware.SimulatedBrakeActuator;
import sensors.CameraSystem;
import sensors.LidarSensor;
import sensors.RadarSensor;
import sensors.SimulatedSensorProvider;
import sensors.WheelSpeedSensor;

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
}
