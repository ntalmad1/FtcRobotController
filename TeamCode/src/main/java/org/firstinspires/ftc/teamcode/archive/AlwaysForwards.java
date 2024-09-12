package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;


/**
 *
 */
@TeleOp(name="Always Forwards", group="Linear OpMode")
@Disabled
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

        MecanumDriveTrainConfig driveTrainConfig = new MecanumDriveTrainConfig();
        driveTrainConfig.robot = this;
        driveTrainConfig.leftFrontDeviceName   = "leftFrontDrive";
        driveTrainConfig.rightFrontDeviceName  = "rightFrontDrive";
        driveTrainConfig.rightRearDeviceName   = "rightRearDrive";
        driveTrainConfig.leftRearDeviceName    = "leftRearDrive";
        driveTrainConfig.accelerationIncrement = 0.02;
        driveTrainConfig.maxPower = 0.65;
        driveTrainConfig.yawOffset = -90;
        driveTrainConfig.incrementalDeceleration = true;
        driveTrainConfig.leftFrontMotorDirection  = DcMotorSimple.Direction.REVERSE;
        driveTrainConfig.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        driveTrainConfig.leftRearMotorDirection  =  DcMotorSimple.Direction.REVERSE;
        driveTrainConfig.rightRearMotorDirection  = DcMotorSimple.Direction.FORWARD;
        driveTrainConfig.imuName = "imu2";
        driveTrainConfig.debug = true;

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode()
    {
        this.driveTrain.init();

        waitForStart();

        //if (isStopRequested()) { return; };

        while (this.opModeIsActive())
        {
            this.driveTrain.run();
        }
    }
}
