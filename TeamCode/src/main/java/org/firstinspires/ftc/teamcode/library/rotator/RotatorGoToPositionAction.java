package org.firstinspires.ftc.teamcode.library.rotator;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.command.AbstractCommand;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public class RotatorGoToPositionAction extends AbstractAction {

    /**
     */
    private Rotator boom;

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
    public RotatorGoToPositionAction(Rotator boom, double power, double startPosition, double targetPosition) {
        this(boom, power);

        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    /**
     * Hidden Constructor
     *
     */
    protected RotatorGoToPositionAction(Rotator boom, double power) {
        this.boom = boom;
        this.power = power;
    }

    /**
     *
     * @return
     */
    public Rotator getBoom () {
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
    public boolean run () {

        if (this.getTargetPosition() == this.getStartPosition()) {
            //this.markAsCompleted();
            return STOP;
        }

        Direction direction = this.getTargetPosition() > this.getStartPosition() ? Direction.REVERSE : Direction.FORWARD;

        double currentPosition = this.boom.getPosition();

        if (direction.equals(Direction.FORWARD)) {

            if (currentPosition <= this.getTargetPosition()) {
                //this.markAsCompleted();
                return STOP;
            }

            if (this.boom.move(-1, this.power, this.getTargetPosition(), this.boom.getMaxPosition())) {
                // this.markAsCompleted();
                return STOP;
            }
        }
        else if (direction.equals(Direction.REVERSE)) {

            if (currentPosition >= this.getTargetPosition()) {
                //this.markAsCompleted();
                return STOP;
            }

            if (this.boom.move(1, this.power, this.boom.getMinPosition(), this.getTargetPosition())) {
                // this.markAsCompleted();
                return STOP;
            }
        }

        return CONTIUE;
    }

    /**
     *
     * @return
     */
    public String toString () {
        return this.getClass() + ": " + this.targetPosition;
    }
}
