package simulator.wheelSpeed;

public class wheelSpeedReading {
    private final String wheelSensorId;
    private final double speed;

    public wheelSpeedReading(String wheelSensorId, double speed) {
        this.wheelSensorId = wheelSensorId;
        this.speed = speed;
    }

    public String getWheelSensorId() {
        return wheelSensorId;
    }

    public double getSpeed(){
        return speed;
    }

}
