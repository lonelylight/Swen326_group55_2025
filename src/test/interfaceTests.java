package interface;

import model.VehicleState;
import interface.DriverInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;






public class interfaceTests{
    //TC_07
    @Test
    void testStatus(){
        DriverInterface i = new DriverInterface();
        VehicleState vehicle = new VehicleState();

        vehicle.setSensorFaultDetected(false);
        i.updateStatus(); //should return System Ready
        vehicle.setSensorFaultDetected(true);
        i.updateStatus(); //should return Maintenece required
        
        
    }
//TC_04
    @Test 
    void testPrintStatus(){
        //test with default values
        DriverInterface i = new DriverInterface();
        String actualMessage = i. createStatusMessage();
        String expectedMessage =  
            "AEBS: ON\n" +
            "Sensitivity: 10.0\n" +
            "Lidar Threshold: 2.0\n" +
            "Wheel Sensor Threshold: 50.0";

        assertEquals(expectedMessage, actualMessage);
    }
    //TC_20
    @Test 
    void testAEBSon(){
        DriverInterface i = new DriverInterface();
        assertTrue(i.controlPanel.isAEBSActive());
    }
}
