package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 *
 */
@TeleOp(name="ServoTest", group="Tests")
//@Disabled
public class ServoTest extends LinearOpMode {

    /**
     */
    private double yStickIncrement = 0.0002;

    /**
     */
    private double gamePadIncrement = 0.002;


    /**
     *
     * @throws InterruptedException
     */
    @Override
    public void runOpMode() throws InterruptedException {

        String servoName = "servoName";

        Servo servo = this.hardwareMap.get(Servo.class, servoName);
        servo.resetDeviceConfigurationForOpMode();

        this.waitForStart();

        servo.setPosition(0);

        double newPos = 0;

        while (this.opModeIsActive()) {

            if (this.gamepad1.left_stick_y != 0) {

                double ly = this.gamepad1.left_stick_y;

                newPos = servo.getPosition() + (ly > 0 ? yStickIncrement : -yStickIncrement);

                servo.setPosition(newPos);
            }
            else if (this.gamepad1.dpad_up) {
                newPos += gamePadIncrement;
            }
            else if (this.gamepad1.dpad_down) {
                newPos -= gamePadIncrement;
            }

            if (newPos < 0) newPos = 0;
            if (newPos > 1) newPos = 1;

            telemetry.addData("Servo position: ", "%2f", servo.getPosition());
            telemetry.update();
       }
    }
}
