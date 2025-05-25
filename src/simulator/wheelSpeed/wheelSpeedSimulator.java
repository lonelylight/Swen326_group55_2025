package simulator;
import java.util.Random;

import simulator.wheel.wheelSpeedReading;
public class wheelSpeedSimulator {
    private String sensorId;
    private boolean faultMode = false;
    private boolean breakProblems = false;
    private Random random = new Random();


    private static final double MIN_SPEED = 0;
    private static final double MAX_SPEED = 250;

    private static double current_speed = 0;

    public wheelSpeedSimulator(String sensorId){
        this.sensorId = sensorId;
    }

    public void enableFaultMode(){
        faultMode = true;
    }

    public void disableFaultMode(){
        faultMode = true;
    }

    public void enableBreakProblems(){
        breakProblems = true;
    }

    public void disableBreakProblems(){
        breakProblems= true;
    }

    public wheelSpeedReading getReading(){
        if(faultMode){
            return new wheelSpeedReading(sensorId, -1);
        } 
        else if(breakProblems){
            current_speed = current_speed - 1;
        }
        else {
            current_speed = MIN_SPEED+ (MAX_SPEED - MIN_SPEED) * random.nextDouble();
        }

        return new wheelSpeedReading(sensorId, current_speed);
    }


}
