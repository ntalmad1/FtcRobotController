package org.firstinspires.ftc.teamcode.archive.competition.config;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;

/**
 *
 */
public class PixelCatcherCompConfig extends PixelCatcherConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public PixelCatcherCompConfig (IsaacBot robot) {
        this.robot = robot;

        ServoComponentConfig leftServoConfig = new ServoComponentConfig(robot);
        leftServoConfig.servoName = "leftCatcherServo";
        leftServoConfig.maxIncrement = 0.02;
        leftServoConfig.maxPosition = 1;
        leftServoConfig.minPosition = 0;
        leftServoConfig.homePosition = 1;

        this.leftArmServoConfig = leftServoConfig;

        this.leftArmServoOpenedPos = 0;

        this.leftArmServoClosedPos = 1;

        this.leftArmServoInitPos = 1;

        this.leftArmInitPos = PixelCatcher.ArmPosition.CLOSED;

        this.leftArmToggle = Control.Gp1_LeftTrigger_Down;

        //--------------------------------------------------

        ServoComponentConfig rightServoConfig = new ServoComponentConfig(robot);
        rightServoConfig.servoName = "rightCatcherServo";
        rightServoConfig.maxIncrement = 0.02;
        rightServoConfig.maxPosition = 1;
        rightServoConfig.minPosition = 0;
        rightServoConfig.homePosition = 1;

        this.rightArmServoConfig = rightServoConfig;

        this.rightArmServoOpenedPos = 0;

        this.rightArmServoClosedPos = 1;

        this.rightArmServoInitPos = 1;

        this.rightArmInitPos = PixelCatcher.ArmPosition.CLOSED;

        this.rightArmToggle = Control.Gp1_RightTrigger_Down;

        //--------------------------------------------------

        this.winchServoName = "catcherWinchServo";

        this.winchToggle1 = Control.Gp1_RightBumper_Down;

        this.winchToggle2 = Control.Gp1_LeftBumper_Down;

        this.winchUpPosition = 0.664;

        this.winchDownPosition = 0.949;

        this.winchServoInitPos = 0.645;
    }

}
