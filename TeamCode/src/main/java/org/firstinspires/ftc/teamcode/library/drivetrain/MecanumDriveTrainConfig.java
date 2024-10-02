package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
public class MecanumDriveTrainConfig extends AbstractDriveTrainConfig
{
    /**
     */
    public double accelerationIncrement = 0.01;

    /**
     */
    public double maxPower = 0.5;

    /**
     *
     */
    public double yawOffset = 0;

    /**
     */
    public DcMotor.Direction leftFrontMotorDirection = DcMotor.Direction.REVERSE;
    public DcMotor.Direction rightFrontMotorDirection = DcMotor.Direction.FORWARD;
    public DcMotor.Direction rightRearMotorDirection = DcMotor.Direction.FORWARD;
    public DcMotor.Direction leftRearMotorDirection = DcMotor.Direction.REVERSE;

    /**
     */
    public boolean incrementalDeceleration = false;

    /**
     */
    public boolean alwaysForwards = true;

    public MecanumDriveTrainConfig() {
        super();
    }

    /**
     *
     * @param robot
     */
    public MecanumDriveTrainConfig(IsaacBot robot) {
        super();

        this.robot = robot;
    }

}
