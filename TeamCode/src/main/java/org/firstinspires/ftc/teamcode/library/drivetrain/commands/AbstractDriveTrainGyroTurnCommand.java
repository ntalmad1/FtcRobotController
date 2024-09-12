package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
public abstract class AbstractDriveTrainGyroTurnCommand extends AbstractSynchronousCommand {

    /**
     *
     */
    public enum Orientation {

        /**
         */
        ABSOLUTE,

        /**
         */
        RELATIVE
    }


    private double startPower;
    private double maxPower;
    protected double degrees;

    double rampUpDegrees;
    double rampDownDegrees;
    double powerBand;

    double currentDegrees;
    double targetDegrees;

    double startingDegrees;

    Direction direction;

    /**
     */
    private Orientation orientation;

    /**
     *
     */
    protected SimpleDriveTrain driveTrain;

    /**
     * Constructor
     *
     * @param driveTrain
     */
    public AbstractDriveTrainGyroTurnCommand (
            SimpleDriveTrain driveTrain,
            Direction direction,
            double startPower,
            double maxPower,
            double degrees,
            Orientation orientation) {
        super();


        this.direction = direction;
        this.driveTrain = driveTrain;

        this.startPower = startPower;
        this.maxPower = maxPower;
        this.degrees = degrees;
        this.orientation = orientation;

    }

    /**
     *
     * @return
     */
    public Orientation getOrientation () {
        return this.orientation;
    }

    @Override
    public void init () {

        this.currentDegrees = this.driveTrain.getYaw();
        this.startingDegrees = this.currentDegrees;

        if (this.orientation.equals(Orientation.RELATIVE)) {
            this.targetDegrees = this.currentDegrees + this.degrees;
        }
        else {
            this.targetDegrees = this.degrees;
        }

        this.setInitialized(true);
    }

    @Override
    public void run () {

        currentDegrees = this.driveTrain.getYaw();

        if (this.inRange(0.5)) {
            this.endCommand();
            return;
        }

        if (targetDegrees > 0 && Direction.RIGHT.equals(this.direction) && (this.currentDegrees > this.targetDegrees)) {
            this.driveTrain.getMotorGroup().setPower(this.startPower);
        }
        else if (targetDegrees > 0 && Direction.RIGHT.equals(this.direction) && (this.currentDegrees < this.targetDegrees)) {
            this.goLeft(0.05);
        }
        else if (targetDegrees > 0 && Direction.LEFT.equals(this.direction) && (this.currentDegrees < this.targetDegrees)) {
            this.driveTrain.getMotorGroup().setPower(this.startPower);
        }
        else if (targetDegrees > 0 && Direction.LEFT.equals(this.direction) && (this.currentDegrees > this.targetDegrees)) {
            this.goRight(0.05);
        }

        // -------------

        else if (targetDegrees < 0 && Direction.RIGHT.equals(this.direction) && (this.currentDegrees > this.targetDegrees)) {
            this.driveTrain.getMotorGroup().setPower(this.startPower);
        }
        else if (targetDegrees < 0 && Direction.RIGHT.equals(this.direction) && (this.currentDegrees < this.targetDegrees)) {
            this.goLeft(0.05);
        }
        else if (targetDegrees < 0 && Direction.LEFT.equals(this.direction) && (this.currentDegrees < this.targetDegrees)) {
            this.driveTrain.getMotorGroup().setPower(this.startPower);
        }
        else if (targetDegrees < 0 && Direction.LEFT.equals(this.direction) && (this.currentDegrees > this.targetDegrees)) {
            this.goRight(0.05);
        }





//        if (targetDegrees == 0 && (this.startingDegrees > 0) && (this.currentDegrees >= 0)) {
//            this.driveTrain.getMotorGroup().setPower(this.startPower);
//        }
//        else if (targetDegrees == 0 && (this.startingDegrees < 0) && (this.currentDegrees <= 0)) {
//            this.driveTrain.getMotorGroup().setPower(this.startPower);
//        }
//        else if ((targetDegrees > 0 && (startingDegrees > targetDegrees && currentDegrees >= targetDegrees))
//             ||  (targetDegrees > 0 && (startingDegrees < targetDegrees && currentDegrees <= targetDegrees))
//             ||  (targetDegrees < 0 && (startingDegrees < targetDegrees && currentDegrees <= targetDegrees))
//             ||  (targetDegrees < 0 && (startingDegrees > targetDegrees && currentDegrees >= targetDegrees)))
//        {
////            if (currentDegrees <= rampUpDegrees)
////            {
////                double newPower = startPower + ((currentDegrees / rampUpDegrees) * powerBand);
////                this.driveTrain.getMotorGroup().setPower(newPower);
////            }
////            else if (currentDegrees > rampUpDegrees)
////            {
////                double newPower = maxPower - (((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand);
////                this.driveTrain.getMotorGroup().setPower(newPower);
////            }
//
//            this.driveTrain.getMotorGroup().setPower(this.startPower);
//
//            if (true || this.driveTrain.getConfig().isDebug())
//            {
//                currentDegrees = this.driveTrain.getYaw();
//                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
//                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
//                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
//                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
//                this.driveTrain.telemetry.update();
//           }
//        }
////        else if (targetDegrees < 0 && (currentDegrees > targetDegrees))
////        {
////
//////            if (currentDegrees >= rampUpDegrees)
//////            {
//////                double newPower = startPower + Math.abs(((currentDegrees / rampUpDegrees) * powerBand));
//////                this.driveTrain.getMotorGroup().setPower(newPower);
//////            }
//////            else if (currentDegrees < rampUpDegrees)
//////            {
//////                double newPower = maxPower - Math.abs((((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand));
//////                this.driveTrain.getMotorGroup().setPower(newPower);
//////            }
////
////            this.driveTrain.getMotorGroup().setPower(this.startPower);
////
////            if (true || this.driveTrain.getConfig().isDebug())
////            {
////                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
////                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
////                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
////                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
////                this.driveTrain.telemetry.update();
////            }
////        }
//        else {
//            this.markAsCompleted();
//
//            if (this.driveTrain.isBrakeOn()) {
//                this.driveTrain.getMotorGroup().enableAll();
//                this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//                this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//                this.driveTrain.getMotorGroup().setTargetPosition(0);
//
//                this.driveTrain.getMotorGroup().setPower(1);
//            }
//            else {
//                this.driveTrain.getMotorGroup().setPower(0);
//                this.driveTrain.getMotorGroup().enableAll();
//                this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//            }
//        }
    }

    /**
     *
     * @return
     */
    private boolean inRange (double tolerance) {

        return (this.currentDegrees < (this.targetDegrees + tolerance)) && (this.currentDegrees > (this.targetDegrees - tolerance));

    }

    private void goRight (double power) {
        this.driveTrain.getMotorGroup().setPower(0);

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.resetMotorGroup();

        this.driveTrain.getMotorGroup().setPower(power);

        this.direction = Direction.RIGHT;
    }

    private void goLeft (double power) {
        this.driveTrain.getMotorGroup().setPower(0);

        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        this.driveTrain.resetMotorGroup();

        this.driveTrain.getMotorGroup().setPower(power);

        this.direction = Direction.LEFT;
    }

    private void endCommand () {
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

        this.markAsCompleted();
    }
}
