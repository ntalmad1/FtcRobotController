package org.firstinspires.ftc.library.winch;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger.Gp2_Left_Trigger_Event;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger.Gp2_Left_Trigger_Handler;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_left_trigger_down.Gp2_Left_Trigger_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger.Gp2_Right_Trigger_Event;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger.Gp2_Right_Trigger_Handler;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownEvent;
import org.firstinspires.ftc.library.component.event.gp2_right_trigger_down.Gp2_Right_Trigger_DownHandler;
import org.firstinspires.ftc.library.motor.EncodedMotor;

/**
 *
 */
public class Winch extends Component {

    /**
     */
    private WinchConfig config;

    /**
     */
    private EncodedMotor motor;

    /**
     * Constructor
     *
     * @param config
     */
    public Winch(WinchConfig config) {
        super(config.robot);

        this.config = config;
        config.debug = true;

        this.motor = new EncodedMotor(this.config.encodedMotorConfig);
    }

    /**
     *
     */
    public void setBrakeOn () {
        this.motor.setBrake(true);
    }

    /**
     *
     */
    public void setBrakeOff () {
        this.motor.setBrake(false);
    }

    /**
     *
     */
    public WinchConfig getConfig () {
        return this.config;
    }

    /**
     */
    private  int lastPosition;

    /**
     */
    private boolean holdFlag = false;

    /**
     *
     */
    public void init () {
        super.init();

        this.motor.init();

        this.addGp2_Left_Trigger_Handler(new Gp2_Left_Trigger_Handler() {
            @Override
            public void onGp2_Left_Trigger(Gp2_Left_Trigger_Event event) {
                Winch.this.onTriggerEvent(event.getPosition() * -1);
            }
        });

        this.addGp2_Right_Trigger_Handler(new Gp2_Right_Trigger_Handler() {
            @Override
            public void onGp2_Right_Trigger(Gp2_Right_Trigger_Event event) {
                Winch.this.onTriggerEvent(event.getPosition());
            }
        });

    }

    /**
     *
     * @param position
     */
    protected void onTriggerEvent (float position) {
        if (position == 0) {

            if (this.motor.isBrakeOn()) {

                if (!this.holdFlag) {
                    this.holdFlag = true;
                    this.lastPosition = this.motor.getCurrentPosition();
                }

                this.motor.setTargetPosition(this.lastPosition);
                this.motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                this.motor.setPower(1);
            }
            else {
                this.motor.setPower(0);
                this.motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
        else {
            this.holdFlag = false;
            this.motor.move(position);
        }
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.motor.run();

        if (this.config.debug) {
            this.telemetry.addData("Winch motor current position: ", "%7d", this.motor.getCurrentPosition());
            this.telemetry.addLine("Brake: " + (this.motor.isBrakeOn() ? "ON" : "OFF"));
            this.telemetry.update();
        }
    }

    /**
     *
     * @param position
     * @param power
     */
    public void goToPosition(int position, double power) {

        this.motor.gotoPosition(position, power);

    }

    /**
     *
     * @param position
     * @param power
     * @param commandCallbackAdapter
     */
    public void goToPosition(int position, double power, CommandCallbackAdapter commandCallbackAdapter) {

        this.motor.gotoPosition(position, power, commandCallbackAdapter);

    }
}
