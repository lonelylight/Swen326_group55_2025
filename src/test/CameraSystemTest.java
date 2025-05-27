package sensors.camera;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CameraSystemTest {
    //TC_08
    @RepeatedTest(10)
    void testCameraReturnsValidClassification() {
        CameraSystem camera = new CameraSystem("C1");
        String classification = camera.classifyObject();
        assertTrue(
            classification.equals("vehicle") || classification.equals("pedestrian") || classification.equals("none"),
            "Classification should be one of: vehicle, pedestrian, or none"
        );
    }

    @Test
    void testCameraSystemInitialization() {
        CameraSystem camera = new CameraSystem("C99");
        assertNotNull(camera);
    }

    @Test
    void testCameraSystemId() {
        CameraSystem camera = new CameraSystem("C100");
        assertEquals("C100", camera.getId(), "Camera ID should match the initialized value");
    }

    @Test
    void testCameraClassificationChanges() {
        CameraSystem camera = new CameraSystem("C101");
        String classification1 = camera.classifyObject();
        String classification2 = camera.classifyObject();
        assertNotEquals(classification1, classification2, "Camera classification should vary on different calls");
    }

    @Test
    void testCameraHandlesInvalidId() {
        CameraSystem camera = new CameraSystem("");
        assertThrows(IllegalArgumentException.class, () -> {
            camera.getId();
        }, "Camera ID should not be empty");
    }

    @Test
    void testCameraHandlesNullId() {
        CameraSystem camera = new CameraSystem(null);
        assertThrows(IllegalArgumentException.class, () -> {
            camera.getId();
        }, "Camera ID should not be null");
    }
}