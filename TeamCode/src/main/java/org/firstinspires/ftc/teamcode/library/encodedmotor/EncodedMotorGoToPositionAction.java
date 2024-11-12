package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.library.action.AbstractAction;
import org.firstinspires.ftc.teamcode.library.command.AbstractSynchronousCommand;

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
    Integer timeout = 250;

    /**
     */
    ElapsedTime timer;


    /**
     *
     * @param motor
     * @param position
     * @param power
     */
    public EncodedMotorGoToPositionAction(EncodedMotor motor, int position, double power) {
        this.motor = motor;
        this.position = position;
        this.power = power;


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
        if (this.withinTolerance()) {
            if (this.timer == null) {
                this.timer = new ElapsedTime();
                this.timer.reset();
            }
        }

        if (!this.isTimedOut() && this.motor.isBusy() && !this.motor.isHolding())
        {
            if (this.motor.getConfig().debug) {
                this.motor.getRobot().telemetry.addData("Running to: ",  " %7d", position);
                this.motor.getRobot().telemetry.addData("Currently at: ",  " at %7d", this.motor.getCurrentPosition());
                this.motor.getRobot().telemetry.addData("Motor Power: ", " %f", this.motor.getPower());
                this.motor.getRobot().telemetry.update();
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

        return this.timer.milliseconds() > this.timeout;
    }

    /**
     *
     * @return
     */
    private boolean withinTolerance() {
        return position - 25 > this.motor.getCurrentPosition() || position + 25 < this.motor.getCurrentPosition();
    }
}
