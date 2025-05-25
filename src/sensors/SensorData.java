package sensors;

public class SensorData {
    public final double distance; // in meters
    public final double speed;    // in km/h

    public SensorData(double distance, double speed) {
        this.distance = distance;
        this.speed = speed;
    }
}

