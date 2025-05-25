package sensors.radar;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadarSensorTest {
    @Test
    void testRadarSensorReturnsValidData() {
        RadarSensor radar = new RadarSensor("R1");
        SensorData data = radar.getData();
        assertTrue(data.distance >= 0 && data.distance <= 100);
        assertTrue(data.speed >= -50 && data.speed <= 50);
    }
    

    @Test
    void testRadarSensorHandlesNoData() {
        RadarSensor radar = new RadarSensor("R1");
        radar.setData(null); // Simulate no data
        SensorData data = radar.getData();
        assertNull(data, "Radar sensor should return null when no data is available");
    }


    @Test
    void testRadarSensorHandlesInvalidData() {
        RadarSensor radar = new RadarSensor("R1");
        radar.setData(new SensorData(-10, 200)); // Simulate invalid data
        SensorData data = radar.getData();
        assertNotNull(data, "Radar sensor should return data even if it's invalid");
        assertTrue(data.distance < 0 || data.distance > 100, "Distance should be out of valid range");
        assertTrue(data.speed < -50 || data.speed > 50, "Speed should be out of valid range");
    }
}