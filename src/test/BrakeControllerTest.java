package test;

import controller.BrakeController;
import diagnostics.BrakeLogger;
import hardware.SimulatedBrakeActuator;
import sensors.SimulatedSensorProvider;

/**
 * Simple test class to verify that the brake control process is working as expected.
 */
public class BrakeControllerTest {

    public static void main(String[] args) {
        // Initializing Dependency Modules
        SimulatedSensorProvider sensorProvider = new SimulatedSensorProvider();
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
