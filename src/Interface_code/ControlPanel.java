package Interface_code;
public class ControlPanel{
    private static double sensitivity = 10;
    private static int threshold;
    private boolean AEBSActive;

    public void setSensitivity(double level){
        assert level > 0;
        
        this.sensitivity = level;
        System.out.println("[Settings] Sensitivity set to" + level);
    }

    public void setThreshold(int milliseconds) {
        assert milliseconds > 0;
        this.threshold = milliseconds;
        System.out.println("[Settings] Threshold set to " + milliseconds + "ms");
    }

    public void toggleAEBS(boolean active) {
        this.AEBSActive = active;
        System.out.println("[Settings] AEBS " + (active ? "Enabled" : "Disabled"));
    }

    public static double getSensitivity() {
        return sensitivity;
    }

    public static int getThreshold() {
        return threshold;
    }

    public boolean isAEBSActive() {
        return AEBSActive;
    }
}