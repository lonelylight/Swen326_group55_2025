package sensors;

public class VisualData {
    private final String objectType; // e.g., "vehicle", "pedestrian", "none"
    private final double confidence; // e.g., 0.0 to 1.0
    private final long timestamp;    // system time in ms

    public VisualData(String objectType, double confidence) {
        this.objectType = objectType;
        this.confidence = confidence;
        this.timestamp = System.currentTimeMillis();
    }

    public String getObjectType() {
        return objectType;
    }

    public double getConfidence() {
        return confidence;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Detected: %s (%.1f%% confidence) at %d", 
            objectType, confidence * 100, timestamp);
    }
}
