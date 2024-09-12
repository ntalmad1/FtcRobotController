package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class AprilTagStrafeRightCommand extends AbstractAprilTagStrafeCommand {

    /**
     * Constructor
     *
     * @param driveTrain
     * @param aprilTagProcessor
     * @param power
     * @param maxDistance
     * @param units
     */
    public AprilTagStrafeRightCommand(SimpleDriveTrain driveTrain, AprilTagProcessor aprilTagProcessor, int id, double power, double maxDistance, Units units, double offset) {
        super(driveTrain, Direction.RIGHT, aprilTagProcessor, id, power, maxDistance, units, offset);
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
