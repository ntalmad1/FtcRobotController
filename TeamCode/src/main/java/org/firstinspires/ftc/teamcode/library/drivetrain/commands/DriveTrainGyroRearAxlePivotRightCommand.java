package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Direction;

public class DriveTrainGyroRearAxlePivotRightCommand extends AbstractDriveTrainGyroTurnCommand{

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public DriveTrainGyroRearAxlePivotRightCommand(
            SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees, Orientation orientation) {
        super(driveTrain, Direction.RIGHT, startPower, maxPower, degrees, orientation);
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.resetMotorGroup();

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        //this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftRearMotor());
        this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightRearMotor());

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        //this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        super.init();
    }

}
