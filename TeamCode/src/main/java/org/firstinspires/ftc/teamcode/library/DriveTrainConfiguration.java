package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 *
 */
public class DriveTrainConfiguration
{
    /**
     */
    public LinearOpMode robot;

    /**
     */
    public double motorTicsPerRev;

    /**
     */
    public double wheelDiameterCm;

    /**
     */
    public double rampUpDistanceCm;

    /**
     */
    public double rampDownDistanceCm;

    /**
     */
    public Units defaultUnits = Units.Centimeters;

    /**
     */
    public String leftFrontDeviceName;
    public String rightFrontDeviceName;
    public String rightRearDeviceName;
    public String leftRearDeviceName;
}
