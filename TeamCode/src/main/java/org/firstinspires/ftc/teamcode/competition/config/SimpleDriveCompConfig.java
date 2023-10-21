package org.firstinspires.ftc.teamcode.competition.config;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrainConfig;

/**
 *
 */
public class SimpleDriveCompConfig extends SimpleDriveTrainConfig {

    /**
     *
     * @param robot
     */
    public SimpleDriveCompConfig (IsaacBot robot) {
        this.robot = robot;

        this.leftFrontDeviceName  = "leftFrontDrive";
        this.rightFrontDeviceName = "rightFrontDrive";
        this.rightRearDeviceName  = "rightRearDrive";
        this.leftRearDeviceName   = "leftRearDrive";
        this.imuName = "imu";

        this.motorTicsPerRev = 537.7;
        this.wheelDiameterCm = 9.6;
        this.rampUpDistanceCm = 50;
        this.rampDownDistanceCm = 50;
    }

}
