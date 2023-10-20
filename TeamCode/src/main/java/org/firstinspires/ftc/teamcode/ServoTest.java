package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Servo Test", group="Linear OpMode")
//@Disabled
public class ServoTest extends IsaacBot {

    /**
     * Top 90 "0 degrees" 0.586
     * Middle 90  "0 degrees" 0.575
     * Bottom 90  "0 degrees" 0.29
     */

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        Servo topServo = this.hardwareMap.get(Servo.class, "topServo");
        topServo.resetDeviceConfigurationForOpMode();
        topServo.setPosition(0.5);

        while (this.opModeIsActive()) {

            telemetry.addData("top servo position: ", "%2f", topServo.getPosition());
            telemetry.update();

        }
    }
}
