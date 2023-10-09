package org.firstinspires.ftc.teamcode.library.component.command;

public class GoToPositionCommand extends AbstractCommand {

    /**
     */
    private double startPosition;

    /**
     */
    private double targetPosition;

    /**
     *
     * @param startPosition
     * @param targetPosition
     */
    public GoToPositionCommand (double startPosition, double targetPosition) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    public double getStartPosition () {
        return this.startPosition;
    }

    public double getTargetPosition () {
        return this.targetPosition;
    }
}
