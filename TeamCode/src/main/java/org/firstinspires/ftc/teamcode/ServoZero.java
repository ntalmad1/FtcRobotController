package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

@TeleOp(name="Servo Zero", group="Linear OpMode")
@Disabled
public class ServoZero extends IsaacBot {

    @Override
    public void runOpMode() throws InterruptedException {

        Servo servo = this.hardwareMap.get(Servo.class, "leftClawServo");

        servo.resetDeviceConfigurationForOpMode();

        double increment = 0.001;

        servo.setPosition(0);

        waitForStart();

        while (this.opModeIsActive()) {

            double rY = this.gamepad2.right_stick_y;

            if (rY != 0 ) {

                double newPos = servo.getPosition() + increment * rY;

                if (newPos >= 0 && newPos <= 1) {
                    servo.setPosition(newPos);
                }

            }

            telemetry.addData("servo position: ", "%2f", servo.getPosition());
            telemetry.update();

        }

    }
}
