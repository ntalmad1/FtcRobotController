package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Units;

public class DriveTrainDiagFrontRightCommand extends AbstractDriveTrainLineCommand {

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     */
    public DriveTrainDiagFrontRightCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units) {
        super(driveTrain, startPower, maxPower, distance * ((double)5/(double)(3)) , units);
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightFrontMotor());
        this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftRearMotor());

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        //this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        //this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        super.init();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
