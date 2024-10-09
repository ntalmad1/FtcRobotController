package org.firstinspires.ftc.teamcode.library.servo;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public class ServoGoToPositionAction extends AbstractAction {

    /**
     */
    private ServoComponent servoComponent;

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
     * Constructor
     *
     * @param servoComponent
     * @param power
     * @param startPosition
     * @param targetPosition
     */
    public ServoGoToPositionAction(ServoComponent servoComponent, double power, double startPosition, double targetPosition) {
        this(servoComponent, power);

        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    /**
     * Hidden Constructor
     *
     * @param servoComponent
     * @param power
     */
    protected ServoGoToPositionAction(ServoComponent servoComponent, double power) {
        this.servoComponent = servoComponent;
        this.power = power;
    }

    /**
     *
     * @return
     */
    public ServoComponent getServoComponent () {
        return this.servoComponent;
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

        double currentPosition = this.servoComponent.getPosition();

        if (direction.equals(Direction.FORWARD)) {

            if (currentPosition <= this.getTargetPosition()) {
                //this.markAsCompleted();
                return STOP;
            }

            if (this.servoComponent.move(-1, this.power, this.getTargetPosition(), this.servoComponent.getMaxPosition())) {
                // this.markAsCompleted();
                return STOP;
            }
        }
        else if (direction.equals(Direction.REVERSE)) {

            if (currentPosition >= this.getTargetPosition()) {
                //this.markAsCompleted();
                return STOP;
            }

            if (this.servoComponent.move(1, this.power, this.servoComponent.getMinPosition(), this.getTargetPosition())) {
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
