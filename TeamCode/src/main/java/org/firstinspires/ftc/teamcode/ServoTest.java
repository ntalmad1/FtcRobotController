package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Servo Test", group="Linear OpMode")
@Disabled
public class ServoTest extends IsaacBot {

    /**
     * Top 90 "0 degrees" 0.586
     * Middle 90  "0 degrees" 0.575
     * Bottom 90  "0 degrees" 0.29
     */

    @Override
    public void runOpMode() throws InterruptedException {
//        DigitalChannel led1Green = this.hardwareMap.get(DigitalChannel.class, "ledOneGreen");
//        DigitalChannel led1Red = this.hardwareMap.get(DigitalChannel.class, "ledOneRed");
//
//        led1Green.setMode(DigitalChannel.Mode.OUTPUT);
//        led1Red.setMode(DigitalChannel.Mode.OUTPUT);
//
//        led1Green.setState(true);
//        led1Red.setState(true);

        Servo servo = this.hardwareMap.get(Servo.class, "testServo");
//        Servo leftClawServo = this.hardwareMap.get(Servo.class, "leftClawServo");
        servo.resetDeviceConfigurationForOpMode();
//        leftClawServo.resetDeviceConfigurationForOpMode();
//        leftClawServo.setDirection(Servo.Direction.REVERSE);
//
//        rightClawServo.setPosition(0.5);
//        leftClawServo.setPosition(0.0);

        waitForStart();

        servo.setPosition(0.0);
//        sleep(250);

        while (this.opModeIsActive()) {
//            leftClawServo.setPosition(0.0);
//
//            led1Green.setState(true);
//            led1Red.setState(false);
//
//            this.telemetry.addLine("green");
//            this.telemetry.update();
//
//            sleep(1000);
//
//            led1Green.setState(false);
//            led1Red.setState(true);
//
//            this.telemetry.addLine("red");
//            this.telemetry.update();
//
//            sleep(1000);
//            led1Green.setState(false);
//            led1Red.setState(false);
//
//
//            this.telemetry.addLine("amber");
//            this.telemetry.update();
//
//            sleep(1000);
//
//            led1Green.setState(true);
//            led1Red.setState(true);
//
//            this.telemetry.addLine("off");
//            this.telemetry.update();
//
//            sleep(1000);



//
//
//
//            telemetry.addData("right claw servo position: ", "%2f", rightClawServo.getPosition());
//            telemetry.addData("left claw servo position: ", "%2f", leftClawServo.getPosition());
//            telemetry.update();



        }
    }
}
