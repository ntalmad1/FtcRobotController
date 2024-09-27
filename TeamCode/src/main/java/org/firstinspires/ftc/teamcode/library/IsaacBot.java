package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.library.component.IComponent;
import org.firstinspires.ftc.teamcode.library.component.RobotComponent;
import org.firstinspires.ftc.teamcode.library.command.ICommand;
import org.firstinspires.ftc.teamcode.library.command.WaitCommand;
import org.firstinspires.ftc.teamcode.library.event.EventBus;
import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_b_press.Gp1_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_down_down.Gp1_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_left_down.Gp1_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_right_down.Gp1_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down.gp1_dpad_up_down.Gp1_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_left_press.Gp1_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_right_press.Gp1_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_x.Gp1_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y.Gp1_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_x.Gp1_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_right_stick_y.Gp1_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp1_x_press.Gp1_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_y_press.Gp1_Y_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_a_press.Gp2_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_b_press.Gp2_B_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_down_down.Gp2_Dpad_Down_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_left_down.Gp2_Dpad_Left_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_right_down.Gp2_Dpad_Right_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_down.gp2_dpad_up_down.Gp2_Dpad_Up_DownHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_down_press.Gp2_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_left_press.Gp2_Dpad_Left_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_right_press.Gp2_Dpad_Right_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_dpad_press.gp2_dpad_up_press.Gp2_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_bumper_press.Gp2_Left_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x.Gp2_LeftStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y.Gp2_LeftStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_bumper_press.Gp2_Right_Bumper_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_x.Gp2_RightStick_X_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_right_stick_y.Gp2_RightStick_Y_Handler;
import org.firstinspires.ftc.teamcode.library.event.gp2_x_press.Gp2_X_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressHandler;

/**
 *
 * @noinspection unused
 */
public abstract class  IsaacBot extends LinearOpMode implements IComponent
{
    /**
     */
    private static IsaacBot instance;

    /**
     *
     * @param command
     */
    public void addCommand (ICommand command) {
        this.robotComponent.addCommand(command);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_A_PressHandler (Gp1_A_PressHandler handler) {
        return this.robotComponent.addGp1_A_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_B_PressHandler (Gp1_B_PressHandler handler) {
        return this.robotComponent.addGp1_B_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_A_PressHandler (Gp2_A_PressHandler handler) {
        return this.robotComponent.addGp2_A_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_B_PressHandler (Gp2_B_PressHandler handler) {
        return this.robotComponent.addGp2_B_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_X_PressHandler (Gp2_X_PressHandler handler) {
        return this.robotComponent.addGp2_X_PressHandler(handler);
    }

    public HandlerRegistration addGp2_Y_PressHandler (Gp2_Y_PressHandler handler) {
        return this.robotComponent.addGp2_Y_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_X_PressHandler (Gp1_X_PressHandler handler) {
        return this.robotComponent.addGp1_X_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Y_PressHandler (Gp1_Y_PressHandler handler) {
        return this.robotComponent.addGp1_Y_PressHandler(handler);
    }


    //endregion
    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Left_Bumper_PressHandler (Gp2_Left_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Left_Bumper_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Right_Bumper_PressHandler (Gp2_Right_Bumper_PressHandler handler) {
        return this.robotComponent.addGp2_Right_Bumper_PressHandler(handler);
    }

    //-----------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Down_DownHandler (Gp1_Dpad_Down_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Down_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Up_DownHandler (Gp1_Dpad_Up_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Up_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Left_DownHandler (Gp1_Dpad_Left_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Left_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Right_DownHandler (Gp1_Dpad_Right_DownHandler handler) {
        return this.robotComponent.addGp1_Dpad_Right_DownHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Down_PressHandler (Gp1_Dpad_Down_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Down_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Up_PressHandler (Gp1_Dpad_Up_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Up_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Left_PressHandler (Gp1_Dpad_Left_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Left_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_Dpad_Right_PressHandler (Gp1_Dpad_Right_PressHandler handler) {
        return this.robotComponent.addGp1_Dpad_Right_PressHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Left_DownHandler (Gp2_Dpad_Left_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Left_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Right_DownHandler (Gp2_Dpad_Right_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Right_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Up_DownHandler (Gp2_Dpad_Up_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Up_DownHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Down_DownHandler (Gp2_Dpad_Down_DownHandler handler) {
        return this.robotComponent.addGp2_Dpad_Down_DownHandler(handler);
    }

    //-----------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Down_PressHandler (Gp2_Dpad_Down_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Down_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Up_PressHandler (Gp2_Dpad_Up_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Up_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Left_PressHandler (Gp2_Dpad_Left_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Left_PressHandler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_Dpad_Right_PressHandler (Gp2_Dpad_Right_PressHandler handler) {
        return this.robotComponent.addGp2_Dpad_Right_PressHandler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftStick_X_Handler (Gp1_LeftStick_X_Handler handler) {
        return this.robotComponent.addGp1_LeftStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_LeftStick_Y_Handler (Gp1_LeftStick_Y_Handler handler) {
        return this.robotComponent.addGp1_LeftStick_Y_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightStick_X_Handler (Gp1_RightStick_X_Handler handler) {
        return this.robotComponent.addGp1_RightStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp1_RightStickYHandler (Gp1_RightStick_Y_Handler handler) {
        return this.robotComponent.addGp1_RightStick_Y_Handler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStick_X_Handler (Gp2_LeftStick_X_Handler handler) {
        return this.robotComponent.addGp2_LeftStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_LeftStick_Y_Handler (Gp2_LeftStick_Y_Handler handler) {
        return this.robotComponent.addGp2_LeftStick_Y_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_RightStick_X_Handler (Gp2_RightStick_X_Handler handler) {
        return this.robotComponent.addGp2_RightStick_X_Handler(handler);
    }

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addGp2_RightStick_Y_Handler (Gp2_RightStick_Y_Handler handler) {
        return this.robotComponent.addGp2_RightStick_Y_Handler(handler);
    }

    //----------------------------------------------------------------------------------------------

    /**
     *
     * @return IsaacBot
     */
    public static IsaacBot getInstance() {
        return instance;
    }

    /**
     */
    protected final RobotComponent robotComponent;

    /**
     */
    private String imuName;

    /**
     */
    private IMU imu;

    /**
     * Constructor
     *
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
        return gyro.getYaw(AngleUnit.DEGREES);
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
     * This code gets ran right after the stop is pressed and is only ran once
     */
    public void onStop () {

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
     * @param imuName
     */
    public void setImuName (String imuName) {
        this.imuName = imuName;
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
