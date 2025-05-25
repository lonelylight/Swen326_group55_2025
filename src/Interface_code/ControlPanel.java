public class ControlPanel{
    private double sensitivity;
    private int threshold;
    private boolean AEBSActive;

    public void setSensitivity(double level){

        this.sensitivity = level;
        System.out.println("[Settings] Sensitivity set to" + level);
    }

    public void setThreshold(int milliseconds) {
        this.threshold = milliseconds;
        System.out.println("[Settings] Threshold set to " + milliseconds + "ms");
    }

    public void toggleAEBS(boolean active) {
        this.AEBSActive = active;
        System.out.println("[Settings] AEBS " + (active ? "Enabled" : "Disabled"));
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public int getThreshold() {
        return threshold;
    }

    public boolean isAEBSActive() {
        return AEBSActive;
    }
}