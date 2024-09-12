package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Units;

public class DriveTrainScanSidewaysLeftCommand extends AbstractDriveTrainScanCommand {

    /**
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @param sensor
     * @param threshold
     */
    public DriveTrainScanSidewaysLeftCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units, DistanceSensor sensor, double threshold) {
        super(driveTrain, startPower, maxPower, distance, units, sensor, threshold);
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        super.init();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
