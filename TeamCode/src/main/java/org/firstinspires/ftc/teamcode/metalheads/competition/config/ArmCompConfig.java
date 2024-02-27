package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.motor.EncodedMotorConfig;
import org.firstinspires.ftc.library.utility.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.arm.ArmConfig;
import org.firstinspires.ftc.library.boom.BoomConfig;

public class ArmCompConfig extends ArmConfig {

    public ArmCompConfig (IsaacBot robot) {
        this.robot = robot;

        EncodedMotorConfig encodedMotorConfig = new EncodedMotorConfig(robot);
        encodedMotorConfig.motorName = "actuatorMotor";
        encodedMotorConfig.minTics = 0;
        encodedMotorConfig.maxTics = 3400;
        encodedMotorConfig.increment = 200;
        encodedMotorConfig.debug = false;
        encodedMotorConfig.brakeOn = false;
        encodedMotorConfig.control = Control.Gp2_RightStickX;

        BoomConfig bottomBoomConfig = new BoomConfig();
        bottomBoomConfig.robot = robot;
        bottomBoomConfig.isDualServo = true;
        bottomBoomConfig.servoName = "bottomLeftServo";
        bottomBoomConfig.secondaryServoName = "bottomRightServo";
        bottomBoomConfig.direction = Servo.Direction.FORWARD;
        bottomBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        bottomBoomConfig.invertInput = true;
        bottomBoomConfig.maxIncrement = 0.0005;
        bottomBoomConfig.zeroDegreePosition = 0.5;
        bottomBoomConfig.degree = 0.000556;
        bottomBoomConfig.gearRatio = 5;
        bottomBoomConfig.homePosition = 0.24;

        this.clawConfig = new ClawCompConfig(robot);
        this.linActConfig = encodedMotorConfig;
        this.bottomBoomConfig = bottomBoomConfig;
    }
}
