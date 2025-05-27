package sensors.wheel;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WheelSpeedSensorTest {
    //TC_18
    @Test
    void testRPMWithinExpectedRange() {
        WheelSensorPair wheelSensor = new WheelSensorPair("W1");
        double rpm = wheelSensor.getRPM();
        assertTrue(rpm >= 0 && rpm <= 1000, "RPM should be between 0 and 1000");
    }

    @Test
    void testRPMChangesOnCall() {
        WheelSensorPair wheelSensor = new WheelSensorPair("W2");
        double rpm1 = wheelSensor.getRPM();
        double rpm2 = wheelSensor.getRPM();
        assertNotEquals(rpm1, rpm2, "RPM values should vary on different calls");
    }

    @Test
    void testWheelSensorPairInitialization() {
        WheelSensorPair wheelSensor = new WheelSensorPair("W3");
        assertNotNull(wheelSensor, "WheelSensorPair should be initialized properly");
        assertEquals("W3", wheelSensor.getId(), "WheelSensorPair ID should match the initialized value");
    }
    //TC_19
    @Test
    void testRPMHandlesNegativeValues() {
        WheelSensorPair wheelSensor = new WheelSensorPair("W4");
        wheelSensor.setRPM(-100); // Simulate negative RPM
        double rpm = wheelSensor.getRPM();
        assertTrue(rpm >= 0, "RPM should not be negative, should return 0 instead");
    }
}