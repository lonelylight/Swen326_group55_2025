public class EmergencyIndicators{
    private Display display;

    public EmergencyIndicators(Display display){
        this.display = display;
    }

    public static void triggerEmergencyBraking(double intensity){
        display.showVisualAlert("Emergency Braking! Intensity:" + intensity);
        display.playSound("emergency_brake_tone");
    }

    public void showSystemStatus(boolean ready){
        String status = ready ? "System Ready" : "Maintenance Required";
        display.showVisualAlert(status);
        

    }
}