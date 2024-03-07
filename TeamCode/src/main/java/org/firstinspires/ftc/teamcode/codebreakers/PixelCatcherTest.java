package org.firstinspires.ftc.teamcode.codebreakers;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "PixelCatcherTest_", group = "Robot")
@Disabled
public class PixelCatcherTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Servo leftPixelServo = this.hardwareMap.get(Servo.class, "leftPixelServo");
        Servo rightPixelServo = this.hardwareMap.get(Servo.class, "rightPixelServo");

        leftPixelServo.resetDeviceConfigurationForOpMode();
        rightPixelServo.resetDeviceConfigurationForOpMode();

        this.waitForStart();

        leftPixelServo.setPosition(0.5);
        rightPixelServo.setPosition(0.5);

        while(this.opModeIsActive()) {

           if (this.gamepad1.left_stick_x != 0) {

               double newPos = leftPixelServo.getPosition() + (this.gamepad1.left_stick_x > 0 ? 0.001 : -0.001);

               if (newPos > 1) newPos = 1;
               if (newPos < 0) newPos = 0;

               leftPixelServo.setPosition(newPos);
           }

            if (this.gamepad1.right_stick_x != 0) {

                double newPos = rightPixelServo.getPosition() + (this.gamepad1.right_stick_x > 0 ? 0.001 : -0.001);

                if (newPos > 1) newPos = 1;
                if (newPos < 0) newPos = 0;

                rightPixelServo.setPosition(newPos);
            }

            this.telemetry.addData("Left pixel servo pos: ", "%2f", leftPixelServo.getPosition());
            this.telemetry.addData("Right pixel servo pos: ", "%2f", rightPixelServo.getPosition());
            this.telemetry.update();

        }

    }
}
