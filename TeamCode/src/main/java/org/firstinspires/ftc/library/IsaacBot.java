package org.firstinspires.ftc.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.library.component.event.g1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.library.component.event.g1_b_press.Gp1_B_PressHandler;
import org.firstinspires.ftc.library.component.event.g2_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.library.component.event.g2_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.library.component.event.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.library.component.IComponent;
import org.firstinspires.ftc.library.component.RobotComponent;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.WaitCommand;
import org.firstinspires.ftc.library.component.event.EventBus;
import org.firstinspires.ftc.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.library.component.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;
import org.firstinspires.ftc.library.component.event.gp2_left_stick_y.Gp2_LeftStickYHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_stick_x.Gp2_RightStickXHandler;
import org.firstinspires.ftc.library.component.event.gp2_right_stick_y.Gp2_RightStickYHandler;
import org.firstinspires.ftc.library.component.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 */
public abstract class  IsaacBot extends LinearOpMode implements IComponent
{
    private static IsaacBot instance;

    public static IsaacBot getInstance() {
        return instance;
    }

    private RobotComponent robotComponent;

    private String imuName;

    /**
     */
    private IMU imu;

    /**
     */
    public IsaacBot()
    {
        super();

        instance = this;

        EventBus.init(this);

        this.robotComponent = new RobotComponent(this);
    }

    /**
     *
     * @return
     */
    public double getYaw ()
    {
        YawPitchRollAngles gyro = imu.getRobotYawPitchRollAngles();
        double yaw = gyro.getYaw(AngleUnit.DEGREES);

        return yaw;
    }

    /**
     *
     * @return
     */
    public EventBus getEventBus () {
        return EventBus.getInstance();
    }

    /**
     *
     */
    public void resetYaw () {
        this.initImu(this.imuName);
    }


    /**
     *
     * @param imuName
     */
    public void initImu (String imuName)
    {
//        telemetry.addLine("Imu Name: " + imuName);
//        telemetry.update();

        this.imu = this.hardwareMap.get(IMU.class, imuName);
        this.imu.resetYaw();
    }

    /**
     * This code gets ran at the start of runOpMode before start is pressed
     */
    public void initBot () {
       this.robotComponent.init();
    }

    /**
     * This code gets ran right after start pressed and is only ran once
     */
    public void go () {

    }


    /**
     * This code gets ran after start pressed and "whileOpIsActive" in a loop
     */
    public void run () {
        this.getEventBus().run();
        this.robotComponent.run();
    }

    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {
        this.initBot();

        this.waitForStart();

        this.go();

        while (this.opModeIsActive()) {
            this.run();
        }
    }

    /**
     *
     * @param command
     */
    public void addCommand (ICommand command) {
        this.robotComponent.addCommand(command);
    }

    public HandlerRegistration addGp1_A_PressHandler (Gp1_A_PressHandler handler) {
        return this.robotComponent.addGp1_A_PressHandler(handler);
    }

    public HandlerRegistration addGp1_B_PressHandler (Gp1_B_PressHandler handler) {
        return this.robotComponent.addGp1_B_PressHandler(handler);
    }

    public HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler) {
        return this.robotComponent.addGp2_A_PressHandler(handler);
    }

    public HandlerRegistration addGp2_B_PressHandler (Gp2_B_PressHandler handler) {
        return this.robotComponent.addGp2_B_PressHandler(handler);
    }

    public HandlerRegistration addGp2_X_PressHandler (Gp2_X_PressHandler handler) {
        return this.robotComponent.addGp2_X_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Y_PressHandler (Gp2_Y_PressHandler handler) {
        return this.robotComponent.addGp2_Y_PressHandler(handler);
    }

    public HandlerRegistration addGp1_X_PressHandler (Gp1_X_PressHandler handler) {
        return this.robotComponent.addGp1_X_PressHandler(handler);
    }

    public HandlerRegistration addGp1_Y_PressHandler (Gp1_Y_PressHandler handler) {
        return this.robotComponent.addGp1_Y_PressHandler(handler);
    }
    //endregion

    public HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Left_Bumper_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Right_Bumper_PressHandler(handler);
    }


    //-----------------------------------------------------------------------------------------

    public HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Left_DownHandler(handler);
    }

    public HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Right_DownHandler(handler);
    }

    public HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Up_DownHandler(handler);
    }

    public HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Down_DownHandler(handler);
    }

    //-----------------------------------------------------------------------------------------

    public HandlerRegistration addGp1_Dpad_Down_PressHandler (Gp1_Dpad_Down_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Down_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStickXHandler (Gp2_LeftStickXHandler handler) {
        return this.robotComponent.addGp2_LeftStickXHandler(handler);
    }

    public HandlerRegistration addGp2_LeftStickYHandler (Gp2_LeftStickYHandler handler) {
        return this.robotComponent.addGp2_LeftStickYHandler(handler);
    }

    public HandlerRegistration addGp2_RightStickXHandler (Gp2_RightStickXHandler handler) {
        return this.robotComponent.addGp2_RightStickXHandler(handler);
    }

    public HandlerRegistration addGp2_RightStickYHandler (Gp2_RightStickYHandler handler) {
        return this.robotComponent.addGp2_RightStickYHandler(handler);
    }

    /**
     *
     * @param milliseconds
     * @param callbackHandler
     */
    public IsaacBot wait (int milliseconds, CommandCallbackHandler callbackHandler) {
        WaitCommand command = new WaitCommand(milliseconds);
        command.addCallbackHandler(callbackHandler);

        this.robotComponent.addCommand(command);

        return this;
    }

    /**
     *
     * @param imuName
     */
    public void setImuName (String imuName) {
        this.imuName = imuName;
    }

    /**
     *
     * @param message
     */
    public void voiceLog (String message) {
        this.voiceLog(message, 2000);
    }

    /**
     *
     * @param message
     * @param milliseconds
     */
    public void voiceLog (String message, long milliseconds) {
        this.telemetry.speak(message);
        this.telemetry.log().add(message);

        try {
            this.sleep(milliseconds);
        } catch (Exception e) {
            // do nothing
        }
    }

}
