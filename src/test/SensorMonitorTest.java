package fusion;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SensorMonitorTest {
    @Test
    void testSensorHealthCheck() {
        SensorData valid = new SensorData(25, 70);
        SensorData invalid = new SensorData(-10, 200);
        assertTrue(SensorMonitor.isSensorHealthy(valid));
        assertFalse(SensorMonitor.isSensorHealthy(invalid));
    }

    @Test
    void testTimeoutSimulation() {
        boolean seenTrue = false;
        for (int i = 0; i < 100; i++) {
            if (SensorMonitor.simulateTimeout(0.5)) {
                seenTrue = true;
                break;
            }
        }
        assertTrue(seenTrue, "Timeout should eventually simulate true");
    }
}
