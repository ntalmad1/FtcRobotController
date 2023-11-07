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

        waitForStart();

        Servo left = this.hardwareMap.get(Servo.class, "middleLeftServo");
        left.resetDeviceConfigurationForOpMode();

        Servo right = this.hardwareMap.get(Servo.class, "middleRightServo");
        right.resetDeviceConfigurationForOpMode();

        left.setDirection(Servo.Direction.REVERSE);
        right.setDirection(Servo.Direction.REVERSE);

        left.setPosition(0.48);
        right.setPosition((double)1 - 0.48);

            telemetry.addData("servo left: ", "%2f", left.getPosition());
            telemetry.addData("servo right: ", "%2f", right.getPosition());
            telemetry.update();

        while (this.opModeIsActive()) {

        }
    }
}
