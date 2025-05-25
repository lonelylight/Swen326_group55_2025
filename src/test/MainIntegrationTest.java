package integration;

import controller.AEBController;
import fusion.FallbackManager;
import fusion.SensorFusion;
import fusion.sensor.SensorData;
import sensors.camera.CameraSystem;
import sensors.lidar.LidarSensor;
import sensors.radar.RadarSensor;

import org.junit.jupiter.api.Test;

public class MainIntegrationTest {
    @Test
    void testFullSystemCycle() {
        RadarSensor radar = new RadarSensor("R1");
        LidarSensor lidar = new LidarSensor("L1");
        CameraSystem camera = new CameraSystem("C1");

        SensorData radarData = radar.getData();
        SensorData lidarData = lidar.getData();
        String objectType = camera.classifyObject();

        SensorData fused = SensorFusion.fuse(radarData, lidarData);

        FallbackManager fm = new FallbackManager();
        if (!fusion.SensorMonitor.isSensorHealthy(radarData)) fm.reportFailure("radar");
        if (!fusion.SensorMonitor.isSensorHealthy(lidarData)) fm.reportFailure("lidar");

        if (fm.shouldFallback()) {
            System.out.println("Fallback activated due to sensor issue");
        } else {
            AEBController controller = new AEBController();
            controller.evaluateAndBrake(fused, objectType);
        }
    }
}
