package sensors;

public class SensorData {
    private final double distanceMeters;
    private final double speedKmH;

    public SensorData(double distanceMeters, double speedKmH) {
        this.distanceMeters = distanceMeters;
        this.speedKmH = speedKmH;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public double getSpeedKmH() {
        return speedKmH;
    }

    @Override
    public String toString() {
        return String.format("Distance: %.2f m, Speed: %.2f km/h", distanceMeters, speedKmH);
    }
}
