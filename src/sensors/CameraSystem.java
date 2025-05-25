package sensors;

public class CameraSystem {
    private final String id;

    public CameraSystem(String id) {
        this.id = id;
    }

    public String classifyObject() {
        String[] objects = {"vehicle", "pedestrian", "none"};
        int idx = (int) (Math.random() * objects.length);
        return objects[idx];
    }
}

