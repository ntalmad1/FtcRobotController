package org.firstinspires.ftc.teamcode.library.component.command;

public class GoToDegreesCommand extends GoToPositionCommand {
    /**
     */
    private double startPosition;

    /**
     */
    private double targetPosition;

    private double degrees;

    /**
     *
     * @param degrees
     */
    public GoToDegreesCommand (double degrees) {
        super();
        this.degrees = degrees;
    }

    public double getDegrees () {
        return this.degrees;
    }

    public double getStartPosition () {
        return this.startPosition;
    }

    public double getTargetPosition () {
        return this.targetPosition;
    }

    public void setStartPosition (double startPosition) {
        this.startPosition = startPosition;
    }

    public void setTargetPosition (double targetPosition) {
        this.targetPosition = targetPosition;
    }
}
