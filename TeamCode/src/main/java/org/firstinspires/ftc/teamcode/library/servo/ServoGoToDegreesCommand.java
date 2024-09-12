package org.firstinspires.ftc.teamcode.library.servo;

public class ServoGoToDegreesCommand extends ServoGoToPositionCommand {

    /**
     */
    private double degrees;

    /**
     *
     * @param degrees
     */
    public ServoGoToDegreesCommand(ServoComponent servo, double degrees, double power) {
        super(servo, power);

        this.degrees = degrees;
    }

    public double getDegrees () {
        return this.degrees;
    }

    /**
     *
     */
    public void init () {
        this.setStartPosition(this.getServo().getPosition());
        this.setTargetPosition(this.getServo().calculateTargetPosition(this.degrees));
        this.setInitialized(true);
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.degrees;
    }
}
