import sensors.*;
import fusion.*;
import controller.*;

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

        // --- Low-level simulation using raw sensor input ---
        RadarSensor radar = new RadarSensor("R1");
        LidarSensor lidar = new LidarSensor("L1");
        CameraSystem camera = new CameraSystem("C1");

        SensorData radarData = radar.getData();
        SensorData lidarData = lidar.getData();

        if (SensorMonitor.simulateTimeout(0.1)) {
            System.out.println("[WARNING] Radar sensor timeout!");
            radarData = new SensorData(-1, -999);
        }

        SensorData fused = SensorFusion.fuse(radarData, lidarData);
        System.out.printf("Fused Distance: %.2f m, Speed: %.2f km/h\n", fused.distance, fused.speed);

        String objectSeen = camera.classifyObject();
        System.out.println("Camera sees: " + objectSeen);

        AEBController aebController = new AEBController();
        aebController.evaluateAndBrake(fused, objectSeen);

        System.out.println("=== AEBS BrakeController Simulation ===");

        // --- High-level simulation using abstracted components ---
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

