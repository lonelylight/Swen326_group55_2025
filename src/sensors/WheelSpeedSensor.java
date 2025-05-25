package sensors;

public class WheelSpeedSensor {
    private final String id;
    private static final long UPDATE_INTERVAL_MS = 10;

    public WheelSpeedSensor(String id) {
        this.id = id;
    }

    public WheelSpeedData getRPMData() {
        double leftRPM = simulateWheelRPM();
        double rightRPM = simulateWheelRPM();
        return new WheelSpeedData(leftRPM, rightRPM);
    }

    private double simulateWheelRPM() {
        return Math.random() * 3000; // 0 to 3000 RPM, typical for cars
    }

    public String getId() {
        return id;
    }

    public long getUpdateIntervalMillis() {
        return UPDATE_INTERVAL_MS;
    }
}
