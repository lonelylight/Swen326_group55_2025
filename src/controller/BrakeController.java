package controller;

import hardware.BrakeActuator;
import model.VehicleState;
import sensors.SensorInputProvider;
import diagnostics.BrakeLogger;
import Interface_code.Display;
import Interface_code.EmergencyIndicators;
/**
 * Core controller: responsible for decision making on brake timing, execution of brake commands and feedback validation.
 */
public class BrakeController {

    private static final int MAX_RETRY_ATTEMPTS = 2;
    private static final double DECELERATION_TOLERANCE = 0.05; // ±5%

    private final SensorInputProvider sensorInput;
    private final BrakeActuator brakeActuator;
    private final BrakeLogger logger;

    public BrakeController(SensorInputProvider sensorInput,
                           BrakeActuator brakeActuator,
                           BrakeLogger logger) {
        this.sensorInput = sensorInput;
        this.brakeActuator = brakeActuator;
        this.logger = logger;
    }

    /**
     * Initiate the emergency braking process.
     * @return True or False
     */
    public boolean engageEmergencyBraking() {
        logger.logInfo(">>> Initiating emergency braking...");

        Display display = new Display();
        EmergencyIndicators emergencyIndicators = new EmergencyIndicators(display);
        emergencyIndicators.triggerEmergencyBraking(1.0);

        int attempt = 0;
        boolean success = false;

        while (attempt <= MAX_RETRY_ATTEMPTS && !success) {
            attempt++;
            logger.logInfo("Attempt #" + attempt + ": Applying brakes...");

            // This place normally does not need to operate, just wait until the result of the brake control, because there is no, so here is the equivalent of simulation
            brakeActuator.applyBrakes(1.0); 

            try {
                Thread.sleep(50); // Waiting for brake feedback (control cycle is 50ms, judged every 50 milliseconds)
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            VehicleState state = sensorInput.getCurrentVehicleState();

            // Determine whether braking is successful according to the rules
            success = validateDeceleration(state);

            if (success) {
                logger.logInfo("Braking verified. Emergency braking succeeded.");
            } else {
                logger.logWarning("Braking verification failed.");
            }
        }

        if (!success) {
            logger.logError("Emergency braking failed after retries. Alerting driver.");
            alertDriver();
        }

        return success;
    }

    /**
     * Verify that the current state meets the desired deceleration rate (±5% tolerance)
     */
    private boolean validateDeceleration(VehicleState state) {
        double expected = state.getExpectedDeceleration();
        double actual = Math.abs(state.getCurrentAcceleration());

        logger.logInfo(String.format("Expected deceleration: %.2f m/s², Actual: %.2f m/s²",
                expected, actual));

        return Math.abs(expected - actual) <= expected * DECELERATION_TOLERANCE;
    }

    /**
     * Simulates an alert to the driver.
     */
    private void alertDriver() {
        // TODO Calling the interface to warn the driver
        //System.out.println("[ALERT] Emergency braking failed. Manual intervention required!");
        //Changed by Amit
        Display display = new Display();
        display.showVisualAlert("[ALERT] Emergency braking failed. Manual intervention required!");
    }
}
