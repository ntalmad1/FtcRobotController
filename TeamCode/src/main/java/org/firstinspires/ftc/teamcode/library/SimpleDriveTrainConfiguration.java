package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 *
 */
public class SimpleDriveTrainConfiguration extends AbstractDriveTrainConfiguration
{
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
}
