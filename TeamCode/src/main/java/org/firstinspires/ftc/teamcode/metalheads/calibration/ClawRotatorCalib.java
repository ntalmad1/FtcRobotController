package org.firstinspires.ftc.teamcode.metalheads.calibration;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_down_press.Gp1_Dpad_Down_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_dpad_press.gp1_dpad_up_press.Gp1_Dpad_Up_PressHandler;
import org.firstinspires.ftc.teamcode.library.rotator.Rotator;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 */
@TeleOp(name="ClawRotatorCalib", group="Calibration")
//@Disabled
public class ClawRotatorCalib extends IsaacBot {

    private Rotator servo;

    private RotatorConfig config;

    private double gamePadIncrement = 0.006;


    public ClawRotatorCalib() {
        super();

        config = new RotatorConfig(this);

        config.servoName = "clawRotator";

        config.maxIncrement = 0.006;

        config.minPosition = 0;
        config.maxPosition = 1;

        config.homePosition = 0.016;
        config.zeroDegreePosition = 0.5;

        config.controllerInputMethod = Control.Gp2_LeftStickX;

    }

    @Override
    public void initBot() {
        super.initBot();

        this.servo = new Rotator(config);
        this.servo.init();
    }

    /**
     *
     */
    @Override
    public void go() {


    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        telemetry.addData("Claw Rotator Pos: ", "%.3f", servo.getPosition());
        telemetry.update();
    }
}
