package interface;

import model.VehicleState;

public class DriverInterface{
    private Display display;
    private EmergencyIndicators indicators;
    private ControlPanel controlPanel;


    public DriverInterface(){
        this.display = new Display();
        this.indicators = new EmergencyIndicators(display);
        this.controlPanel = new ControlPanel();
        controlPanel.toggleAEBS(true);
    }

    public void updateStatus(){
        //VehicleState vehicle = new VehicleState();
        boolean sensor = VehicleState.isSensorFaultDetected();
        
        indicators.showSystemStatus(sensor);
    }

    public void triggerEmergencyBraking(double intensity){
        
        indicators.triggerEmergencyBraking(intensity);
    }

    public void toggleAEBS(){
        controlPanel.toggleAEBS(!controlPanel.isAEBSActive());
    }

    public void configureSetting(double sensitivity, double LidarThreshold, double WheelThreshold){
        controlPanel.setSensitivity(sensitivity);
        controlPanel.setLidarThreshold(LidarThreshold);
        controlPanel.setWheelThreshold(WheelThreshold);
        
    }

    public void showStatus(){
        display.showVisualAlert(createStatusMessage());
       
    }

    public String createStatusMessage(){
        String message = "AEBS: "
        if(controlPanel.isAEBSActive()){
            message += "ON";
        }
        else{
            message += "OFF";
        }
        message += "\nSensitivity: " + controlPanel.getSensitivity();
        message += "\nLidar Threshold: " + controlPanel.getLidarThreshold();
        message += "\nWheel Sensor Threshold: " + controlPanel.getWheelThreshold();
        return message;
    }


   
 



}