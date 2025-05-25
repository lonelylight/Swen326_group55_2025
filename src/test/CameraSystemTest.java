package sensors.camera;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CameraSystemTest {
    @Test
    void testCameraReturnsValidObject() {
        CameraSystem camera = new CameraSystem("C1");
        String object = camera.classifyObject();
        assertTrue(object.equals("vehicle") || object.equals("pedestrian") || object.equals("none"));
    }
}

