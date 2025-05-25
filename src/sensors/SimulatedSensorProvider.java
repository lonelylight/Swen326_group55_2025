package sensors;

import model.VehicleState;
import Interface_code.Display;
import fusion.*;

import java.util.Random;

public class SimulatedSensorProvider implements SensorInputProvider {

    private final Random random = new Random();
    private final Display display = new Display(); 

    private final RadarSensor radarSensor;
    private final LidarSensor lidarSensor;
    private final CameraSystem cameraSystem;
    private final WheelSpeedSensor frontWheelSpeedSensor;
    private final WheelSpeedSensor rearWheelSpeedSensor;

    public SimulatedSensorProvider(RadarSensor radarSensor,LidarSensor lidarSensor,CameraSystem cameraSystem,WheelSpeedSensor frontWheelSpeedSensor,WheelSpeedSensor rearWheelSpeedSensor){
        this.frontWheelSpeedSensor = frontWheelSpeedSensor;
        this.rearWheelSpeedSensor = rearWheelSpeedSensor;
        this.cameraSystem = cameraSystem;
        this.lidarSensor = lidarSensor;
        this.radarSensor = radarSensor;
    }


    @Override
    public VehicleState getCurrentVehicleState() {

        // System fault detected
        if(isSystemFault()){
            display.showVisualAlert("System fault detected. Please check the sensors.");
            return  null;
        }
        // check the sensors
        else if(!isSensorDataConsistent(lidarSensor,radarSensor)){
            display.showVisualAlert("Invalid sensor data. Please check the sensors.");
            return  null;
        }
        

        VehicleState state = new VehicleState();

        SensorData fused = SensorFusion.fuse(radarSensor.getData(), lidarSensor.getData());
        
        // Simulate the vehicle speed
        // state.setVehicleSpeedKmh(60 + random.nextGaussian() * 2);
        state.setVehicleSpeedKmh(fused.getSpeedKmH());

        // Simulate acceleration
        state.setCurrentAcceleration(-2.5 + random.nextGaussian() * 0.2); // Simulate the desired deceleration.

        // Simulate the expected deceleration
        state.setExpectedDeceleration(2.5); // fixed or configurable by the controller

        // Simulate brake intensity
        state.setBrakeIntensity(1.0); // Emergency braking

        // Simulate wheel speed data
        // state.setWheelSpeed("FL", 800.0 + random.nextGaussian() * 5); // Front Left
        // state.setWheelSpeed("FR", 805.0 + random.nextGaussian() * 5);
        // state.setWheelSpeed("RL", 790.0 + random.nextGaussian() * 5);
        // state.setWheelSpeed("RR", 795.0 + random.nextGaussian() * 5);
        state.setWheelSpeed("FL", frontWheelSpeedSensor.getRPMData().getLeftWheelRPM());
        state.setWheelSpeed("FR", frontWheelSpeedSensor.getRPMData().getRightWheelRPM());
        state.setWheelSpeed("RL", rearWheelSpeedSensor.getRPMData().getLeftWheelRPM());
        state.setWheelSpeed("RR", rearWheelSpeedSensor.getRPMData().getRightWheelRPM());

        // Emulate the state flag
        state.setBrakeEngaged(true); // Assume previously executed
        state.setSensorFaultDetected(random.nextDouble() < 0.05); // 5% probability of sensor exception

        state.updateTimestamp();
        return state;
    }

    @Override
    public boolean allSensorsOperational() {
        // Simulation: 95% chance of normal
        return random.nextDouble() >= 0.05;
    }

    @Override
    public boolean isSensorDataConsistent(LidarSensor lidarSensor,RadarSensor radarSensor) {
        return Math.abs(lidarSensor.getData().getSpeedKmH() - radarSensor.getData().getSpeedKmH()) <= 0.05;
    }

    @Override
    public boolean isSystemFault() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!allSensorsOperational()) {
                return true;
            }
        }
        return true;
    }
}
