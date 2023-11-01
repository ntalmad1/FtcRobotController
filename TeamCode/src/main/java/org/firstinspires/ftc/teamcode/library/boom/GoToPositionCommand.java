package org.firstinspires.ftc.teamcode.library.boom;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public class GoToPositionCommand extends AbstractCommand {

    /**
     */
    private Boom boom;

    /**
     */
    private double power;

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
    public GoToPositionCommand (Boom boom, double power, double startPosition, double targetPosition) {
        this(boom, power);

        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    /**
     * Hidden Constructor
     *
     */
    protected GoToPositionCommand(Boom boom, double power) {
        this.boom = boom;
        this.power = power;
        this.setSynchronous(true);
    }

    /**
     *
     * @return
     */
    public Boom getBoom () {
        return this.boom;
    }

    /**
     *
     * @return
     */
    public double getStartPosition () {
        return this.startPosition;
    }

    /**
     *
     * @return
     */
    public double getTargetPosition () {
        return this.targetPosition;
    }

    /**
     *
     * @param startPosition
     */
    public void setStartPosition (double startPosition) {
        this.startPosition = startPosition;
    }

    /**
     *
     * @param targetPosition
     */
    public void setTargetPosition (double targetPosition) {
        this.targetPosition = targetPosition;
    }

    /**
     *
     */
    public void run () {
        if (this.isCompleted()) {
            return;
        }

        if (this.getTargetPosition() == this.getStartPosition()) {
            this.markAsCompleted();
            return;
        }

        Direction direction = this.getTargetPosition() > this.getStartPosition() ? Direction.REVERSE : Direction.FORWARD;

        double currentPosition = this.boom.getPosition();

        if (direction.equals(Direction.FORWARD)) {

            if (currentPosition <= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.boom.move(-1, this.power, this.getTargetPosition(), this.boom.getMaxPosition())) {
                this.markAsCompleted();
                return;
            }
        }
        else if (direction.equals(Direction.REVERSE)) {

            if (currentPosition >= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.boom.move(1, this.power, this.boom.getMinPosition(), this.getTargetPosition())) {
                this.markAsCompleted();
                return;
            }
        }
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.targetPosition;
    }
}
