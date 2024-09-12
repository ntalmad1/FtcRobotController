package org.firstinspires.ftc.teamcode.archive.competition.config;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrainConfig;

/**
 *
 */
public class MecanumDriveCompConfig  extends MecanumDriveTrainConfig {

    /**
     *
     * @param robot
     */
    public MecanumDriveCompConfig (IsaacBot robot) {
        this.robot = robot;

        this.leftFrontDeviceName   = "leftFrontDrive";
        this.rightFrontDeviceName  = "rightFrontDrive";
        this.rightRearDeviceName   = "rightRearDrive";
        this.leftRearDeviceName    = "leftRearDrive";
        this.accelerationIncrement = 0.04;
        this.maxPower = 0.8;
        this.yawOffset = 0;
        this.incrementalDeceleration = false;
        this.leftFrontMotorDirection  = DcMotorSimple.Direction.REVERSE;
        this.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        this.leftRearMotorDirection  =  DcMotorSimple.Direction.REVERSE;
        this.rightRearMotorDirection  = DcMotorSimple.Direction.FORWARD;
        this.imuName = "externalImu";
        this.debug = false;
    }
}
