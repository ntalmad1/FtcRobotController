package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.utility.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.BoomConfig;
import org.firstinspires.ftc.library.claw.ClawConfig;
import org.firstinspires.ftc.library.rotator.RotatorConfig;

/**
 *
 */
public class ClawCompConfig extends ClawConfig {

    public ClawCompConfig(IsaacBot robot) {
        this.robot = robot;

        this.leftClawName = "rightClawServo";
        this.rightClawName = "leftClawServo";
        this.leftClawMinPosition = 0;
        this.leftClawMaxPosition = 0.35;
        this.rightClawMinPosition = 0;
        this.rightClawMaxPosition = 0.35;

        this.rightClawInitPosition = 0.0;
        this.leftClawInitPosition = 0.0;

        BoomConfig clawBoomConfig = new BoomConfig();
        clawBoomConfig.robot = robot;
        clawBoomConfig.servoName = "baseClawServo";
        clawBoomConfig.direction = Servo.Direction.FORWARD;
        clawBoomConfig.controllerInputMethod = Control.Gp2_RightStickY;
        clawBoomConfig.invertInput = true;
        clawBoomConfig.minPosition = 0.01;
        clawBoomConfig.maxPosition = 1;
        clawBoomConfig.zeroDegreePosition = 0.55;
        clawBoomConfig.homePosition = 0.01;
        clawBoomConfig.maxIncrement = 0.01;

        this.clawBoomConfig = clawBoomConfig;
    }

}
