package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Converter;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/**
 *
 */
public abstract class AbstractAprilTagStrafeCommand extends AbstractSynchronousCommand {

    protected SimpleDriveTrain driveTrain;
    protected Direction direction;

    private AprilTagProcessor aprilTagProcessor;
    private int targetId;

    private double power;
    private double maxDistance;
    private Units units;

    private int maxTics;

    private boolean detected;

    private double offset;

    /**
     * Constructor
     */
    public AbstractAprilTagStrafeCommand(
            SimpleDriveTrain driveTrain,
            Direction direction,
            AprilTagProcessor aprilTagProcessor,
            int targetId,
            double power,
            double maxDistance,
            Units units,
            double offset) {

        super();

        this.driveTrain = driveTrain;
        this.direction = direction;
        this.aprilTagProcessor = aprilTagProcessor;
        this.targetId = targetId;
        this.power = power;
        this.maxDistance = maxDistance;
        this.units = units;
        this.offset = offset;
    }

    /**
     *
     */
    public void init () {
        if (units == null) {
            units = this.driveTrain.getConfig().defaultUnits;
        }

        this.maxDistance = Converter.convertToCm(maxDistance, units);
        this.maxTics = this.driveTrain.convertCmToTics(maxDistance);

        this.driveTrain.resetMotorGroup();
        this.driveTrain.getMotorGroup().setTargetPosition(maxTics);
        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.driveTrain.getMotorGroup().setPower(this.power);

        this.setInitialized(true);
    }


    @Override
    public void run() {
        if (this.driveTrain.getMotorGroup().isBusy())
        {
            int currentPosition = this.driveTrain.getLeftFrontMotor().getCurrentPosition();

            if (this.driveTrain.getConfig().debug) {
                this.driveTrain.getRobot().telemetry.addData("Running to",  " %7d", maxTics);
                this.driveTrain.getRobot().telemetry.addData("Left Front Currently at",  " at %7d", this.driveTrain.getLeftFrontMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Left Front  Motor Power: ", " %f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.getRobot().telemetry.update();
            }

            if (this.driveTrain.getLeftFrontMotor().getCurrentPosition() >= this.maxTics) {
                this.endCommand(false);
            }
            else {

                List<AprilTagDetection> currentDetections = this.aprilTagProcessor.getDetections();

                for (AprilTagDetection detection : currentDetections) {

                    if (detection.id == this.targetId) {
                        this.driveTrain.getRobot().telemetry.addData("Target ID: ",  " %7d", this.targetId);
                        this.driveTrain.getRobot().telemetry.addData("Detected ID: ",  " %7d", detection.id);
                        this.driveTrain.getRobot().telemetry.addData("Pos X: ",  " %2f", detection.ftcPose.x);
                        this.driveTrain.getRobot().telemetry.update();
                    }

                    if (detection.id == this.targetId) {
                        this.driveTrain.getMotorGroup().setPower(0.05);

                        if (Direction.RIGHT.equals(this.direction) && detection.ftcPose.x <= this.offset) {

                            if (Math.abs(this.offset - detection.ftcPose.x) > 0.5) {
                                this.goLeft();
                                return;
                            }

                            this.endCommand(true);
                        }

                        else if (Direction.LEFT.equals(this.direction) && detection.ftcPose.x >= this.offset ) {

                            if (Math.abs(detection.ftcPose.x - this.offset) > 0.5) {
                                this.goRight();
                                return;
                            }

                            this.endCommand(true);
                        }
                    }
                }
            }
        }
        else {

//            List<AprilTagDetection> currentDetections = this.aprilTagProcessor.getDetections();
//
//            for (AprilTagDetection detection : currentDetections) {
//
//                if (detection.id == this.targetId) {
//                    this.driveTrain.getRobot().telemetry.addData("Target ID: ", " %7d", this.targetId);
//                    this.driveTrain.getRobot().telemetry.addData("Detected ID: ", " %7d", detection.id);
//                    this.driveTrain.getRobot().telemetry.addData("Pos X: ", " %2f", detection.ftcPose.x);
//                    this.driveTrain.getRobot().telemetry.update();
//                }
//            }

           this.endCommand(false);

        }

    }

    /**
     *
     * @return
     */
    public boolean isDetected () {
        return this.isDetected();
    }

    /**
     *
     */
    private void endCommand (boolean detected) {
        this.detected = detected;



        if (this.driveTrain.isBrakeOn()) {
            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);

            this.driveTrain.getMotorGroup().setTargetPosition(0);

            this.driveTrain.getMotorGroup().setPower(1);
        }
        else {
            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

        if (detected) {
            this.markAsCompleted();
        } else {
            this.markAsFailed(new Exception("Failed to detected April Tag with ID " + this.targetId));
        }
    }

    private void goRight () {
        this.driveTrain.getMotorGroup().setPower(0);
        int tics = this.driveTrain.getLeftFrontMotor().getCurrentPosition();

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.resetMotorGroup();

        this.driveTrain.getMotorGroup().setTargetPosition(tics);
        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.driveTrain.getMotorGroup().setPower(0.05);


        this.direction = Direction.RIGHT;
    }

    private void goLeft () {
        this.driveTrain.getMotorGroup().setPower(0);
        int tics = this.driveTrain.getLeftFrontMotor().getCurrentPosition();

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.resetMotorGroup();

        this.driveTrain.getMotorGroup().setTargetPosition(tics);
        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.driveTrain.getMotorGroup().setPower(0.05);

        this.direction = Direction.LEFT;
    }
}
