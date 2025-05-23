package hardware;

/**
 * Interface that provides brake control functions.
 * The controller will send brake commands to the actuator through this interface.
 */
public interface BrakeActuator {

    /**
     * Apply brake operation.
     * @param intensity of braking, range: 0.0 (no braking) to 1.0 (maximum braking)
     */
    void applyBrakes(double intensity);

    /**
     * Queries if the physical execution of the brake was successful (confirmed by analog feedback or hardware).
     */
    boolean isBrakeEngaged();
}
