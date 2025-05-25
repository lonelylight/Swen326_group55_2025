package sensors;

public class CameraSystem {
    private final String id;
    private static final long UPDATE_INTERVAL_MS = 50;
    private static final String[] OBJECTS = {"vehicle", "pedestrian", "none"};

    public CameraSystem(String id) {
        this.id = id;
    }

    public VisualData classifyObject() {
        int idx = (int) (Math.random() * OBJECTS.length);
        String objectType = OBJECTS[idx];
        double confidence = generateConfidence(objectType);
        return new VisualData(objectType, confidence);
    }

    private double generateConfidence(String objectType) {
        switch (objectType) {
            case "vehicle":
                return 0.7 + Math.random() * 0.3;  // 70–100%
            case "pedestrian":
                return 0.6 + Math.random() * 0.4;  // 60–100%
            default:
                return 0.0;  // No object
        }
    }

    public String getId() {
        return id;
    }

    public long getUpdateIntervalMillis() {
        return UPDATE_INTERVAL_MS;
    }
}
