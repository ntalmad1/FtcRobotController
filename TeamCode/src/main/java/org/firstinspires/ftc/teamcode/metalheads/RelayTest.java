package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="RelayTest", group="Linear OpMode")
//@Disabled
public class RelayTest extends LinearOpMode
{
    @Override
    public void runOpMode() throws InterruptedException {

        Servo relaySwitch = this.hardwareMap.get(Servo.class, "relay");

        Servo servo = this.hardwareMap.get(Servo.class, "servo");
        servo.resetDeviceConfigurationForOpMode();

        waitForStart();

        int relayPos = 0;
        relaySwitch.setPosition(0);

        int servoPos = 0;
        servo.setPosition(0);

        while (this.opModeIsActive()) {

            if (gamepad1.x) {

                if (servoPos == 0) {
                    servoPos = 1;
                }
                else if (servoPos == 1) {
                    servoPos = 0;
                }

                servo.setPosition(servoPos);
            }

            if (gamepad1.y) {

                if (relayPos == 0) {
                    relayPos = 1;
                }
                else if (relayPos == 1) {
                    relayPos = 0;
                }

                relaySwitch.setPosition(relayPos);
            }


        }

    }
}
