package org.firstinspires.ftc.teamcode.library.arm.boom;

public class GoToDegreesCommand extends GoToPositionCommand {

    /**
     */
    private double degrees;

    /**
     *
     * @param degrees
     */
    public GoToDegreesCommand (Boom boom, double degrees, double power) {
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
