package org.firstinspires.ftc.teamcode.archive.competition.config;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.lightbar.LightBarConfig;

/**
 *
 */
public class LightBarCompConfig extends LightBarConfig {

    /**
     * Constructor
     *
     * @param robot
     */
    public LightBarCompConfig (IsaacBot robot) {
        this.robot = robot;

        this.leftPixelCatcherArmLED = "leftCatcherLED";
        this.rightPixelCatcherArmLED = "rightCatcherLED";

        this.leftPixelLED = "leftPixelLED";
        this.rightPixelLED = "rightPixelLED";

        this.leftClawPincherLED = "leftPincherLED";
        this.rightClawPincherLED = "rightPincherLED";

        this.driveTrainModeLED = "driveModeLED";

        this.leftPixelCapturedTolerance = 50;
        this.leftPixelInRangeTolerance = 100;

        this.rightPixelCapturedTolerance = 30;
        this.rightPixelInRangeTolerance = 100;
    }

}
