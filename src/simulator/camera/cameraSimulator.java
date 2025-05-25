package simulator.camera;

import java.util.Random;

public class cameraSimulator {
    private String cameraId;
    private boolean faultMode = false;
    private boolean badWeather = false;
    private Random random = new Random();

    public cameraSimulator(String cameraId){
        this.cameraId = cameraId;
    }
    
    private String[] objects = ["None", "Bike", "Car", "Pedestrian"];
    
    public void enableFaultMode(){
        faultMode = true;
    }

    public void disableFaultMode(){
        faultMode = false;
    }

    public void enableBadWeather(){
        badWeather = true;
    }

    public void disableBadWeather(){
        badWeather = false;
    }
    public cameraReading getReading(){
        if(faultMode){
            return new cameraReading(cameraId, null,0);
        }


        String objectType = objects[random.nextInt(objects.length)];

        if(badWeather){
            double accuracy = 0.5 * random.nextDouble();
        }
        else{
            double accuracy = 1 * random.nextDouble();
        }

        return new cameraReading(cameraId, objectType, accuracy);
    }
}
