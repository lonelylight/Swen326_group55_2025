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

    public void configureSetting(double sensitivity, double Lidarthreshold, double WheelThreshold){
        controlPanel.setSensitivity(sensitivity);
        controlPanel.setLidarThreshold(Lidarthreshold);
        controlPanel.setWheelThreshold(WheelThreshold);
        
    }


   
 



}