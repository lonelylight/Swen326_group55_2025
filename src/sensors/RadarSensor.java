package sensors;

public class RadarSensor {
    private String id;

    public RadarSensor(String id) {
        this.id = id;
    }

    public SensorData getData() {
        // Simulated data for now
        double distance = Math.random() * 100; // meters
        double speed = Math.random() * 100 - 50; // km/h
        return new SensorData(distance, speed);
    }
}

