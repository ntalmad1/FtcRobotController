package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 * Tool to 'zero' the servos for the linear actuator arms. Also to
 * get the servo positions for set positions and limits.
 *
 */
@TeleOp(name="LinActsCalib", group="Calibration")
// @Disabled
public class LinActsCalib extends IsaacBot {

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
    private double dpadIncrement;

    /**
     * Constructor
     */
    public LinActsCalib(){
        super();

        leftServoName = "leftActServo";
        rightServoName = "rightActServo";
        stickIncrement = 0.0002;
        dpadIncrement = 0.002;

        leftActuatorServo = this.hardwareMap.get(Servo.class, leftServoName);
        leftActuatorServo.resetDeviceConfigurationForOpMode();

        rightActuatorServo = this.hardwareMap.get(Servo.class, rightServoName);
        rightActuatorServo.resetDeviceConfigurationForOpMode();

        this.addGp1_LeftStick_Y_Handler(event -> {
            moveServoByStick(leftActuatorServo, event.getPosition());
        });

        this.addGp1_RightStick_X_Handler(event -> {
            moveServoByStick(rightActuatorServo, event.getPosition());
        });

        this.addGp1_Dpad_Down_PressHandler(event -> {
            decrementServo(leftActuatorServo);
        });

        this.addGp1_Dpad_Up_PressHandler(event -> {
            incrementServo(leftActuatorServo);
        });

        this.addGp1_Dpad_Left_PressHandler(event -> {
            incrementServo(rightActuatorServo);
        });

        this.addGp1_Dpad_Right_PressHandler(event -> {
            decrementServo(rightActuatorServo);
        });
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

        telemetry.addData("Left servo pos:", "%.3f", leftActuatorServo.getPosition());
        telemetry.addData("Right servo pos:", "%.3f", rightActuatorServo.getPosition());
        telemetry.update();
    }

    /**
     *
     * @param stickPosition
     */
    private void moveServoByStick(Servo servo, double stickPosition) {
        double newPos = servo.getPosition() + (stickPosition > 0 ? -stickIncrement : stickIncrement);

        if (newPos < 0) {
            newPos = 0;
        }
        else if (newPos > 1) {
            newPos = 1;
        }

        servo.setPosition(newPos);
    }

    /**
     *
     * @param servo
     */
    private void incrementServo(Servo servo) {
        double newPosition = servo.getPosition() + this.dpadIncrement;

        if (newPosition > 1) {
            newPosition = 1;
        }

        servo.setPosition(newPosition);
    }

    /**
     *
     * @param servo
     */
    private void decrementServo(Servo servo) {
        double newPosition = servo.getPosition() - this.dpadIncrement;

        if (newPosition < 0) {
            newPosition = 0;
        }

        servo.setPosition(newPosition);
    }
}
