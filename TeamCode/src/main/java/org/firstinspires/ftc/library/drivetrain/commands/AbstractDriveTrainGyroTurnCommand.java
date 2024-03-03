package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;

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
            double startPower,
            double maxPower,
            double degrees,
            Orientation orientation) {
        super();

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

        this.powerBand = maxPower - startPower;

        this.currentDegrees = this.driveTrain.getYaw();

        if (this.orientation.equals(Orientation.RELATIVE)) {
            this.targetDegrees = this.currentDegrees + this.degrees;
        }
        else {
            this.targetDegrees = this.degrees;
        }


//        this.rampUpDegrees = this.currentDegrees + ((targetDegrees - this.currentDegrees) / 2);
//        this.rampDownDegrees = this.currentDegrees + ((targetDegrees - this.currentDegrees) / 2);

        this.setInitialized(true);
    }

    @Override
    public void run () {

        if (degrees >= 0 && (currentDegrees < targetDegrees))
        {
//            if (currentDegrees <= rampUpDegrees)
//            {
//                double newPower = startPower + ((currentDegrees / rampUpDegrees) * powerBand);
//                this.driveTrain.getMotorGroup().setPower(newPower);
//            }
//            else if (currentDegrees > rampUpDegrees)
//            {
//                double newPower = maxPower - (((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand);
//                this.driveTrain.getMotorGroup().setPower(newPower);
//            }

            this.driveTrain.getMotorGroup().setPower(this.startPower);

            if (true || this.driveTrain.getConfig().isDebug())
            {
                currentDegrees = this.driveTrain.getYaw();
                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.telemetry.update();
           }
        }
        else if (degrees < 0 && (currentDegrees > targetDegrees))
        {
//            if (currentDegrees >= rampUpDegrees)
//            {
//                double newPower = startPower + Math.abs(((currentDegrees / rampUpDegrees) * powerBand));
//                this.driveTrain.getMotorGroup().setPower(newPower);
//            }
//            else if (currentDegrees < rampUpDegrees)
//            {
//                double newPower = maxPower - Math.abs((((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand));
//                this.driveTrain.getMotorGroup().setPower(newPower);
//            }

            this.driveTrain.getMotorGroup().setPower(this.startPower);

            if (true || this.driveTrain.getConfig().isDebug())
            {
                currentDegrees = this.driveTrain.getYaw();
                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.telemetry.update();
            }
        }
        else {
            this.markAsCompleted();

            if (this.driveTrain.isBrakeOn()) {
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
        }
    }

}
