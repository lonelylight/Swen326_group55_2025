package sensors;

import model.VehicleState;

import java.util.Random;

public class SimulatedSensorProvider implements SensorInputProvider {

    private final Random random = new Random();

    @Override
    public VehicleState getCurrentVehicleState() {
        VehicleState state = new VehicleState();
        
        // Simulate the vehicle speed
        state.setVehicleSpeedKmh(60 + random.nextGaussian() * 2);

        // Simulate acceleration
        state.setCurrentAcceleration(-2.5 + random.nextGaussian() * 0.2); // Simulate the desired deceleration.

        // Simulate the expected deceleration
        state.setExpectedDeceleration(2.5); // fixed or configurable by the controller

        // Simulate brake intensity
        state.setBrakeIntensity(1.0); // Emergency braking

        // Simulate wheel speed data
        state.setWheelSpeed("FL", 800.0 + random.nextGaussian() * 5); // Front Left
        state.setWheelSpeed("FR", 805.0 + random.nextGaussian() * 5);
        state.setWheelSpeed("RL", 790.0 + random.nextGaussian() * 5);
        state.setWheelSpeed("RR", 795.0 + random.nextGaussian() * 5);

        // Emulate the state flag
        state.setBrakeEngaged(true); // Assume previously executed
        state.setSensorFaultDetected(random.nextDouble() < 0.05); // 5% probability of sensor exception

        state.updateTimestamp();
        return state;
    }

    @Override
    public boolean allSensorsOperational() {
        // Simulation: 95% chance of normal
        return random.nextDouble() >= 0.05;
    }

    @Override
    public boolean isSensorDataConsistent(String sensorName) {
        // Simple simulation: 98% consistent
        return random.nextDouble() < 0.98;
    }
}
