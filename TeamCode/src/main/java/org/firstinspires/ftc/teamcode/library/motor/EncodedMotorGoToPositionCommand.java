package org.firstinspires.ftc.teamcode.library.motor;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.command.AbstractSynchronousCommand;

/**
 *
 */
public class EncodedMotorGoToPositionCommand extends AbstractSynchronousCommand {

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
     *
     * @param motor
     * @param position
     * @param power
     */
    public EncodedMotorGoToPositionCommand(EncodedMotor motor, int position, double power) {
        this.motor = motor;
        this.position = position;
        this.power = power;
    }

    /**
     *
     */
    public void init () {

        this.motor.setTargetPosition(this.position);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setPower(this.power);

        this.setInitialized(true);
    }

    @Override
    public void run() {
        if (this.motor.isBusy())
        {
            if (this.motor.getConfig().debug == true) {
                this.motor.getRobot().telemetry.addData("Running to: ",  " %7d", position);
                this.motor.getRobot().telemetry.addData("Currently at: ",  " at %7d", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", " %f", this.motor.getPower());
                this.motor.getRobot().telemetry.update();
            }
        }
        else {
            this.markAsCompleted();

            if (this.motor.isBrakeOn()) {
                this.motor.setPower(0);
                this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            } else {
                this.motor.setPower(1);
            }


        }

    }
}
