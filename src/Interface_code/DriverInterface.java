package interface;

import model.VehicleState;
//import sensors.SensorInputProvider;
public class DriverInterface{
    private Display display;
    private EmergencyIndicators indicators;
    private ControlPanel controlPanel;

    public DriverInterface(){
        this.display = new Display();
        this.indicators = new EmergencyIndicators(display);
        this.controlPanel = new ControlPanel();
    }

    public void updateStatus(){
        //VehicleState vehicle = new VehicleState();
        boolean sensor_status = VehicleState.isSensorFaultDetected();
       
        indicators.showSystemStatus(sensor_status);
    }

    public void triggerEmergencyBraking(double intensity){
        
        indicators.triggerEmergencyBraking(intensity);
    }

    public void toggleAEBS(boolean active){
        controlPanel.toggleAEBS(active)
    }

    public void configureSetting(double sensitivity, int threshold){
        controlPanel.setSensitivity(sensitivity);
        controlPanel.setThreshold(threshold);
        
    }


   
 



}