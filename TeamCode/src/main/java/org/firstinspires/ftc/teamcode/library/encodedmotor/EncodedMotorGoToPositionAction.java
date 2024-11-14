package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public class EncodedMotorGoToPositionAction extends AbstractAction {

    /**
     */
    private double power;

    /**
     */
    private int position;

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private Integer timeout;

    /**
     */
    private int tolerance = 40;

    /**
     */
    private ElapsedTime timer;

    /**
     */
    private Direction direction;



    /**
     *
     * @param motor
     * @param position
     * @param power
     */
    public EncodedMotorGoToPositionAction(EncodedMotor motor, int position, double power, Integer timeout) {
        this.motor = motor;
        this.position = position;
        this.power = power;

        if (this.motor.getCurrentPosition() <= this.position) {
            direction = Direction.FORWARD;
        } else {
            direction = Direction.REVERSE;
        }


        this.timeout = timeout;


    }

    /**
     *
     */
    public void init () {
        this.motor.moveToPosition(power, position);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (this.timeout != null && this.withinTolerance()) {
            if (this.timer == null) {
                this.timer = new ElapsedTime();
                this.timer.reset();
            }
        }

        if (!this.isTimedOut() && this.motor.isBusy())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to: ",  position);
                this.motor.getRobot().telemetry.addData("Currently at: ", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", this.motor.getPower());
                this.motor.getRobot().telemetry.addData("Motor Direction: ", this.motor.getDirection());
            }

            return CONTIUE;
        }
        else {
            return STOP;
        }

    }

    /**
     *
     * @return
     */
    private boolean isTimedOut() {
        if (this.timer == null) {
            return false;
        }

        boolean timedOut = this.timer.milliseconds() > this.timeout;
        if (timedOut) {
            this.motor.setPower(0);
            this.timer = null;
        }

        return timedOut;
    }

    /**
     *
     * @return
     */
    private boolean withinTolerance() {
//        return position - this.motor.getCurrentPosition() <= tolerance || Math.abs(this.motor.getCurrentPosition() - position) <= tolerance;
        if (Direction.FORWARD.equals(direction)) { // Opening
            return this.motor.getCurrentPosition() >= position - tolerance;
        } else {
            return this.motor.getCurrentPosition() <= position + tolerance;
        }
    }
}
