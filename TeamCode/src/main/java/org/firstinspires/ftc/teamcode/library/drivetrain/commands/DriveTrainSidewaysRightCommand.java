package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Units;

public class DriveTrainSidewaysRightCommand extends AbstractDriveTrainLineCommand {

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     */
    public DriveTrainSidewaysRightCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units) {
        super(driveTrain, startPower, maxPower, distance, units);
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