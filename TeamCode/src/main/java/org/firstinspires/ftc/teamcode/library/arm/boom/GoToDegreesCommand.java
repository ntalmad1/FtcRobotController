package org.firstinspires.ftc.teamcode.library.arm.boom;

public class GoToDegreesCommand extends GoToPositionCommand {

    /**
     */
    private Boom boom;

    /**
     */
    private double degrees;

    /**
     *
     * @param degrees
     */
    public GoToDegreesCommand (Boom boom, double degrees) {
        super();

        this.boom = boom;
        this.degrees = degrees;
    }

    public double getDegrees () {
        return this.degrees;
    }

    /**
     *
     */
    public void init () {
        this.setStartPosition(this.boom.getPosition());
        this.setTargetPosition(this.boom.calculateTargetPosition(this.degrees));
        this.setInitialized(true);
    }
}
