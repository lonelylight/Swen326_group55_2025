package fusion;

public class FallbackManager {
    private boolean radarFailed = false;
    private boolean lidarFailed = false;

    public void reportFailure(String sensorType) {
        if (sensorType.equals("radar")) radarFailed = true;
        if (sensorType.equals("lidar")) lidarFailed = true;
    }

    public boolean shouldFallback() {
        return radarFailed || lidarFailed;
    }

    public void reset() {
        radarFailed = false;
        lidarFailed = false;
    }
}
