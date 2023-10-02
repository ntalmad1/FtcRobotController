package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

public class AbstractDriveTrainConfiguration
{
    /**
     */
    public IsaacBot robot;

    /**
     */
    public String leftFrontDeviceName;
    public String rightFrontDeviceName;
    public String rightRearDeviceName;
    public String leftRearDeviceName;
    public boolean debug = false;

    /**
     *
     * @return
     */
    public boolean isDebug ()
    {
        return this.debug;
    }
}
