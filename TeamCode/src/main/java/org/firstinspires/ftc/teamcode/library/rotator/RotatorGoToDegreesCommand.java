package org.firstinspires.ftc.teamcode.library.rotator;

public class RotatorGoToDegreesCommand extends RotatorGoToPositionCommand {

    /**
     */
    private double degrees;

    /**
     *
     * @param degrees
     */
    public RotatorGoToDegreesCommand(Rotator boom, double degrees, double power) {
        super(boom, power);

        this.degrees = degrees;
    }

    public double getDegrees () {
        return this.degrees;
    }

    /**
     *
     */
    public void init () {
        this.setStartPosition(this.getBoom().getPosition());
        this.setTargetPosition(this.getBoom().calculateTargetPosition(this.degrees));
        this.setInitialized(true);
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.degrees;
    }
}
