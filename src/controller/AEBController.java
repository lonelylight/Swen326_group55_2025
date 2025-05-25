package controller;

import sensors.*;
import fusion.*;
import interface.ControlPanel;
public class AEBController {


    public void evaluateAndBrake(SensorData fusedData, String objectClass) {
        if (fusedData.distance < ControlPanel.getSensitivity() || objectClass.equals("pedestrian")) {
            System.out.println("[AEB] Emergency Brake Activated!");
        } else {
            System.out.println("[AEB] No action required.");
        }
    }

    

    
}
