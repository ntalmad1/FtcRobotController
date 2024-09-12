package org.firstinspires.ftc.teamcode.library.motor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_stick_x.Gp2_RightStickXEvent;
import org.firstinspires.ftc.library.component.event.gp2_right_stick_x.Gp2_RightStickXHandler;
import org.firstinspires.ftc.library.utility.Control;

/**
 *
 */
public class EncodedMotor extends Component {

    /**
     */
    private DcMotor motor;

    /**
     */
    private boolean brakeOn;

    /**
     */
    private EncodedMotorConfig config;


    /**
     * @param config
     */
    public EncodedMotor(EncodedMotorConfig config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     * @return
     */
    public EncodedMotorConfig getConfig () {
        return this.config;
    }

    /**
     *
     * @return
     */
    public int getCurrentPosition () {
        return this.motor.getCurrentPosition();
    }

    public int getTargetPosition () {
        return this.motor.getTargetPosition();
    }

    /**
     *
     * @return
     */
    public double getPower () {
        return this.motor.getPower();
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
     * @param power
     */
    public void gotoPosition (int position, double power) {
        this.addCommand(new EncodedMotorGoToPositionCommand(this, position, power));
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

        this.motor = this.robot.hardwareMap.get(DcMotor.class, this.config.motorName);

        this.motor.setDirection(this.config.initialMotorDirection);
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.setBrake(this.config.brakeOn);

        if (Control.Gp2_RightStickX.equals(this.config.control)) {
            this.addGp2_RightStickXHandler(new Gp2_RightStickXHandler() {
                @Override
                public void onGp2_RightStickX(Gp2_RightStickXEvent event) {
                    EncodedMotor.this.move(event.getPosition());
                }
            });
        }
    }

    public boolean isBrakeOn () {
        return this.brakeOn;
    }

    /**
     *
     * @return
     */
    public boolean isBusy () {
        return this.motor.isBusy();
    }

    /**
     *
     * @param power
     */
    public void move (double power) {
        this.move(power, this.config.increment);
    }

    /**
     *
     * @param power
     */
    public void move (double power, int increment) {

        int targetPosition = this.motor.getCurrentPosition() + increment * (power < 0 ? -1 : 1);

        if (power > 0 && targetPosition > this.config.maxTics) {
            targetPosition = this.config.maxTics;
        }
        else if (power < 0 && targetPosition < this.config.minTics) {
            targetPosition = this.config.minTics;
        }

        this.motor.setTargetPosition(targetPosition);
        this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.motor.setPower(power);
    }

    /**
     *
     */
    public void resetEncoder () {
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     *
     * @param brakeOn
     */
    public void setBrake (boolean brakeOn) {
        if (brakeOn) {
            this.brakeOn = true;
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        else {
            this.brakeOn = false;
            this.motor.setPower(0);

            this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    /**
     *
     * @param direction
     */
    public void setDirection (DcMotorSimple.Direction direction) {
        this.motor.setDirection(direction);
    }

    /**
     *
     * @param mode
     */
    public void setMode (DcMotor.RunMode mode) {
        this.motor.setMode(mode);
    }

    /**
     *
     * @param power
     */
    public void setPower (double power) {
        this.motor.setPower(power);
    }

    /**
     *
     * @param position
     */
    public void setTargetPosition (int position) {
        this.motor.setTargetPosition(position);
    }

}
