package org.firstinspires.ftc.teamcode.library.drivetrain;

import org.firstinspires.ftc.library.utility.Units;

/**
 *
 */
public class SimpleDriveTrainConfig extends AbstractDriveTrainConfig
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
    public double turningRadiusCm;

    /**
     */
    public Units defaultUnits = Units.Centimeters;

    /**
     */
    public double yawOffset = 90;

    /**
     */
    public boolean brakeOn = false;
}
