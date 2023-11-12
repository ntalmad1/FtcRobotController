package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.Control;
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

        this.leftArmServoInitPos = 0;

        this.leftArmInitPos = PixelCatcher.ArmPosition.OPENED;

        this.leftArmToggle = Control.Gp1_LeftTrigger_Down;

        //--------------------------------------------------

        this.rightArmServoName = "rightCatcherServo";

        this.rightArmServoOpenedPos = 0;

        this.rightArmServoClosedPos = 1;

        this.rightArmServoInitPos = 0;

        this.rightArmIntiPos = PixelCatcher.ArmPosition.OPENED;

        this.rightArmToggle = Control.Gp1_RightTrigger_Down;

        this.leftPixelSensorName = "leftPixelSensor";

        this.rightPixelSensorName = "rightPixelSensor";
    }

}
