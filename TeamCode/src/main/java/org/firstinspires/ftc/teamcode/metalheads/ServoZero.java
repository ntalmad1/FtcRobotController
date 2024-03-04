package org.firstinspires.ftc.teamcode.metalheads;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;

@TeleOp(name="Servo Zero", group="Linear OpMode")
//@Disabled
public class ServoZero extends IsaacBot {

    @Override
    public void runOpMode() throws InterruptedException {

        double bigIncrement = 0.05;
        double smallIncrement = 0.01;

        waitForStart();

//        Servo rightRelay = this.hardwareMap.get(Servo.class, "rightBoomRelay");
//        Servo leftRelay = this.hardwareMap.get(Servo.class, "leftBoomRelay");

//        rightRelay.setPosition(1);
//        leftRelay.setPosition(1);

        Servo servo = this.hardwareMap.get(Servo.class, "catcherWinchServo");
        servo.resetDeviceConfigurationForOpMode();

        servo.setDirection(Servo.Direction.FORWARD);
        servo.setPosition(0.949);

//        Servo servo2 = this.hardwareMap.get(Servo.class, "bottomLeftServo");
//        servo2.resetDeviceConfigurationForOpMode();
//
//        servo2.setDirection(Servo.Direction.FORWARD);
//        servo2.setPosition(0.5);

        double maxpos = 1.0;
        double minpos = 0.0;

        while (this.opModeIsActive()) {

            if (gamepad1.left_bumper) {
                minpos = servo.getPosition();
            }
            if (gamepad1.right_bumper) {
                maxpos = servo.getPosition();
            }

            if (servo.getPosition()>0) {
                if (gamepad1.dpad_down == true) {
                    servo.setPosition(servo.getPosition()-bigIncrement);
                    while (gamepad1.dpad_down == true) {}
                }
            }
            if (servo.getPosition()<1) {
                if (gamepad1.dpad_up == true) {
                    servo.setPosition(servo.getPosition()+bigIncrement);
                    while (gamepad1.dpad_up == true) {}
                }
            }

            if (servo.getPosition()>0) {
                if (gamepad1.dpad_left == true) {
                    servo.setPosition(servo.getPosition()-smallIncrement);
                    while (gamepad1.dpad_left == true) {}
                }
            }
            if (servo.getPosition()<1) {
                if (gamepad1.dpad_right == true) {
                    servo.setPosition(servo.getPosition()+smallIncrement);
                    while (gamepad1.dpad_right == true) {}
                }
            }

            if (this.gamepad1.left_stick_x != 0) {

                double lx = this.gamepad1.left_stick_x;

                double newPos = servo.getPosition() + (lx * 0.002);

                if (newPos < minpos) newPos = minpos;
                if (newPos > maxpos) newPos = maxpos;

                servo.setPosition(newPos);
            }

            if (gamepad1.a) {
                servo.setPosition(maxpos);
            }
            if (gamepad1.b) {
                servo.setPosition(minpos);
            }

            telemetry.addData("servo: ", "%2f", servo.getPosition());
            telemetry.addData("Max Position ", "%2f", maxpos);
            telemetry.addData("Min Position ", "%2f", minpos);
            telemetry.update();
        }
    }
}
