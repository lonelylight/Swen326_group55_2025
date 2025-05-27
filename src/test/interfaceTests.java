package interface;

import model.VehicleState;
import interface.DriverInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class interfaceTests{
    @Test
    void testStatus(){
        DriverInterface i = new DriverInterface();
        VehicleState vehicle = new VehicleState();

        vehicle.setSensorFaultDetected(false);
        i.updateStatus(); //should return System Ready
        vehicle.setSensorFaultDetected(true);
        i.updateStatus(); //should return Maintenece required
        
        
    }
}
