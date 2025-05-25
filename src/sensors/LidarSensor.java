package sensors;

public class LidarSensor {
    private final String id;

    public LidarSensor(String id) {
        this.id = id;
    }

    public SensorData getData() {
        double distance = Math.random() * 80;
        double speed = Math.random() * 60 - 30;
        return new SensorData(distance, speed);
    }
}
