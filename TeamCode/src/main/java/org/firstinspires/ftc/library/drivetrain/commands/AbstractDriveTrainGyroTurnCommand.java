package org.firstinspires.ftc.library.drivetrain.commands;

import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;

/**
 *
 */
public abstract class AbstractDriveTrainGyroTurnCommand extends AbstractSynchronousCommand {

    private double startPower;
    private double maxPower;
    protected double degrees;

    double rampUpDegrees;
    double rampDownDegrees;
    double powerBand;

    double currentDegrees;
    double targetDegrees;

    /**
     *
     */
    protected SimpleDriveTrain driveTrain;

    /**
     * Constructor
     *
     * @param driveTrain
     */
    public AbstractDriveTrainGyroTurnCommand (SimpleDriveTrain driveTrain, double startPower, double maxPower, double degrees) {
        super();

        this.driveTrain = driveTrain;

        this.startPower = startPower;
        this.maxPower = maxPower;
        this.degrees = degrees;

    }

    @Override
    public void init () {
        this.driveTrain.telemetry.addLine("Initing");
        this.driveTrain.telemetry.update();

        this.powerBand = maxPower - startPower;

        //this.driveTrain.resetYaw();
        //this.driveTrain.getMotorGroup().setPower(startPower);

        this.currentDegrees = this.driveTrain.getYaw();
        this.targetDegrees = this.currentDegrees + this.degrees;

        this.rampUpDegrees = this.currentDegrees + ((targetDegrees - this.currentDegrees) / 2);
        this.rampDownDegrees = this.currentDegrees + ((targetDegrees - this.currentDegrees) / 2);

        this.setInitialized(true);
    }

    @Override
    public void run () {

        this.driveTrain.telemetry.addLine("Running command");
        this.driveTrain.telemetry.update();

        if (degrees >= 0 && (currentDegrees < targetDegrees))
        {
            if (currentDegrees <= rampUpDegrees)
            {
                double newPower = startPower + ((currentDegrees / rampUpDegrees) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
            else if (currentDegrees > rampUpDegrees)
            {
                double newPower = maxPower - (((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }

            currentDegrees = this.driveTrain.getYaw();

            if (true || this.driveTrain.getConfig().isDebug())
            {
                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.telemetry.update();
           }
        }
        else if (degrees < 0 && (currentDegrees > targetDegrees))
        {
            if (currentDegrees >= rampUpDegrees)
            {
                double newPower = startPower + Math.abs(((currentDegrees / rampUpDegrees) * powerBand));
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
            else if (currentDegrees < rampUpDegrees)
            {
                double newPower = maxPower - Math.abs((((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand));
                this.driveTrain.getMotorGroup().setPower(newPower);
            }

            currentDegrees = this.driveTrain.getYaw();

            if (true || this.driveTrain.getConfig().isDebug())
            {
                this.driveTrain.telemetry.addLine("Right");
                this.driveTrain.telemetry.update();
//                this.driveTrain.telemetry.addData("Degrees: ", "%2f", degrees);
//                this.driveTrain.telemetry.addData("Target Degrees: ", "%2f", targetDegrees);
//                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
//                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
//                this.driveTrain.telemetry.update();
            }
        }
        else {
            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.markAsCompleted();
        }
    }

}
