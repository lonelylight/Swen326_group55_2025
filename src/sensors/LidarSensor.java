package sensors;

public class LidarSensor {
    private final String id;
    private static final long UPDATE_INTERVAL_MS = 100;

    public LidarSensor(String id) {
        this.id = id;
    }

    public SensorData getData() {
        double distance = getSimulatedDistance(); // meters
        double speed = getSimulatedSpeed();       // km/h
        return new SensorData(distance, speed);
    }

    private double getSimulatedDistance() {
        return 1.0 + Math.random() * 79.0; // 1 to 80 meters
    }

    private double getSimulatedSpeed() {
        return -30 + Math.random() * 60;  // -30 to +30 km/h
    }

    public String getId() {
        return id;
    }

    public long getUpdateIntervalMillis() {
        return UPDATE_INTERVAL_MS;
    }
}
