package sensors;

public class WheelSensorPair {
    private final String id;

    public WheelSensorPair(String id) {
        this.id = id;
    }

    public double getRPM() {
        return Math.random() * 1000;
    }

    public String getId() {
        return id;
    }

    public void setRPM(double rpm) {
        // Simulate setting RPM, but ensure it doesn't go negative
        if (rpm < 0) {
            System.out.println("RPM cannot be negative. Setting to 0.");
        }
    }
}