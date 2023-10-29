package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;

public class DriveTrainGyroTurnLeftCommand extends AbstractDriveTrainGyroTurnCommand{

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public DriveTrainGyroTurnLeftCommand (SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees) {
        super(driveTrain, startPower, maxPower, degrees);
    }

    /**
     *
     */
    public void init () {

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        super.init();
    }

}
