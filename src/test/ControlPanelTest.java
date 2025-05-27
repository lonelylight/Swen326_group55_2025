package interface.ControlPanel;

import fusion.sensor.SensorData;
import org.junit.jupiter.api.Test;

import interface.DriverInterface;
import controller.AEBController;

import static org.junit.jupiter.api.Assertions.*;



public class ControlPanelTest{
    //TC_05
    @Test
    void testAEBSToggle(){
        DriverInterface i = new DriverInterface();
        AEBController controller = new AEBController();
        //turn off AEBS
        i.toggleAEBS();
        controller.evaluateAndBrake(new SensorData(5, 20), "vehicle"); // Shouldnt brake
        //turn on AEBS
        i.toggleAEBS();
        controller.evaluateAndBrake(new SensorData(5, 20), "vehicle"); // Should brake 
        }
    //TC_11
    @Test
    void testSetting(){
        DriverInterface i = new DriverInterface();
        
        //out of bounds inputs
        assertThrows(IllegalArgumentException.class, () -> {
        i.configureSetting(51.0, 6.0, 101.0); // Assuming this throws
    });

    }




}