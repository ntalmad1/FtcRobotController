package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.arm.ArmConfig;
import org.firstinspires.ftc.library.boom.BoomConfig;

public class ArmCompConfig extends ArmConfig {

    public ArmCompConfig (IsaacBot robot) {
        this.robot = robot;

        BoomConfig midBoomConfig = new BoomConfig();
        midBoomConfig.robot = robot;
        midBoomConfig.isDualServo = true;
        midBoomConfig.servoName = "middleLeftServo";
        midBoomConfig.secondaryServoName = "middleRightServo";
        midBoomConfig.direction = Servo.Direction.REVERSE;
        midBoomConfig.controllerInputMethod = Control.Gp2_RightStickX;
        midBoomConfig.invertInput = false;
        midBoomConfig.maxIncrement = 0.001;
        midBoomConfig.degree = 0.000556;
        midBoomConfig.zeroDegreePosition = 0.48;
        midBoomConfig.gearRatio = 5;

        BoomConfig bottomBoomConfig = new BoomConfig();
        bottomBoomConfig.robot = robot;
        bottomBoomConfig.isDualServo = true;
        bottomBoomConfig.servoName = "bottomLeftServo";
        bottomBoomConfig.secondaryServoName = "bottomRightServo";
        bottomBoomConfig.direction = Servo.Direction.FORWARD;
        bottomBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        bottomBoomConfig.invertInput = true;
        bottomBoomConfig.maxIncrement = 0.001;
        bottomBoomConfig.zeroDegreePosition = 0.28;
        bottomBoomConfig.degree = 0.000556;
        bottomBoomConfig.gearRatio = 5;

        this.clawConfig = new ClawCompConfig(robot);
        this.midBoomConfig = midBoomConfig;
        this.bottomBoomConfig = bottomBoomConfig;
    }
}
