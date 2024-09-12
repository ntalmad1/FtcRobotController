package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.library.utility.Units;

/**
 * 
 */
@Autonomous(name="Robot: SelfDrive", group="Robot")
@Disabled
public class SelfDrive extends IsaacBot
{
    /**
     */
    private final SimpleDriveTrain driveTrain;

    /**
     */
    public SelfDrive ()
    {
        super();

        SimpleDriveTrainConfig driveTrainConfig = new SimpleDriveTrainConfig();

        driveTrainConfig.robot = this;
        driveTrainConfig.leftFrontDeviceName  = "leftFrontDrive";
        driveTrainConfig.rightFrontDeviceName = "rightFrontDrive";
        driveTrainConfig.rightRearDeviceName  = "rightRearDrive";
        driveTrainConfig.leftRearDeviceName   = "leftRearDrive";
        driveTrainConfig.imuName = "imu";

        driveTrainConfig.motorTicsPerRev = 537.7;
        driveTrainConfig.wheelDiameterCm = 9.6;
        driveTrainConfig.rampUpDistanceCm = 50;
        driveTrainConfig.rampDownDistanceCm = 50;

        driveTrainConfig.debug = true;

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        this.driveTrain.init();

        telemetry.addLine("Drive train initialized. Ready.");
        telemetry.update();

        waitForStart();



//        this.driveTrain.forward(0.1, 0.8, 1, Units.Meters);
//
//        sleep(250);
//
//        this.driveTrain.gyroTurnLeft(0.05, 0.2, 90);
//
//        sleep(250);
//
//        this.driveTrain.forward(0.1, 0.8, 1, Units.Meters);

        boolean commandAdded = false;

        while (this.opModeIsActive()) {
            this.driveTrain.run();

            if (!commandAdded) {
//                this.driveTrain
//                        .forward(0.1, 0.2, 10, Units.Centimeters)
//                        .back(0.1, 0.2, 10, Units.Centimeters);

                this.driveTrain
                        //.sidewaysRight(0.1, 0.3, 10, Units.Centimeters);
                        //.frontAxelPivotLeft(0.1, 0.3, 45);
                        //.diagFrontRight(0.1, 0.3, 10, Units.Centimeters)
                        //.diagRearLeft(0.1, 0.3, 10, Units.Centimeters)
                        //.diagFrontLeft(0.1, 0.3, 10, Units.Centimeters)
                        .diagRearRight(0.1, 0.3, 10, Units.Centimeters)
                ;
                commandAdded = true;
            }
        }
    }
}