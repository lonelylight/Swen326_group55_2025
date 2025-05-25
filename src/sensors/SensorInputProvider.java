package sensors;

import model.VehicleState;

public interface SensorInputProvider {

    /**
     * Get a complete snapshot of the current vehicle state.
     */
    VehicleState getCurrentVehicleState();

    /**
     * Whether all sensors are working properly.
     */
    boolean allSensorsOperational();

    /**
     * Analog redundancy check: specify if the sensors are redundant data consistent.
     * @param sensorName Sensor name, e.g. “LIDAR”, “RADAR”, “Camera”.
     */
    boolean isSensorDataConsistent(LidarSensor lidarSensor,RadarSensor radarSensor);

    /**
     * 判断系统是否故障
     * @return
     */
    boolean isSystemFault();

}
