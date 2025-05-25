package sensors.lidar;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LidarSensorTest {
    @Test
    void testLidarSensorReturnsValidData() {
        LidarSensor lidar = new LidarSensor("L1");
        SensorData data = lidar.getData();
        assertTrue(data.distance >= 0 && data.distance <= 80);
        assertTrue(data.speed >= -30 && data.speed <= 30);
    }

    @Test
    void testLidarSensorHandlesNoData() {
        LidarSensor lidar = new LidarSensor("L1");
        lidar.setData(null); // Simulate no data
        SensorData data = lidar.getData();
        assertNull(data, "Lidar sensor should return null when no data is available");
    }

    @Test
    void testLidarSensorHandlesInvalidData() {
        LidarSensor lidar = new LidarSensor("L1");
        lidar.setData(new SensorData(-5, 100)); // Simulate invalid data
        SensorData data = lidar.getData();
        assertNotNull(data, "Lidar sensor should return data even if it's invalid");
        assertTrue(data.distance < 0 || data.distance > 80, "Distance should be out of valid range");
        assertTrue(data.speed < -30 || data.speed > 30, "Speed should be out of valid range");
    }
}