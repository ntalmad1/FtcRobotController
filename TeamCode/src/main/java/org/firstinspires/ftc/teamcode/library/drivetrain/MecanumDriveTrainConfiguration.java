package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 *
 */
public class MecanumDriveTrainConfiguration extends AbstractDriveTrainConfiguration
{
    /**
     */
    public double accelerationIncrement = 0.01;

    public double maxpower = 0.5;

    public DcMotor.Direction leftFrontMotorDirection = DcMotor.Direction.REVERSE;
}
