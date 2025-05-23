package model;

import java.time.Instant;
import java.util.Map;
import java.util.HashMap;

public class VehicleState {

    // --- Dynamic state ---
    private double vehicleSpeedKmh; // current vehicle speed (km/h)
    private double currentAcceleration; // current acceleration (m/s²)
    private double expectedDeceleration; // expected deceleration (m/s²)
    private double brakeIntensity; // current brake intensity (0.0 - 1.0)

    // Wheel speed: rotational speed of each wheel (in RPM)
    private Map<String, Double> wheelSpeeds;

    // --- Status flag ---
    private boolean brakeEngaged; // whether the brake has been engaged
    private boolean sensorFaultDetected; // if a sensor fault has been detected
    private boolean manualInterventionRequired; // whether manual intervention is required

    private Instant timestamp;

    public VehicleState() {
        wheelSpeeds = new HashMap<>();
        timestamp = Instant.now();
    }


    public double getVehicleSpeedKmh() {
        return vehicleSpeedKmh;
    }

    public void setVehicleSpeedKmh(double vehicleSpeedKmh) {
        this.vehicleSpeedKmh = vehicleSpeedKmh;
    }

    public double getCurrentAcceleration() {
        return currentAcceleration;
    }

    public void setCurrentAcceleration(double currentAcceleration) {
        this.currentAcceleration = currentAcceleration;
    }

    public double getExpectedDeceleration() {
        return expectedDeceleration;
    }

    public void setExpectedDeceleration(double expectedDeceleration) {
        this.expectedDeceleration = expectedDeceleration;
    }

    public double getBrakeIntensity() {
        return brakeIntensity;
    }

    public void setBrakeIntensity(double brakeIntensity) {
        this.brakeIntensity = brakeIntensity;
    }

    public Map<String, Double> getWheelSpeeds() {
        return wheelSpeeds;
    }

    public void setWheelSpeed(String wheel, double rpm) {
        this.wheelSpeeds.put(wheel, rpm);
    }

    public boolean isBrakeEngaged() {
        return brakeEngaged;
    }

    public void setBrakeEngaged(boolean brakeEngaged) {
        this.brakeEngaged = brakeEngaged;
    }

    public boolean isSensorFaultDetected() {
        return sensorFaultDetected;
    }

    public void setSensorFaultDetected(boolean sensorFaultDetected) {
        this.sensorFaultDetected = sensorFaultDetected;
    }

    public boolean isManualInterventionRequired() {
        return manualInterventionRequired;
    }

    public void setManualInterventionRequired(boolean manualInterventionRequired) {
        this.manualInterventionRequired = manualInterventionRequired;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void updateTimestamp() {
        this.timestamp = Instant.now();
    }

    // Scalable: provides calculation aids such as average wheel speed, slip rate, etc.
    public double getAverageWheelSpeed() {
        return wheelSpeeds.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public String toString() {
        return "VehicleState{" +
                "vehicleSpeedKmh=" + vehicleSpeedKmh +
                ", currentAcceleration=" + currentAcceleration +
                ", expectedDeceleration=" + expectedDeceleration +
                ", brakeIntensity=" + brakeIntensity +
                ", brakeEngaged=" + brakeEngaged +
                ", sensorFaultDetected=" + sensorFaultDetected +
                ", manualInterventionRequired=" + manualInterventionRequired +
                ", timestamp=" + timestamp +
                ", wheelSpeeds=" + wheelSpeeds +
                '}';
    }
}
