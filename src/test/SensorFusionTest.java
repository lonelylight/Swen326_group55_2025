package fusion;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SensorFusionTest {
    @Test
    void testFusionAveragesCorrectly() {
        SensorData radar = new SensorData(10, 50);
        SensorData lidar = new SensorData(20, 30);
        SensorData result = SensorFusion.fuse(radar, lidar);
        assertEquals(15.0, result.distance, 0.01);
        assertEquals(40.0, result.speed, 0.01);
    }
    
    @Test
    void testFusionHandlesNullData() {
        SensorData radar = new SensorData(10, 50);
        SensorData lidar = null;
        SensorData result = SensorFusion.fuse(radar, lidar);
        assertEquals(radar.distance, result.distance);
        assertEquals(radar.speed, result.speed);
        
        lidar = new SensorData(20, 30);
        result = SensorFusion.fuse(null, lidar);
        assertEquals(lidar.distance, result.distance);
        assertEquals(lidar.speed, result.speed);
    }

    @Test
    void testFusionHandlesInvalidData() {
        SensorData radar = new SensorData(-5, 200); // Invalid distance and speed
        SensorData lidar = new SensorData(20, 30);
        SensorData result = SensorFusion.fuse(radar, lidar);
        assertTrue(result.distance < 0 || result.distance > 100); // Assuming valid range is 0-100
        assertTrue(result.speed < -50 || result.speed > 50); // Assuming valid range is -50 to 50
    }
}