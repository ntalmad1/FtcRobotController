package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;

public class DriveTrainGyroFrontAxlePivotRightCommand extends AbstractDriveTrainGyroTurnCommand{

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public DriveTrainGyroFrontAxlePivotRightCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees, Orientation orientation) {
        super(driveTrain, startPower, maxPower, -degrees, orientation);
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.resetMotorGroup();

        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftFrontMotor());
        this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightFrontMotor());

        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        super.init();
    }

}
