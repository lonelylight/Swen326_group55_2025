public class ControlPanel{
    private static double sensitivity = 10;
    private static double LidarThreshold = 2.0;
    private static double WheelThreshold = 50.0;
    private static boolean AEBSActive;

    public void setSensitivity(double level){

        this.sensitivity = level;
        System.out.println("[Settings] Sensitivity set to" + level);
    }

    public void setLidarThreshold(int Threshold) {
        this.LidarThresholdThreshold = Threshold;
        System.out.println("[Settings] Lidar Threshold set to " Threshold);
    }

    public void setWheelThreshold(int Threshold) {
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

    public static int getLidarThreshold() {
        return LidarThreshold;
    }

    public static int getWheelThreshold() {
        return WheelThreshold;
    }

    public static boolean isAEBSActive() {
        return AEBSActive;
    }
}