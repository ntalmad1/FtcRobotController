package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Units;

public class DriveTrainSidewaysLeftCommand extends AbstractDriveTrainLineCommand {

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     */
    public DriveTrainSidewaysLeftCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units) {
        super(driveTrain, startPower, maxPower, distance, units);
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
