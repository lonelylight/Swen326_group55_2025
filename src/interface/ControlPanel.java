package interface;
public class ControlPanel{
    private static double sensitivity = 10.0;
    private static double LidarThreshold = 2.0;
    private static double WheelThreshold = 50.0;
    private static boolean AEBSActive;

    public void setSensitivity(double level){
        assert level < 50.0 && level > 0.0;
        this.sensitivity = level;
        System.out.println("[Settings] Sensitivity set to" + level);
    }

    public void setLidarThreshold(double Threshold) {
        assert Threshold >= 1.0 && Threshold <= 5.0;
        this.LidarThresholdThreshold = Threshold;
        System.out.println("[Settings] Lidar Threshold set to " Threshold);
    }

    public void setWheelThreshold(double Threshold) {
        assert Threshold >= 20.0 && Threshold <= 100.0;
        this.WheelThreshold = Threshold;
        System.out.println("[Settings] Wheel Speed Threshold set to " Threshold);
    }


    public void toggleAEBS(boolean active) {
        this.AEBSActive = active;
        System.out.println("[Settings] AEBS " + (active ? "Enabled" : "Disabled"));
    }

    public static double getSensitivity() {
        return sensitivity;
    }

    public static double getLidarThreshold() {
        return LidarThreshold;
    }

    public static double getWheelThreshold() {
        return WheelThreshold;
    }

    public static boolean isAEBSActive() {
        return AEBSActive;
    }
}