package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.claw.Claw;
import org.firstinspires.ftc.library.claw.ClawConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.boom.BoomConfig;
import org.firstinspires.ftc.library.rotator.RotatorConfig;
import org.firstinspires.ftc.library.component.event.EventBus;

@TeleOp(name="ClawTest", group="Linear OpMode")
@Disabled
public class ClawTest extends IsaacBot {

    private Claw claw;

    public ClawTest() {
        ClawConfig clawConfig = new ClawConfig();
        clawConfig.robot = this;
        clawConfig.leftClawName = "leftClawServo";
        clawConfig.rightClawName = "rightClawServo";
        clawConfig.leftClawMinPosition = 0;
        clawConfig.leftClawMaxPosition = 0.35;
        clawConfig.rightClawMinPosition = 0;
        clawConfig.rightClawMaxPosition = 0.35;

        BoomConfig clawBoomConfig = new BoomConfig();
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

        RotatorConfig clawRotatorConfig = new RotatorConfig();
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
