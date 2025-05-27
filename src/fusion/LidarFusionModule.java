package fusion;

import sensors.LidarSensor;
import sensors.SensorData;
import interface.ControlPanel;

public class LidarFusionModule {
    private final LidarSensor[] sensors;

    public LidarFusionModule(LidarSensor... sensors) {
        if (sensors.length != 3) {
            throw new IllegalArgumentException("TMR requires exactly 3 sensors.");
        }
        this.sensors = sensors;
    }

    public SensorData getFusedData() {
        SensorData d1 = sensors[0].getData();
        SensorData d2 = sensors[1].getData();
        SensorData d3 = sensors[2].getData();

        return majorityVote(d1, d2, d3);
    }

    private SensorData majorityVote(SensorData d1, SensorData d2, SensorData d3) {
        //double tol = 2.0; // tighter tolerance than radar
        double tol = ControlPanel.getLidarThreshold();
        boolean d1d2 = closeEnough(d1, d2, tol);
        boolean d1d3 = closeEnough(d1, d3, tol);
        boolean d2d3 = closeEnough(d2, d3, tol);

        if (d1d2 && d1d3) return d1;
        if (d1d2) return d1;
        if (d1d3) return d1;
        if (d2d3) return d2;

        System.err.println("Lidar sensor disagreement detected!");
        return d1; // fallback
    }

    private boolean closeEnough(SensorData a, SensorData b, double tol) {
        return Math.abs(a.getDistanceMeters() - b.getDistanceMeters()) < tol &&
               Math.abs(a.getSpeedKmH() - b.getSpeedKmH()) < tol;
    }
}
