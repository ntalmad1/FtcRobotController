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

        double increment = 0.01;

        waitForStart();

        Servo launcher = this.hardwareMap.get(Servo.class, "droneBase");
        launcher.resetDeviceConfigurationForOpMode();

        launcher.setDirection(Servo.Direction.FORWARD);
        launcher.setPosition(0.5);

        while (this.opModeIsActive()) {

            if (launcher.getPosition()>0) {
                if (gamepad1.dpad_down == true) {
                    launcher.setPosition(launcher.getPosition()-increment);
                    while (gamepad1.dpad_down == true) {}
                }
            }
            if (launcher.getPosition()<1) {
                if (gamepad1.dpad_up == true) {
                    launcher.setPosition(launcher.getPosition()+increment);
                    while (gamepad1.dpad_up == true) {}
                }
            }

            telemetry.addData("servo: ", "%2f", launcher.getPosition());
            telemetry.update();
        }
    }
}
