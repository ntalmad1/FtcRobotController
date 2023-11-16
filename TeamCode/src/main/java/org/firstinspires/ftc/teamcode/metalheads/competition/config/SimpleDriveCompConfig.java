package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;

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
        this.rampUpDistanceCm = 10;
        this.rampDownDistanceCm = 10;
    }

}
