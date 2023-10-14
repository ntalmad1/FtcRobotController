package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Claw;
import org.firstinspires.ftc.teamcode.library.arm.ClawConfig;
import org.firstinspires.ftc.teamcode.library.arm.boom.BoomConfiguration;
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
        clawBoomConfig.isDualServo = false;
        clawBoomConfig.direction = Servo.Direction.FORWARD;
        clawBoomConfig.controllerInputMethod = Control.Gp2_Dpad_Up;
        clawBoomConfig.controllerInputMethod2 = Control.Gp2_Dpad_Down;
        clawBoomConfig.invertInput = false;
        clawBoomConfig.minPosition = 0;
        clawBoomConfig.maxPosition = 1;
        clawBoomConfig.zeroDegreePosition = .5;
        clawBoomConfig.homePosition = 0.5;
        clawBoomConfig.maxIncrement = 0.001;
        clawBoomConfig.degree = 0.0033;

        clawConfig.clawBoomConfig = clawBoomConfig;
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
