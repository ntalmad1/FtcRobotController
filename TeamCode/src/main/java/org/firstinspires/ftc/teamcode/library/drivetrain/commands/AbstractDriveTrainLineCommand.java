package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Converter;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 *
 */
public abstract class AbstractDriveTrainLineCommand extends AbstractSynchronousCommand {

    private double startPower;
    private double maxPower;
    private double distance;
    private Units units;

    protected SimpleDriveTrain driveTrain;

    private int tics;
    private int rampUpTics;
    private int rampUpTicsEnd;
    private int rampDownTics;
    private int rampDownTicsStart;

    private double powerBand;

    /**
     * Constructor
     */
    public AbstractDriveTrainLineCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units) {
        super();

        this.driveTrain = driveTrain;
        this.startPower = startPower;
        this.maxPower = maxPower;
        this.distance = distance;
        this.units = units;
    }

    /**
     *
     */
    public void init () {
        if (units == null) {
            units = this.driveTrain.getConfig().defaultUnits;
        }

        this.distance = Converter.convertToCm(distance, units);
        this.tics = this.driveTrain.convertCmToTics(distance);

        this.driveTrain.resetMotorGroup();
        this.driveTrain.getMotorGroup().setTargetPosition(tics);
        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.driveTrain.getMotorGroup().setPower(startPower);

        this.rampUpTics = this.driveTrain.convertCmToTics(this.driveTrain.getConfig().rampUpDistanceCm);
        this.rampDownTics = this.driveTrain.convertCmToTics(this.driveTrain.getConfig().rampDownDistanceCm);

        if ((this.rampUpTics + this.rampDownTics) > this.tics)
        {
            this.rampUpTics = tics / 2;
            this.rampDownTics = tics / 2;
        }

        this.rampUpTicsEnd = rampUpTics;
        this.rampDownTicsStart = tics - rampDownTics;

        this.powerBand = maxPower - startPower;

        this.setInitialized(true);
    }


    @Override
    public void run() {
        if (this.driveTrain.getMotorGroup().isBusy())
        {
            int currentPosition = this.driveTrain.getLeftFrontMotor().getCurrentPosition();

            if (this.driveTrain.getConfig().debug = true) {
                this.driveTrain.getRobot().telemetry.addData("Running to",  " %7d", tics);
                this.driveTrain.getRobot().telemetry.addData("Left Front Currently at",  " at %7d", this.driveTrain.getLeftFrontMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Left Front  Motor Power: ", " %f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.getRobot().telemetry.update();
            }

            if (currentPosition <= rampUpTicsEnd)
            {
                double newPower = startPower + (((double)currentPosition / (double)rampUpTics) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
            else if (currentPosition > rampDownTicsStart)
            {
                double newPower = maxPower - (((double)(currentPosition - rampDownTicsStart) / (double)rampDownTics) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
        }
        else {
            this.markAsCompleted();

            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }
}
