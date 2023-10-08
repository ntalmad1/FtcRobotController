package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 *
 */
public class MecanumDriveTrainConfiguration extends AbstractDriveTrainConfiguration
{
    /**
     */
    public double accelerationIncrement = 0.01;

    /**
     */
    public double maxPower = 0.5;

    /**
     */
    public DcMotor.Direction leftFrontMotorDirection = DcMotor.Direction.REVERSE;
    public DcMotor.Direction rightFrontMotorDirection = DcMotor.Direction.FORWARD;
    public DcMotor.Direction rightRearMotorDirection = DcMotor.Direction.FORWARD;
    public DcMotor.Direction leftRearMotorDirection = DcMotor.Direction.REVERSE;

    /**
     */
    public boolean incrementalDeceleration = false;

}
