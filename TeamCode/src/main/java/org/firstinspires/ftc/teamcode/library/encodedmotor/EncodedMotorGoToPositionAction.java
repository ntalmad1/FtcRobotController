package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.action.AbstractAction;

/**
 *
 */
public class EncodedMotorGoToPositionAction extends AbstractAction {

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
    private Integer timeout;

    /**
     */
    private int lastPos;

    /**
     */
    private int lastPosEqualsCount;

    /**
     */
    private int timeoutAfterXCycles = 10;

    private int startPos;

    /**
     *
     * @param motor
     * @param position
     * @param power
     */
    public EncodedMotorGoToPositionAction(EncodedMotor motor, int position, double power, Integer timeout) {
        this.motor = motor;
        this.targetPosition = position;
        this.power = power;
        this.timeout = timeout;
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

            if (this.timeout != null) {

                int currentPos = this.motor.getCurrentPosition();

                if ((startPos < currentPos && currentPos > this.targetPosition - 40)
                        || (startPos > currentPos && currentPos < this.targetPosition + 40)) {
                    if (currentPos == lastPos) {
                        this.lastPosEqualsCount++;
                    } else {
                        this.lastPos = currentPos;
                        this.lastPosEqualsCount = 0;
                    }

                    if (this.lastPosEqualsCount > timeoutAfterXCycles) {
                        if (this.targetPosition != 0 && this.motor.isBrakeOn())
                        {
                            this.motor.setTargetPosition(this.motor.getCurrentPosition());
                            this.motor.setPower(1);
                        }
                        else {
                            this.motor.setPower(0);
                        }
                        return STOP;
                    }
                }
            }

            return CONTIUE;
        }
        else {

            if (this.startPos != this.motor.getCurrentPosition()) {
                // if (this.timeout != null) {
                if (this.targetPosition != 0 && this.motor.isBrakeOn())
                {
                    this.motor.setTargetPosition(this.motor.getCurrentPosition());
                    this.motor.setPower(1);
                }
                else {
                    this.motor.setPower(0);
                }
                // }

                return STOP;

            }

            return CONTIUE;
        }
    }
}
