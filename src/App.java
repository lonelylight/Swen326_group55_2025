
import controller.BrakeController;
import diagnostics.BrakeLogger;
import hardware.BrakeActuator;
import hardware.SimulatedBrakeActuator;
import sensors.SensorInputProvider;
import sensors.SimulatedSensorProvider;

public class App {


    /**
     * System entry: integrated brake control module to run an emergency braking process.
     */
    public static void main(String[] args) {
        System.out.println("=== AEBS Brake Control Simulation ===");

        // Initializing Dependency Modules
        SensorInputProvider sensorProvider = new SimulatedSensorProvider();
        BrakeActuator brakeActuator = new SimulatedBrakeActuator();
        BrakeLogger logger = new BrakeLogger();

        // Building the Controller
        BrakeController controller = new BrakeController(sensorProvider, brakeActuator, logger);

        // Simulated triggering of emergency braking
        boolean success = controller.engageEmergencyBraking();

        if (success) {
            System.out.println("[SYSTEM] Emergency braking completed successfully.");
        } else {
            System.out.println("[SYSTEM] Emergency braking failed. Manual intervention required.");
        }

        System.out.println("=== Simulation Complete ===");
    }
}

