package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Servo Test", group="Linear OpMode")
//@Disabled
public class ServoTest extends IsaacBot {

    @Override
    public void runOpMode() throws InterruptedException {

        Servo bottomServo = this.hardwareMap.get(Servo.class, "bottomServo");
        Servo middleServo = this.hardwareMap.get(Servo.class, "middleServo");
        Servo topServo = this.hardwareMap.get(Servo.class, "topServo");

        waitForStart();

        bottomServo.resetDeviceConfigurationForOpMode();
        middleServo.resetDeviceConfigurationForOpMode();
        topServo.resetDeviceConfigurationForOpMode();

        // topServo.setDirection(Servo.Direction.REVERSE);

        while (this.opModeIsActive()) {

            bottomServo.setPosition(0.4);

            sleep(1000);

            middleServo.setPosition(0.4);

            sleep(1000);

            topServo.setPosition(0.4);

            sleep(5000);

            this.telemetry.addData("Bottom position: ", "%2f", bottomServo.getPosition());
            this.telemetry.addData("Middle position: ", "%2f", middleServo.getPosition());
            this.telemetry.addData("Top position: ", "%2f", topServo.getPosition());
            this.telemetry.update();
        }
    }
}
