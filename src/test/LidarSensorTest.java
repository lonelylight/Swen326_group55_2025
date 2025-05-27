package sensors.lidar;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LidarSensorTest {

    @Test
    void testLidarSensorReturnsValidData() {
        LidarSensor lidar = new LidarSensor("L1");
        SensorData data = lidar.getData();
        assertNotNull(data, "Sensor data should not be null");
        assertTrue(data.getDistanceMeters() >= 0 && data.getDistanceMeters() <= 80, "Distance out of bounds");
        assertTrue(data.getSpeedKmH() >= -30 && data.getSpeedKmH() <= 30, "Speed out of bounds");
    }

    @Test
    void testLidarSensorHandlesNullData() {
        LidarSensor lidar = new LidarSensor("L2");
        lidar.setData(null);
        assertNull(lidar.getData(), "Lidar should return null when internal data is null");
    }

    @Test
    void testLidarSensorWithInvalidData() {
        LidarSensor lidar = new LidarSensor("L3");
        lidar.setData(new SensorData(-10, 100)); // Invalid
        SensorData data = lidar.getData();
        assertNotNull(data);
        assertTrue(data.getDistanceMeters() < 0 || data.getDistanceMeters() > 80);
        assertTrue(data.getSpeedKmH() < -30 || data.getSpeedKmH() > 30);
    }
    
    @Test
    void testLidarSensorInitialization() {
        LidarSensor lidar = new LidarSensor("L4");
        assertNotNull(lidar, "Lidar sensor should be initialized properly");
        assertEquals("L4", lidar.getId(), "Lidar sensor ID should match the initialized value");
    }

    //TC_10
    @Test
    void testLidarSensorIdValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new LidarSensor(""); // Empty ID
        }, "Lidar sensor ID should not be empty");

        assertThrows(IllegalArgumentException.class, () -> {
            new LidarSensor(null); // Null ID
        }, "Lidar sensor ID should not be null");
    }
}
