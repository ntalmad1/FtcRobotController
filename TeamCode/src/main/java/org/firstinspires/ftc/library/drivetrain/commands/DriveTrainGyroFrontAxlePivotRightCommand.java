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
    public DriveTrainGyroFrontAxlePivotRightCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees) {
        super(driveTrain, startPower, maxPower, -degrees);

        this.driveTrain.telemetry.addLine("DriveTrainGyroFrontAxlePivotRightCommand");
        this.driveTrain.telemetry.addData("Degrees: ", "%2f", -degrees);
        this.driveTrain.telemetry.update();
    }

    /**
     *
     */
    public void init () {

        //this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftFrontMotor());
        this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightFrontMotor());

        //this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        super.init();
    }

}
