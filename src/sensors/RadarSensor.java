package sensors;

public class RadarSensor {
    private final String id;
    private static final long UPDATE_INTERVAL_MS = 100;

    public RadarSensor(String id) {
        this.id = id;
    }

    public SensorData getData() {
        // Simulate radar readings
        double distance = getSimulatedDistance(); // meters
        double speed = getSimulatedSpeed();       // km/h
        return new SensorData(distance, speed);
    }

    private double getSimulatedDistance() {
        return 1.0 + Math.random() * 99.0; // 1 to 100 meters
    }

    private double getSimulatedSpeed() {
        return -50 + Math.random() * 100; // -50 to +50 km/h
    }

    public String getId() {
        return id;
    }

    public long getUpdateIntervalMillis() {
        return UPDATE_INTERVAL_MS;
    }
}
