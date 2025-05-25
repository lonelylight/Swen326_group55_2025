package controller;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;

public class AEBControllerTest {
    @Test
    void testEvaluateAndBrake() {
        AEBController controller = new AEBController();
        controller.evaluateAndBrake(new SensorData(5, 20), "vehicle"); // Should brake
        controller.evaluateAndBrake(new SensorData(50, 20), "none");   // Should not brake
        controller.evaluateAndBrake(new SensorData(50, 20), "pedestrian"); // Should brake
    }
}
