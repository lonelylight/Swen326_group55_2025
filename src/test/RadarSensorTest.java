package sensors.radar;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RadarSensorTest {

    @Test
    void testRadarSensorReturnsValidData() {
        RadarSensor radar = new RadarSensor("R1");
        SensorData data = radar.getData();
        assertNotNull(data);
        assertTrue(data.getDistanceMeters() >= 0 && data.getDistanceMeters() <= 100);
        assertTrue(data.getSpeedKmH() >= -50 && data.getSpeedKmH() <= 50);
    }

    @Test
    void testRadarSensorHandlesNullData() {
        RadarSensor radar = new RadarSensor("R2");
        radar.setData(null);
        assertNull(radar.getData(), "Radar should return null when data is not set");
    }

    @Test
    void testRadarSensorWithInvalidData() {
        RadarSensor radar = new RadarSensor("R3");
        radar.setData(new SensorData(-10, 120));
        SensorData data = radar.getData();
        assertNotNull(data);
        assertTrue(data.getDistanceMeters() < 0 || data.getDistanceMeters() > 100);
        assertTrue(data.getSpeedKmH() < -50 || data.getSpeedKmH() > 50);
    }

    @Test
    void testRadarSensorInitialization() {
        RadarSensor radar = new RadarSensor("R4");
        assertNotNull(radar, "Radar sensor should be initialized properly");
        assertEquals("R4", radar.getId(), "Radar sensor ID should match the initialized value");
    }

    @Test
    void testRadarSensorIdValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new RadarSensor(""); // Empty ID
        }, "Radar sensor ID should not be empty");

        assertThrows(IllegalArgumentException.class, () -> {
            new RadarSensor(null); // Null ID
        }, "Radar sensor ID should not be null");
    }
}
