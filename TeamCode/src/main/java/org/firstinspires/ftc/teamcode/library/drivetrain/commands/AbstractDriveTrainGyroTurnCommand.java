package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;

/**
 *
 */
public abstract class AbstractDriveTrainGyroTurnCommand extends AbstractSynchronousCommand {

    private double startPower;
    private double maxPower;
    private double degrees;

    double rampUpDegrees;
    double rampDownDegrees;
    double powerBand;

    double currentDegrees;

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

        this.rampUpDegrees = degrees / 2;
        this.rampDownDegrees = degrees / 2;
        this.powerBand = maxPower - startPower;

        this.driveTrain.resetYaw();
        this.driveTrain.getMotorGroup().setPower(startPower);

        this.currentDegrees = Math.abs(this.driveTrain.getYaw());

        this.setInitialized(true);
    }

    @Override
    public void run () {
        if (currentDegrees < degrees)
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

            currentDegrees = Math.abs(this.driveTrain.getYaw());

            if (this.driveTrain.getConfig().isDebug())
            {
                this.driveTrain.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
                this.driveTrain.telemetry.addData("Motor Power: ", "%2f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.telemetry.update();
           }
        }
        else {
            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.markAsCompleted();
        }
    }

}
