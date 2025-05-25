package fusion;

import sensors.*;

public class SensorFusion {
    public static SensorData fuse(SensorData radar, SensorData lidar) {
        double avgDistance = (radar.distance + lidar.distance) / 2.0;
        double avgSpeed = (radar.speed + lidar.speed) / 2.0;
        return new SensorData(avgDistance, avgSpeed);
    }
}
