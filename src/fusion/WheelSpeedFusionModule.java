package fusion;

import sensors.WheelSpeedSensor;
import sensors.WheelSpeedData;

public class WheelSpeedFusionModule {
    private final WheelSpeedSensor[] sensors;

    public WheelSpeedFusionModule(WheelSpeedSensor... sensors) {
        if (sensors.length != 3) {
            throw new IllegalArgumentException("TMR requires exactly 3 sensors.");
        }
        this.sensors = sensors;
    }

    public WheelSpeedData getFusedData() {
        WheelSpeedData d1 = sensors[0].getRPMData();
        WheelSpeedData d2 = sensors[1].getRPMData();
        WheelSpeedData d3 = sensors[2].getRPMData();

        return majorityVote(d1, d2, d3);
    }

    private WheelSpeedData majorityVote(WheelSpeedData d1, WheelSpeedData d2, WheelSpeedData d3) {
        double tol = 50.0; // RPM tolerance

        boolean d1d2 = closeEnough(d1, d2, tol);
        boolean d1d3 = closeEnough(d1, d3, tol);
        boolean d2d3 = closeEnough(d2, d3, tol);

        if (d1d2 && d1d3) return d1;
        if (d1d2) return d1;
        if (d1d3) return d1;
        if (d2d3) return d2;

        System.err.println("Wheel speed sensor disagreement detected!");
        return d1; // fallback
    }

    private boolean closeEnough(WheelSpeedData a, WheelSpeedData b, double tol) {
        return Math.abs(a.getLeftWheelRPM() - b.getLeftWheelRPM()) < tol &&
               Math.abs(a.getRightWheelRPM() - b.getRightWheelRPM()) < tol;
    }
}
