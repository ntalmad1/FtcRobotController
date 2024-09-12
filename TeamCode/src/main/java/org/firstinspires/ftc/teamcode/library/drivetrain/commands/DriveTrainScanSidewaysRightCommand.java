package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Units;


public class DriveTrainScanSidewaysRightCommand extends AbstractDriveTrainScanCommand {

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
    public DriveTrainScanSidewaysRightCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units, DistanceSensor sensor, double threshold) {
        super(driveTrain, startPower, maxPower, distance, units, sensor, threshold);
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        super.init();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
