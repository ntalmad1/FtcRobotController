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

        Servo bottomLeftServo = this.hardwareMap.get(Servo.class, "bottomLeftServo");
        Servo bottomRightServo = this.hardwareMap.get(Servo.class, "bottomRightServo");
        //Servo middleServo = this.hardwareMap.get(Servo.class, "middleServo");
        //Servo topServo = this.hardwareMap.get(Servo.class, "topServo");

        waitForStart();

        bottomLeftServo.resetDeviceConfigurationForOpMode();
        bottomRightServo.resetDeviceConfigurationForOpMode();


        //topServo.resetDeviceConfigurationForOpMode();

        bottomLeftServo.setDirection(Servo.Direction.REVERSE);
        bottomRightServo.setDirection(Servo.Direction.REVERSE);

        while (this.opModeIsActive()) {

            // bottom right servo position one would be arm across towards the front


//            bottomLeftServo.setPosition(0.0);
//            bottomRightServo.setPosition(1.0);
//
//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.1);
//            bottomRightServo.setPosition(0.9);
//
//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.2);
//            bottomRightServo.setPosition(0.8);
//
//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.3);
//            bottomRightServo.setPosition(0.7);

            sleep(1000);

            bottomLeftServo.setPosition(0.4);
            bottomRightServo.setPosition(0.6);

//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.5);
//            bottomRightServo.setPosition(0.5);
//
//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.6);
//            bottomRightServo.setPosition(0.4);

          

//            bottomServo.setPosition(0.4);
//
//            sleep(1000);
//
//            middleServo.setPosition(0.4);
//
//            sleep(1000);
//
//            topServo.setPosition(0.4);
//
//            sleep(5000);
//
//            this.telemetry.addData("Bottom position: ", "%2f", bottomServo.getPosition());
//            this.telemetry.addData("Middle position: ", "%2f", middleServo.getPosition());
//            this.telemetry.addData("Top position: ", "%2f", topServo.getPosition());
//            this.telemetry.update();
        }
    }
}
