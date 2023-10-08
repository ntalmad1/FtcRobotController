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

    /**
     * Top 90 "0 degrees" 0.586
     * Middle 90  "0 degrees" 0.575
     * Bottom 90  "0 degrees" 0.0.29
     */

    @Override
    public void runOpMode() throws InterruptedException {

        Servo bottomLeftServo = this.hardwareMap.get(Servo.class, "bottomLeftServo");
        Servo bottomRightServo = this.hardwareMap.get(Servo.class, "bottomRightServo");
        Servo middleServo = this.hardwareMap.get(Servo.class, "middleServo");
        Servo topServo = this.hardwareMap.get(Servo.class, "topServo");

        waitForStart();

        bottomLeftServo.resetDeviceConfigurationForOpMode();
        bottomRightServo.resetDeviceConfigurationForOpMode();
        middleServo.resetDeviceConfigurationForOpMode();
        topServo.resetDeviceConfigurationForOpMode();

        bottomLeftServo.setDirection(Servo.Direction.REVERSE);
        bottomRightServo.setDirection(Servo.Direction.REVERSE);
        middleServo.setDirection(Servo.Direction.REVERSE);

//
//        sleep(1000);

        middleServo.setPosition(0.0);
        topServo.setPosition(0.0);
        bottomLeftServo.setPosition(0.00);
        bottomRightServo.setPosition(1.0);

        double incrementAmount = 0.001;
        double middleIncrementAmount = 0.0005;

        while (this.opModeIsActive()) {

            //sleep(3000);
            double rY = gamepad2.right_stick_y;
            double rX = gamepad2.right_stick_x;

            double lX = -gamepad2.left_stick_x;
            double lY = gamepad2.left_stick_y;

            if (rY > 0)
            {
                double newPos = topServo.getPosition() + (incrementAmount*rY);

                if (newPos > 1) {
                    newPos = 1;
                }

                topServo.setPosition(newPos);
            }
            else if (rY < 0)
            {
                double newPos = topServo.getPosition() + (incrementAmount*rY);

                if (newPos < 0) {
                    newPos = 0;
                }

                topServo.setPosition(newPos);
            }
            //---------------------------------------------------------------
            if (rX > 0)
            {
                double newPos = middleServo.getPosition() + (middleIncrementAmount * rX);

                if (newPos > 1) {
                    newPos = 1;
                }

                middleServo.setPosition(newPos);
            }
            else if (rX < 0)
            {
                double newPos = middleServo.getPosition() + (middleIncrementAmount * rX);

                if (newPos < 0) {
                    newPos = 0;
                }

                middleServo.setPosition(newPos);
            }

            //--------------------------------------------------------------------

            if (lX > 0)
            {
                double newPos = bottomLeftServo.getPosition() + (incrementAmount*lX);

                if (newPos > 1) {
                    newPos = 1;
                }

                bottomLeftServo.setPosition(newPos);
                bottomRightServo.setPosition(1 - newPos);
            }
            else if (lX < 0)
            {
                double newPos = bottomLeftServo.getPosition() + (incrementAmount*lX);

                if (newPos < 0) {
                    newPos = 0;
                }

                bottomLeftServo.setPosition(newPos);
                bottomRightServo.setPosition(1 - newPos);
            }

            this.telemetry.addData("Top position: ", "%2f", topServo.getPosition());
            this.telemetry.addData("Middle position: ", "%2f", middleServo.getPosition());
            this.telemetry.addData("Bottom left position: ", "%2f", bottomLeftServo.getPosition());
            this.telemetry.addData("Bottom right position: ", "%2f", bottomRightServo.getPosition());

            this.telemetry.addData("Right stick Y: ", "%2f", rY);
            this.telemetry.addData("Left stick X: ", "%2f", lX);
            this.telemetry.addData("Left stick Y: ", "%2f", lY);

            this.telemetry.update();

//            middleServo.setPosition(0.2);
//            bottomLeftServo.setPosition(0.25);
//            bottomRightServo.setPosition(1.0 - 0.25);
//
//
//            sleep(1000);
////
//            middleServo.setPosition(1);
////
//            sleep(3000);
////
//            middleServo.setPosition(0.2);
//            bottomLeftServo.setPosition(0.05);
//            bottomRightServo.setPosition(1.0-0.05);
////
//            sleep(1000);
////
//            middleServo.setPosition(0.05);
////
//            sleep(3000);

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

//            sleep(1000);
//
//            bottomLeftServo.setPosition(0.4);
//            bottomRightServo.setPosition(0.6);

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
