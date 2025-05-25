package fusion;

import sensors.*;

public class SensorMonitor {
    public static boolean isSensorHealthy(SensorData data) {
        return data.distance >= 0 && data.distance < 200 &&
               data.speed > -150 && data.speed < 150;
    }

    public static boolean simulateTimeout(double probability) {
        return Math.random() < probability;
    }
}
