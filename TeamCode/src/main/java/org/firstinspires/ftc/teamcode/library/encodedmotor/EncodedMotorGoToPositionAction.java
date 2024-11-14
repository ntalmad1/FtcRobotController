package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
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
    private int lastPos;

    /**
     */
    private int lastPosEqualsCount;

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
        this.timeout = timeout;
    }

    /**
     *
     */
    public void init () {

        this.motor.setTargetPosition(this.position);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setPower(1);

    }

    /**
     *
     * @return
     */
    @Override
    public boolean run() {


        if (this.motor.isBusy())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to: ",  position);
                this.motor.getRobot().telemetry.addData("Currently at: ", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", this.motor.getPower());
                this.motor.getRobot().telemetry.addData("Motor Direction: ", this.motor.getDirection());
                this.motor.getRobot().telemetry.update();
            }

            if (this.timeout != null) {

                int currentPos = this.motor.getCurrentPosition();

                if (currentPos == lastPos) {
                    this.lastPosEqualsCount++;
                } else {
                    this.lastPos = currentPos;
                    this.lastPosEqualsCount = 0;
                }

                if (this.lastPosEqualsCount > 5){
                    this.motor.setPower(0);
                    this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                    return STOP;
                }
            }

            return CONTIUE;
        }
        else {
            if (this.timeout != null) {
                this.motor.setPower(0);
                this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            return STOP;
        }
    }
}
