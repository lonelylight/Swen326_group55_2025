package test;

import simulator.*;
import simulator.radar.*;
import simulator.camera.*;
import simulator.wheelSpeed.*;
import fusion.sensor.SensorData;
import sensors.RadarSensor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CameraSimulatorTest{
    
    //TC20
    @Test
    void testRadarSimulatorWorks() {
        radarSimulator sim1 = new radarSimulator("R1");
        double speed = sim1.getReading();

        assert(speed > 0.5);
        assert(speed < 200);

    }

    //TC21
    @Test
    void testRadarSimulatorBroken() {
        radarSimulator sim1 = new radarSimulator("R1");
        sim1.enableFaultMode();
        double speed = sim1.getReading();

        assert(speed < 0);

    }

    //TC20
    @Test
    void testRadarSimulatorStuck() {
        radarSimulator sim1 = new radarSimulator("R1");
        sim1.enableStuckValueMode();
        double speed = sim1.getReading();
        double temp = speed;
        speed = sim1.getReading();
        assert(temp == speed);

    }

    
}
