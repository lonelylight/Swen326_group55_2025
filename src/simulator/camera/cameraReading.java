package simulator.camera;

public class cameraReading {
    private final String cameraId;
    private final String objectType;
    private final double accuracy;

    public cameraReading(String cameraId, String objectType, double accuracy) {
        this.cameraId = cameraId;
        this.objectType = objectType;
        this.accuracy = accuracy;
    }

    public String getCameraId() {
        return cameraId;
    }

    public String getObject() {
        return objectType;
    }
}
