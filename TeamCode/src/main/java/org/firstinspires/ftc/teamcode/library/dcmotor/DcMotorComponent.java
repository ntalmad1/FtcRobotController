package org.firstinspires.ftc.teamcode.library.dcmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.utility.Control;

public class DcMotorComponent extends Component {

    /**
     */
    private DcMotor motor;

    /**
     */
    private boolean brakeOn;

    /**
     */
    private DcMotorComponentConfig config;

    /**
     * Constructor
     *
     * @param dcMotorComponentConfig
     */
    public DcMotorComponent(DcMotorComponentConfig dcMotorComponentConfig) {
        super(dcMotorComponentConfig.robot);

        this.config = dcMotorComponentConfig;
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.motor = this.robot.hardwareMap.get(DcMotor.class, this.config.motorName);
        this.setDirection(this.config.initialMotorDirection);

        if (this.config.brakeOn) {
            this.setBrake(true);
        }

        this.addControl(this.config.control);
    }

    /**
     *
     * @param control
     */
    public void addControl(Control control) {
        if (Control.Gp1_LeftStickY.equals(control)) {
            this.addGp1_LeftStick_Y_Handler(event -> { DcMotorComponent.this.move(-event.getPosition()); });
        }

        if (Control.Gp1_RightStickY.equals(control)) {
            this.addGp1_RightStick_Y_Handler(event -> { DcMotorComponent.this.move(-event.getPosition()); });
        }

        if (Control.Gp2_LeftStickY.equals(control)) {
            this.addGp2_LeftStick_Y_Handler(event -> { DcMotorComponent.this.move(-event.getPosition()); });
        }

        if (Control.Gp2_RightStickX.equals(control)) {
            this.addGp2_RightStick_X_Handler(event -> { DcMotorComponent.this.move(event.getPosition()); });
        }

        if (Control.Gp2_RightStickY.equals(control)) {
            this.addGp2_RightStick_Y_Handler(event -> { DcMotorComponent.this.move(event.getPosition()); });
        }

        if (Control.Gp2_Dpad_UpDown.equals(control)) {
            this.addGp2_Dpad_Up_DownHandler(event -> { DcMotorComponent.this.move(1); });
            this.addGp2_Dpad_Up_PressHandler(event -> { DcMotorComponent.this.move(0); });

            this.addGp2_Dpad_Down_DownHandler(event -> { DcMotorComponent.this.move(-1); });
            this.addGp2_Dpad_Down_PressHandler(event -> { DcMotorComponent.this.move(0); });
        }
    }

    /**
     *
     * @return
     */
    public DcMotorComponentConfig getConfig() {
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
     * @return
     */
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
     * @param power
     */
    public void move (double power) {
        this.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.setPower(power);
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
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }
}
