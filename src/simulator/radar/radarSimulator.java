
import java.util.Random;
import simulator.radar.radarReading;

public class radarSimulator {
    private String sensorId;
    private boolean faultMode = false;
    private boolean stuckValueMode = false;
    private double stuckDistance = 0.0;
    private Random random = new Random();
    
    private static final double MAX_DISTANCE = 200;
    private static final double MIN_DISTANCE = 0.5;
    private static final double MAX_RELATIVE_SPEED = 100;
   
    public radarSimulator(String sensorId){
        this.sensorId = sensorId;
    }

    public void enableFaultMode(){
        faultMode = true;
    }

    public void disableFaultMode(){
        faultMode = true;
    }

    public double getReading(){
        if(faultMode){
            return new radarReading(sensorId, -1, -999).getDistanceMeters();
        }
         
        double distance = 0;
        if (stuckValueMode) {
            distance = stuckDistance;
        } else {
            distance = MIN_DISTANCE + (MAX_DISTANCE - MIN_DISTANCE) * random.nextDouble();
        }

        // Simulate relative speed between -MAX_RELATIVE_SPEED and +MAX_RELATIVE_SPEED
        double relativeSpeed = MAX_RELATIVE_SPEED + 2 * MAX_RELATIVE_SPEED * random.nextDouble();

        return new radarReading(sensorId, distance, relativeSpeed).getDistanceMeters();

    }


}
