package fusion;

import sensors.CameraSystem;
import sensors.VisualData;

public class CameraFusionModule {
    private final CameraSystem[] cameras;

    public CameraFusionModule(CameraSystem... cameras) {
        if (cameras.length != 3) {
            throw new IllegalArgumentException("TMR requires exactly 3 camera systems.");
        }
        this.cameras = cameras;
    }

    public VisualData getFusedData() {
        VisualData d1 = cameras[0].classifyObject();
        VisualData d2 = cameras[1].classifyObject();
        VisualData d3 = cameras[2].classifyObject();

        return majorityVote(d1, d2, d3);
    }

    private VisualData majorityVote(VisualData d1, VisualData d2, VisualData d3) {
        if (d1.getObjectType().equals(d2.getObjectType()) && 
            d1.getObjectType().equals(d3.getObjectType())) {
            return d1;
        }
        if (d1.getObjectType().equals(d2.getObjectType())) return pickHigherConfidence(d1, d2);
        if (d1.getObjectType().equals(d3.getObjectType())) return pickHigherConfidence(d1, d3);
        if (d2.getObjectType().equals(d3.getObjectType())) return pickHigherConfidence(d2, d3);

        System.err.println("Camera classification disagreement detected!");
        return pickHighestConfidence(d1, d2, d3);
    }

    private VisualData pickHigherConfidence(VisualData a, VisualData b) {
        return (a.getConfidence() >= b.getConfidence()) ? a : b;
    }

    private VisualData pickHighestConfidence(VisualData a, VisualData b, VisualData c) {
        VisualData best = a;
        if (b.getConfidence() > best.getConfidence()) best = b;
        if (c.getConfidence() > best.getConfidence()) best = c;
        return best;
    }
}
