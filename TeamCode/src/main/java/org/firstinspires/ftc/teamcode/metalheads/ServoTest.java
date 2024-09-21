package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;

/**
 *
 */
@TeleOp(name="ServoTest", group="Tests")
//@Disabled
public class ServoTest extends IsaacBot {

    /**
     */
    private double yStickIncrement = 0.0002;

    /**
     */
    private double gamePadIncrement = 0.002;

    /**
     */
    private String servoName = "leftActServo";

    /**
     */
    private Servo servo;

    @Override
    public void initBot() {
        servo = this.hardwareMap.get(Servo.class, servoName);
        servo.resetDeviceConfigurationForOpMode();

        this.addGp1_Dpad_Down_PressHandler(new Gp1_Dpad_Down_PressHandler() {
            public void onGp1_Dpad_Down_Press(Gp1_Dpad_Down_PressEvent event) {
                double newPos = servo.getPosition() - gamePadIncrement;

                if (newPos < 0) newPos = 0;
                if (newPos > 1) newPos = 1;

                servo.setPosition(newPos);
            }
        });

        this.addGp1_Dpad_Up_PressHandler(new Gp1_Dpad_Up_PressHandler() {
            public void onGp1_Dpad_Up_Press(Gp1_Dpad_Up_PressEvent event) {
                double newPos = servo.getPosition() + gamePadIncrement;

                if (newPos < 0) newPos = 0;
                if (newPos > 1) newPos = 1;

                servo.setPosition(newPos);
            }
        });
    }

    /**
     *
     */
    @Override
    public void go() {
        servo.setPosition(0);
    }

    /**
     *
     */
    @Override
    public void run() {

        if (this.gamepad1.left_stick_y != 0) {

            double ly = this.gamepad1.left_stick_y;

            double newPos = servo.getPosition() + (ly > 0 ? -yStickIncrement : yStickIncrement);

            if (newPos < 0) newPos = 0;
            if (newPos > 1) newPos = 1;

            servo.setPosition(newPos);
        }

        telemetry.addData("Servo position: ", "%.3f", servo.getPosition());
        telemetry.update();
    }
}
