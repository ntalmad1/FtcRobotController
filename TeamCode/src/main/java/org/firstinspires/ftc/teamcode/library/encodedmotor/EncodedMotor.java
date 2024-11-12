package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.dcmotor.DcMotorComponent;
import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackHandler;

/**
 *
 */
public class EncodedMotor extends DcMotorComponent {
    /**
     */
    private boolean holding = false;

    /**
     */
    private int holdPosition;

    /**
     * Constructor
     *
     * @param config
     */
    public EncodedMotor(EncodedMotorConfig config) {
        super(config);
    }

    /**
     *
     * @return
     */
    public EncodedMotorConfig getConfig() {
        return (EncodedMotorConfig)super.getConfig();
    }

    /**
     *
     * @param position
     */
    public void gotoPosition (int position) {
        this.gotoPosition(position, 1);
    }

    /**
     *
     * @param position
     */
    public Action gotoPositionAction (int position) {
        return this.gotoPositionAction(position, 1);
    }

    /**
     *
     * @param position
     * @param power
     */
    public void gotoPosition (int position, double power) {
        this.addCommand(new EncodedMotorGoToPositionCommand(this, position, power));
    }

    /**
     *
     * @param motorPos
     * @return
     */
    public Action gotoPositionAction(MotorPos motorPos) {
        if (motorPos == null) {
            return this.gotoPositionAction(this.getCurrentPosition(), 1);
        }

        if (motorPos.getPos() == null) {
            return this.gotoPositionAction(this.getCurrentPosition(), motorPos.getPower());
        }

        return this.gotoPositionAction(motorPos.getPos(), motorPos.getPower());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Action gotoPositionAction (int position, double power) {
        return new EncodedMotorGoToPositionAction(this, position, power);
    }

    /**
     *
     * @param position
     * @param power
     * @param handler
     */
    public void gotoPosition (int position, double power, CommandCallbackHandler handler) {
        EncodedMotorGoToPositionCommand command = new EncodedMotorGoToPositionCommand(this, position, power);
        command.addCallbackHandler(handler);
        this.addCommand(command);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     *
     * @return
     */
    public boolean isHolding() {
        return this.holding;
    }

    /**
     *
     * @param power
     */
    public void move (double power) {
        if (power == 0) {

            if (this.isBrakeOn()) {
                power = 1;
            }

            this.moveToPosition(power, this.getCurrentPosition());
        }
        else if (power > 0) {
            this.moveToPosition(1, this.getCurrentPosition() + (int)(power * this.getConfig().scale));
        }
        else if (power < 0) {
            this.moveToPosition(1, this.getCurrentPosition() + (int)(power * this.getConfig().scale));
        }
    }

    /**
     *
     * @param power
     * @param targetPosition
     */
    public void moveToPosition (double power, int targetPosition) {
        if (power == 0 && this.holding) {
            return;
        }

        if (power > 0 && targetPosition > this.getConfig().maxTics) {
            targetPosition = this.getConfig().maxTics;
            holding = false;
        }
        else if (power < 0 && targetPosition < this.getConfig().minTics) {
            targetPosition = this.getConfig().minTics;
            holding = false;
        }
        else if (power == 0) {
//            targetPosition = this.getCurrentPosition();

//            if (isBrakeOn()) {
//                if (!holding) {
//                    holdPosition = targetPosition;
//                }
//                else {
//                    targetPosition = holdPosition;
//                }
//                holding = true;
//                power = 1;
//            }
//            else {
            if (this.isBrakeOn()) {
                holding = true;
                power = 1;
            }
                //return;
//            }
        }
        else {
            holding = false;
        }

        this.setTargetPosition(targetPosition);
        this.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.setPower(power);
    }

    /**
     *
     */
    public void resetEncoder () {
        this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     */
    public void run () {
        super.run();

        if (!this.isBrakeOn() && this.isDualMotor()) {

            if (!this.getMotor().isBusy() || !this.getSecondaryMotor().isBusy()) {
                this.setPower(0);
            }
        }
    }
}
