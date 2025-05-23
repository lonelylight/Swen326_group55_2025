package hardware;

import java.util.Random;

/**
 * Simulate the brake actuator to simulate brake actuation with possible failure scenarios.
 */
public class SimulatedBrakeActuator implements BrakeActuator {

    private boolean engaged;
    private final Random random;

    public SimulatedBrakeActuator() {
        this.random = new Random();
        this.engaged = false;
    }

    @Override
    public void applyBrakes(double intensity) {
        try {
            Thread.sleep(10); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulated brake success or failure (5% probability of failure)
        if (intensity > 0.0) {
            engaged = random.nextDouble() >= 0.05;
        } else {
            engaged = false;
        }

        System.out.printf("[Actuator] Brake command: %.2f -> %s%n",
                intensity, engaged ? "ENGAGED" : "FAILED");
    }

    @Override
    public boolean isBrakeEngaged() {
        return engaged;
    }
}
