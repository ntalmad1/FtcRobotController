package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 *
 */
public class DriveTrainSpinMotorCommand extends AbstractDriveTrainLineCommand {

    /**
     */
    private int leftFront;

    /**
     */
    private int rightFront;

    /**
     */
    private int rightRear;

    /**
     */
    private int leftRear;

    /**
     */
    private DcMotorSimple.Direction direction;


    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     */
    public DriveTrainSpinMotorCommand(SimpleDriveTrain driveTrain,
                                      int leftFront,
                                      int rightFront,
                                      int rightRear,
                                      int leftRear,
                                      DcMotorSimple.Direction direction, double startPower, double maxPower, double distance, Units units) {
        super(driveTrain, startPower, maxPower, distance, units);

        this.leftFront = leftFront;
        this.rightFront = rightFront;
        this.rightRear = rightRear;
        this.leftRear = leftRear;

        this.direction = direction;
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.getMotorGroup().enableAll();

        if (leftFront == 1) {
            this.driveTrain.getLeftFrontMotor().setDirection(direction);
        } else {
            this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftFrontMotor());
        }

        if (rightFront == 1) {
            this.driveTrain.getRightFrontMotor().setDirection(direction);
        } else {
            this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightFrontMotor());
        }

        if (rightRear == 1) {
            this.driveTrain.getRightRearMotor().setDirection(direction);
        } else {
            this.driveTrain.getMotorGroup().disable(this.driveTrain.getRightRearMotor());
        }

        if (leftRear == 1) {
            this.driveTrain.getLeftRearMotor().setDirection(direction);
        } else {
            this.driveTrain.getMotorGroup().disable(this.driveTrain.getLeftRearMotor());
        }

        super.init();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
