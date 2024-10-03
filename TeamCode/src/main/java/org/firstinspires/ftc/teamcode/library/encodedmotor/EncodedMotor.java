package org.firstinspires.ftc.teamcode.library.encodedmotor;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.library.utility.Control;

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

    /**
     *
     * @return
     */
    public DcMotorSimple.Direction getDirection() {
        return this.motor.getDirection();
    }

    /**
     *
     * @return
     */
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

        this.motor = this.robot.hardwareMap.get(DcMotor.class, this.config.motorName);
        this.motor.setDirection(this.config.initialMotorDirection);

        if (this.config.brakeOn) {
            this.setBrake(true);
        }

        this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (Control.Gp1_LeftStickY.equals(this.config.control)) {
            this.addGp1_LeftStick_Y_Handler(event -> { EncodedMotor.this.move(-event.getPosition()); });
        }

        if (Control.Gp1_RightStickY.equals(this.config.control)) {
            this.addGp1_RightStick_Y_Handler(event -> { EncodedMotor.this.move(-event.getPosition()); });
        }

        if (Control.Gp2_LeftStickY.equals(this.config.control)) {
            this.addGp2_LeftStick_Y_Handler(event -> { EncodedMotor.this.move(-event.getPosition()); });
        }

        if (Control.Gp2_RightStickX.equals(this.config.control)) {
            this.addGp2_RightStick_X_Handler(event -> { EncodedMotor.this.move(event.getPosition()); });
        }

        if (Control.Gp2_RightStickY.equals(this.config.control)) {
            this.addGp2_RightStick_Y_Handler(event -> { EncodedMotor.this.move(event.getPosition()); });
        }

        if (Control.Gp2_Dpad_UpDown.equals(this.config.control)) {
            this.addGp2_Dpad_Up_DownHandler(event -> { EncodedMotor.this.move(1); });
            this.addGp2_Dpad_Up_PressHandler(event -> { EncodedMotor.this.move(0); });

            this.addGp2_Dpad_Down_DownHandler(event -> { EncodedMotor.this.move(-1); });
            this.addGp2_Dpad_Down_PressHandler(event -> { EncodedMotor.this.move(0); });
        }
    }

    public boolean isBrakeOn () {
        return this.brakeOn;
    }

    /**
     *
     * @return
     */
    public boolean isBusy() {
        return this.motor.isBusy();
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
        this.move(power, this.config.increment);
    }

    private double previousPower = 0;

    private boolean holding = false;

    private int holdPosition;

    /**
     *
     * @param power
     */
    public void move (double power, int increment) {
        int targetPosition = this.motor.getCurrentPosition() + increment * (power < 0 ? -1 : 1);
        this.moveToPosition(power, targetPosition);
    }

    /**
     *
     * @param power
     * @param targetPosition
     */
    public void moveToPosition (double power, int targetPosition) {
        if (previousPower == 0 && power == 0) {
            return;
        }

        if (power > 0 && targetPosition > this.config.maxTics) {
            targetPosition = this.config.maxTics;
            previousPower = power;
            holding = false;
        }
        else if (power < 0 && targetPosition < this.config.minTics) {
            targetPosition = this.config.minTics;
            previousPower = power;
            holding = false;
        }
        else if (power == 0) {
            targetPosition = this.motor.getCurrentPosition();
            previousPower = power;

            if (isBrakeOn()) {
                if (!holding) {
                    holdPosition = targetPosition;
                }
                else {
                    targetPosition = holdPosition;
                }
                holding = true;
                power = 1;
            }
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

    /**
     *
     * @return
     */
    public DcMotor.ZeroPowerBehavior getZeroPowerBehavior() {
        return this.motor.getZeroPowerBehavior();
    }

}
