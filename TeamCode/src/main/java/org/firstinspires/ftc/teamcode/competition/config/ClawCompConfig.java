package org.firstinspires.ftc.teamcode.competition.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.library.Control;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.boom.BoomConfig;
import org.firstinspires.ftc.teamcode.library.claw.ClawConfig;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfiguration;

/**
 *
 */
public class ClawCompConfig extends ClawConfig {

    public ClawCompConfig(IsaacBot robot) {
        this.robot = robot;

        this.leftClawName = "leftClawServo";
        this.rightClawName = "rightClawServo";
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
        clawBoomConfig.minPosition = 0;
        clawBoomConfig.maxPosition = 1;
        clawBoomConfig.zeroDegreePosition = .5;
        //clawBoomConfig.homePosition = 1;
        clawBoomConfig.homePosition = 0.85;
        clawBoomConfig.maxIncrement = 0.003;

        RotatorConfiguration clawRotatorConfig = new RotatorConfiguration();
        clawRotatorConfig.robot = robot;
        clawRotatorConfig.servoName = "rotateClawServo";
        clawRotatorConfig.direction = Servo.Direction.FORWARD;
        clawRotatorConfig.controllerInputMethod = Control.Gp2_Dpad_Left;
        clawRotatorConfig.controllerInputMethod2 = Control.Gp2_Dpad_Right;
        clawRotatorConfig.invertInput = true;
        clawRotatorConfig.minPosition = 0.00;
        clawRotatorConfig.maxPosition = 1.00;
        clawRotatorConfig.zeroDegreePosition = 0.30;
        clawRotatorConfig.homePosition = 0.30;
        clawRotatorConfig.maxIncrement = 0.0015;

        this.clawBoomConfig = clawBoomConfig;
        this.clawRotatorConfig = clawRotatorConfig;
    }

}
