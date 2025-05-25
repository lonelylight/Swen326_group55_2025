package controller;

import sensors.*;
import fusion.*;

public class AEBController {
    public void evaluateAndBrake(SensorData fusedData, String objectClass) {
        if (fusedData.distance < 10 || objectClass.equals("pedestrian")) {
            System.out.println("[AEB] Emergency Brake Activated!");
        } else {
            System.out.println("[AEB] No action required.");
        }
    }
}
