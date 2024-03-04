package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.utility.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;

public class PixelCatcherCompConfig extends PixelCatcherConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public PixelCatcherCompConfig (IsaacBot robot) {
        this.robot = robot;

        this.leftArmServoName = "leftCatcherServo";

        this.leftArmServoOpenedPos = 0;

        this.leftArmServoClosedPos = 1;

        this.leftArmServoInitPos = 1;

        this.leftArmInitPos = PixelCatcher.ArmPosition.CLOSED;

        this.leftArmToggle = Control.Gp1_LeftTrigger_Down;

        //--------------------------------------------------

        this.rightArmServoName = "rightCatcherServo";

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
