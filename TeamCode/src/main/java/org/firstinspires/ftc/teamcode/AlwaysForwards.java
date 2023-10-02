package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrainConfiguration;
import org.firstinspires.ftc.teamcode.library.utility.GridUtils;
import org.firstinspires.ftc.teamcode.library.utility.Point;


/**
 *
 */
@TeleOp(name="Always Forwards", group="Linear OpMode")
//@Disabled
public class AlwaysForwards extends IsaacBot
{
    /**
     */
    private MecanumDriveTrain driveTrain;

    /**
     * Constructor
     */
    public AlwaysForwards ()
    {
        super();

        MecanumDriveTrainConfiguration driveTrainConfig = new MecanumDriveTrainConfiguration();
        driveTrainConfig.robot = this;
        driveTrainConfig.leftFrontDeviceName   = "leftFrontDrive";
        driveTrainConfig.rightFrontDeviceName  = "rightFrontDrive";
        driveTrainConfig.rightRearDeviceName   = "rightRearDrive";
        driveTrainConfig.leftRearDeviceName    = "leftRearDrive";
        driveTrainConfig.accelerationIncrement = 0.02;
        driveTrainConfig.maxpower = 0.5;

        driveTrainConfig.debug = true;


        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode()
    {
        this.initImu("imu");
        this.driveTrain.init();

        waitForStart();

        //if (isStopRequested()) { return; };

        while (this.opModeIsActive())
        {
            this.driveTrain.run();
        }
    }
}
