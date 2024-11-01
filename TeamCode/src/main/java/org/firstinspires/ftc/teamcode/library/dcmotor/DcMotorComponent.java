package org.firstinspires.ftc.teamcode.library.dcmotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.utility.Control;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public class DcMotorComponent extends Component {

    /**
     */
    private DcMotor motor;

    /**
     */
    private DcMotor sececondaryMotor;

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
        if (this.isDualMotor()) {
            this.sececondaryMotor = this.robot.hardwareMap.get(DcMotor.class, this.config.secondaryMotorName);
        }
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

        if (Control.Gp1_Dpad_UpDown.equals(control)) {
            this.addGp1_Dpad_Up_DownHandler(event -> { DcMotorComponent.this.move(1); });
            this.addGp1_Dpad_Up_PressHandler(event -> { DcMotorComponent.this.move(0); });

            this.addGp1_Dpad_Down_DownHandler(event -> { DcMotorComponent.this.move(-1); });
            this.addGp1_Dpad_Down_PressHandler(event -> { DcMotorComponent.this.move(0); });
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
    public double getPower() {
        return this.motor.getPower();
    }

    /**
     *
     * @return
     */
    public DcMotor getMotor() {
        return this.motor;
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
     * @return
     */
    public boolean isDualMotor() {
        return this.getConfig().isDualMotor;
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

        if (this.isDualMotor()) {
            if (this.getConfig().initialMotorDirection.equals(this.getConfig().secondaryInitialMotorDirection)) {
                this.sececondaryMotor.setDirection(direction);
            }
            else {
                this.sececondaryMotor.setDirection(Direction.invert(direction));
            }
        }
    }

    /**
     *
     * @param mode
     */
    public void setMode (DcMotor.RunMode mode) {
        this.motor.setMode(mode);
        if (this.isDualMotor()) {
            this.sececondaryMotor.setMode(mode);
        }
    }

    /**
     *
     * @param power
     */
    public void setPower (double power) {
        this.motor.setPower(power);
        if (this.isDualMotor()) {
            this.sececondaryMotor.setPower(power);
        }
    }

    /**
     *
     * @param position
     */
    public void setTargetPosition (int position) {
        this.motor.setTargetPosition(position);
        if (this.isDualMotor()) {
            this.sececondaryMotor.setTargetPosition(position);
        }
    }

    /**
     *
     * @param brakeOn
     */
    public void setBrake (boolean brakeOn) {
        if (brakeOn) {
            this.brakeOn = true;
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            if (this.isDualMotor()) {
                this.sececondaryMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
        }
        else {
            this.brakeOn = false;
            this.motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            if (this.isDualMotor()) {
                this.sececondaryMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            }
        }
    }
}
