package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Servo Test", group="Linear OpMode")
@Disabled
public class ServoTest extends IsaacBot {


    @Override
    public void runOpMode() throws InterruptedException {


        Servo servo = this.hardwareMap.get(Servo.class, "droneTrigger");
        servo.resetDeviceConfigurationForOpMode();

        waitForStart();

        servo.setPosition(0.0);

        while (this.opModeIsActive()) {

            if (this.gamepad1.left_stick_x != 0) {

                double lx = this.gamepad1.left_stick_x;

                double newPos = servo.getPosition() + (lx > 0 ? 0.001 : -0.001);

                if (newPos < 0) newPos = 0;
                if (newPos > 1) newPos = 1;

                servo.setPosition(newPos);
            }

            telemetry.addData("Servo position: ", "%2f", servo.getPosition());
            telemetry.update();

        }
    }
}
