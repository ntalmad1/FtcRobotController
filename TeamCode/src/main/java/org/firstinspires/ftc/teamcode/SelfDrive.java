package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.library.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.SimpleDriveTrainConfiguration;
import org.firstinspires.ftc.teamcode.library.Units;

/**
 * 
 */
@Autonomous(name="Robot: SelfDrive", group="Robot")
@Disabled
public class SelfDrive extends LinearOpMode
{
    /**
     */
    private final SimpleDriveTrain driveTrain;

    //private MecanumDriveTrain driveTrain;

    /**
     */
    public SelfDrive ()
    {
        super();

        SimpleDriveTrainConfiguration driveTrainConfig = new SimpleDriveTrainConfiguration();

        driveTrainConfig.robot = this;
        driveTrainConfig.motorTicsPerRev = 537.7;
        driveTrainConfig.wheelDiameterCm = 9.6;
        driveTrainConfig.rampUpDistanceCm = 50;
        driveTrainConfig.rampDownDistanceCm = 50;
        driveTrainConfig.leftFrontDeviceName  = "left_front_drive";
        //driveTrainConfig.rightFrontDeviceName = "right_front_drive";
        //driveTrainConfig.rightRearDeviceName  = "right_rear_drive";
        //driveTrainConfig.leftRearDeviceName   = "left_rear_drive";

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        this.driveTrain.init();

        telemetry.addLine("Drive train initialized. Ready.");
        telemetry.update();

        waitForStart();

        this.driveTrain.forward(0.1, 0.5, 100, Units.Centimeters);

        sleep(250);

       // this.driveTrain.turnLeft(0.1, 90);

       // sleep(250);

       // this.driveTrain.forward(0.1, 0.5, 100, Units.Centimeters);
    }
}