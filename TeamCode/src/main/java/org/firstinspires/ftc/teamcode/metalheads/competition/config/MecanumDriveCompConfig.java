package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;

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
        this.imuName = "imu";
        this.debug = false;
    }
}
