package org.firstinspires.ftc.teamcode.metalheads;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;

@TeleOp(name="Servo Zero", group="Linear OpMode")
//@Disabled
public class ServoZero extends IsaacBot {

    @Override
    public void runOpMode() throws InterruptedException {

        double increment = 0.02;

        waitForStart();

        Servo servo = this.hardwareMap.get(Servo.class, "catcherWinchServo");
        servo.resetDeviceConfigurationForOpMode();

        servo.setDirection(Servo.Direction.FORWARD);
        servo.setPosition(1.0);
        double maxpos = 0.903;
        double minpos = 0.2966;

        boolean active = true;

        while (this.opModeIsActive()) {

            if (servo.getPosition()>0) {
                if (gamepad1.dpad_down == true) {
                    servo.setPosition(servo.getPosition()-increment);
                    while (gamepad1.dpad_down == true) {}
                }
            }
            if (servo.getPosition()<1) {
                if (gamepad1.dpad_up == true) {
                    servo.setPosition(servo.getPosition()+increment);
                    while (gamepad1.dpad_up == true) {}
                }
            }

            if (this.gamepad1.left_stick_x != 0) {

                double lx = this.gamepad1.left_stick_x;

                double newPos = servo.getPosition() + (lx * 0.002);

                if (newPos < minpos) newPos = minpos;
                if (newPos > maxpos) newPos = maxpos;

                servo.setPosition(newPos);
            }

            telemetry.addData("servo: ", "%2f", servo.getPosition());
            telemetry.update();
        }
    }
}
