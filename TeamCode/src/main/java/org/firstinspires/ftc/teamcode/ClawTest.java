package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.claw.Claw;
import org.firstinspires.ftc.teamcode.library.claw.ClawConfig;
import org.firstinspires.ftc.teamcode.library.boom.BoomConfiguration;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfiguration;
import org.firstinspires.ftc.teamcode.library.component.event.EventBus;

@TeleOp(name="ClawTest", group="Linear OpMode")
//@Disabled
public class ClawTest extends IsaacBot {

    private Claw claw;

    public ClawTest() {
        ClawConfig clawConfig = new ClawConfig();
        clawConfig.robot = this;

        BoomConfiguration clawBoomConfig = new BoomConfiguration();
        clawBoomConfig.robot = this;
        clawBoomConfig.servoName = "baseClawServo";
        clawBoomConfig.direction = Servo.Direction.FORWARD;
        clawBoomConfig.controllerInputMethod = Control.Gp2_Dpad_Up;
        clawBoomConfig.controllerInputMethod2 = Control.Gp2_Dpad_Down;
        clawBoomConfig.invertInput = true;
        clawBoomConfig.minPosition = 0;
        clawBoomConfig.maxPosition = 1;
        clawBoomConfig.zeroDegreePosition = .5;
        clawBoomConfig.homePosition = 0.5;
        clawBoomConfig.maxIncrement = 0.001;

        RotatorConfiguration clawRotatorConfig = new RotatorConfiguration();
        clawRotatorConfig.robot = this;
        clawRotatorConfig.servoName = "rotateClawServo";
        clawRotatorConfig.direction = Servo.Direction.FORWARD;
        clawRotatorConfig.controllerInputMethod = Control.Gp2_Dpad_Left;
        clawRotatorConfig.controllerInputMethod2 = Control.Gp2_Dpad_Right;
        clawRotatorConfig.invertInput = true;
        clawRotatorConfig.minPosition = 0;
        clawRotatorConfig.maxPosition = 1;
        clawRotatorConfig.zeroDegreePosition = .5;
        clawRotatorConfig.homePosition = 0.5;
        clawRotatorConfig.maxIncrement = 0.001;
        clawRotatorConfig.debug = true;

        clawConfig.clawBoomConfig = clawBoomConfig;
        clawConfig.clawRotatorConfig = clawRotatorConfig;

        this.claw = new Claw(clawConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        this.claw.init();

        this.waitForStart();

        while (this.opModeIsActive()) {
            EventBus.getInstance().run();
            this.claw.run();
        }

    }
}
