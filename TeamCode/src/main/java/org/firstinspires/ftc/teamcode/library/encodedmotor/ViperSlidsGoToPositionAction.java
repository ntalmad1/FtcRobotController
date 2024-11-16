package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

/**
 *
 */
public class ViperSlidsGoToPositionAction extends AbstractAction {

    /**
     */
    private double power;

    /**
     */
    private int targetPosition;

    /**
     */
    private EncodedMotor motor;

    /**
     */
    private int startPos;

    /**
     *
     * @param motor
     * @param position
     * @param power
     */
    public ViperSlidsGoToPositionAction(EncodedMotor motor, int position, double power) {
        this.motor = motor;
        this.targetPosition = position;
        this.power = power;
    }

    /**
     *
     */
    public void init () {
        this.startPos = this.motor.getCurrentPosition();

        this.motor.setTargetPosition(this.targetPosition);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setPower(power);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {
        if (!this.motor.getMotor().getMode().equals(DcMotor.RunMode.RUN_TO_POSITION)) {
            this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        if (this.motor.getPower() != power) {
            this.motor.setPower(power);
        }

        if (this.motor.isBusy())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to: ", targetPosition);
                this.motor.getRobot().telemetry.addData("Currently at: ", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", this.motor.getPower());
                this.motor.getRobot().telemetry.addData("Motor Direction: ", this.motor.getDirection());
                this.motor.getRobot().telemetry.update();
            }

            if ((this.targetPosition == Constants.VIPER_SLIDES_MIN_TICS)
                && this.motor.getCurrentPosition() < 60) {
                return STOP;
            }

            return CONTIUE;
        }
        else {

            if ((this.motor.getCurrentPosition() > (this.startPos + 10))
                || (this.motor.getCurrentPosition() < (this.startPos - 10))) {
                return STOP;
            }

            return STOP;
        }
    }
}
