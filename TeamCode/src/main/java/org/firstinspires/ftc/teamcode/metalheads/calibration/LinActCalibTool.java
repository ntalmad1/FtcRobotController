package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 * Tool to 'zero' the servos for the linear actuator arms. Also to
 * get the servo positions for set positions and limits.
 *
 */
@TeleOp(name="LinActCalibTool", group="Calibration")
// @Disabled
public class LinActCalibTool extends IsaacBot {

    /**
     */
    private Servo leftActuatorServo;

    /**
     */
    private String leftServoName;

    /**
     */
    private Servo rightActuatorServo;

    /**
     */
    private String rightServoName;

    /**
     */
    private double stickIncrement;

    /**
     */
    private double gamePadIncrement;

    /**
     * Constructor
     */
    public LinActCalibTool(){
        super();

        leftServoName = "leftActServo";
        rightServoName = "rightActServo";
        stickIncrement = 0.0002;
        gamePadIncrement = 0.002;

        leftActuatorServo = this.hardwareMap.get(Servo.class, leftServoName);
        leftActuatorServo.resetDeviceConfigurationForOpMode();

        rightActuatorServo = this.hardwareMap.get(Servo.class, rightServoName);
        rightActuatorServo.resetDeviceConfigurationForOpMode();

        this.addGp1_LeftStick_Y_Handler();

        this.addGp1_RightStick_X_Handler();

        this.addGp1_Dpad_Down_PressHandler();

        this.addGp1_Dpad_Up_PressHandler();

        this.addGp1_Dpad_Left_PressHandler();

        this.addGp1_Dpad_Right_PressHandler();
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();
    }

    /**
     *
     */
    @Override
    public void run() {
       super.run();
    }

    /**
     *
     * @param servo
     */
    private void DpadIncrement (Servo servo) {
            
    }

}
