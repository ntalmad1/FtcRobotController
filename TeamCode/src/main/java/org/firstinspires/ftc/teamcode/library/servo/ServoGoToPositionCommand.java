package org.firstinspires.ftc.teamcode.library.servo;

import org.firstinspires.ftc.library.component.command.AbstractCommand;
import org.firstinspires.ftc.library.utility.Direction;

/**
 *
 */
public class ServoGoToPositionCommand extends AbstractCommand {

    /**
     */
    private ServoComponent servo;

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
    public ServoGoToPositionCommand(ServoComponent servo, double power, double startPosition, double targetPosition) {
        this(servo, power);

        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    /**
     * Hidden Constructor
     *
     */
    protected ServoGoToPositionCommand(ServoComponent boom, double power) {
        this.servo = boom;
        this.power = power;
        this.setSynchronous(true);
    }

    /**
     *
     * @return
     */
    public ServoComponent getServo() {
        return this.servo;
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

        double currentPosition = this.servo.getPosition();

        if (direction.equals(Direction.FORWARD)) {

            if (currentPosition <= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.servo.move(-1, this.power, this.getTargetPosition(), this.servo.getMaxPosition())) {
                this.markAsCompleted();
                return;
            }
        }
        else if (direction.equals(Direction.REVERSE)) {

            if (currentPosition >= this.getTargetPosition()) {
                this.markAsCompleted();
                return;
            }

            if (this.servo.move(1, this.power, this.servo.getMinPosition(), this.getTargetPosition())) {
                this.markAsCompleted();
                return;
            }
        }
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.targetPosition;
    }
}
