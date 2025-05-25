package sensors;

public class WheelSpeedData {
    private final double leftWheelRPM;
    private final double rightWheelRPM;
    private final long timestamp;

    public WheelSpeedData(double leftWheelRPM, double rightWheelRPM) {
        this.leftWheelRPM = leftWheelRPM;
        this.rightWheelRPM = rightWheelRPM;
        this.timestamp = System.currentTimeMillis();
    }

    public double getLeftWheelRPM() {
        return leftWheelRPM;
    }

    public double getRightWheelRPM() {
        return rightWheelRPM;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Left RPM: %.2f, Right RPM: %.2f at %d", 
            leftWheelRPM, rightWheelRPM, timestamp);
    }
}
