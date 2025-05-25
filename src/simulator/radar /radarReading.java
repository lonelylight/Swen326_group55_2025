package simulator.radar ;

public class radarReading {
    private final String radarId;
    private final double distanceMeters;
    private final double relativeSpeedKmh;

    public radarReading(String radarId, double distanceMeters, double relativeSpeedKmh) {
        this.radarId = radarId;
        this.distanceMeters = distanceMeters;
        this.relativeSpeedKmh = relativeSpeedKmh;
    }

    public String getRadarId() {
        return radarId;
    }

    public double getDistanceMeters() {
        return distanceMeters;
    }

    public double getRelativeSpeedKmh() {
        return relativeSpeedKmh;
    }
}
