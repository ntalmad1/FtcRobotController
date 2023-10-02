package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.utility.Units;
import org.firstinspires.ftc.teamcode.library.utility.Converter;
import org.firstinspires.ftc.teamcode.library.utility.GridUtils;

/**
 *
 */
public class SimpleDriveTrain extends AbstractDriveTrain
{
    /**
     *
     * @param config The drive train configuration, because of the number of variables, instead of
     *               passing all the variables as parameters, pass the variables in a configurable
     *               object
     */
    public SimpleDriveTrain(SimpleDriveTrainConfiguration config)
    {
        super(config);
    }

    /**
     */
    public void init ()
    {
        super.init();
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel - Units will default to the default units defined int he
     *                 drive train configuration
     */
    public void back (double startPower, double maxPower, double distance)
    {
        this.back(startPower, maxPower, distance, this.getConfig().defaultUnits);
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The unit type for distance
     */
    public void back (double startPower, double maxPower, double distance, Units units)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.line(startPower, maxPower, distance, units);
    }


    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel - Units will default to the default units defined int he
     *                 drive train configuration
     */
    public void forward (double startPower, double maxPower, double distance)
    {
        this.forward(startPower, maxPower, distance, this.getConfig().defaultUnits);
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The unit type for distance
     */
    public void forward (double startPower, double maxPower, double distance, Units units)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.line(startPower, maxPower, distance, units);
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public void turnLeft (double startPower, double maxPower, double degrees)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.turn(startPower, maxPower, degrees);
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public void turnRight (double startPower, double maxPower, double degrees)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.turn(startPower, maxPower, degrees);
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    protected void turn (double startPower, double maxPower, double degrees)
    {
        double arcLength = GridUtils.arcLength(this.getConfig().turningRadiusCm, degrees);
        this.line(startPower, maxPower, arcLength, Units.Centimeters);
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The units for distance, defaults to default units from configuration
     */
    protected void line (double startPower, double maxPower, double distance, Units units)
    {
        if (units == null) {
            units = this.getConfig().defaultUnits;
        }

        //-----------------------------------------------------------------

        distance = Converter.convertToCm(distance, units);
        int tics = this.convertCmToTics(distance);

        this.resetMotorGroup();
        this.motorGroup.setTargetPosition(tics);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        this.motorGroup.setPower(startPower);

        // TODO: future - more than one acceleration algorithm that is configurable (e.g. linear vs logarithmic)

        int rampUpTics = this.convertCmToTics(this.getConfig().rampUpDistanceCm);
        int rampDownTics = this.convertCmToTics(this.getConfig().rampDownDistanceCm);

        if ((rampUpTics + rampDownTics) > tics)
        {
            rampUpTics = tics / 2;
            rampDownTics = tics / 2;
        }

        int rampUpTicsEnd = rampUpTics;
        int rampDownTicsStart = tics - rampDownTics;

        double powerBand = maxPower - startPower;

        while (this.robot.opModeIsActive() && this.motorGroup.isBusy())
        {
            int currentPosition = this.leftFrontMotor.getCurrentPosition();

            this.robot.telemetry.addData("Running to",  " %7d", tics);
            this.robot.telemetry.addData("Left Front Currently at",  " at %7d", this.leftFrontMotor.getCurrentPosition());
            this.robot.telemetry.addData("Left Front  Motor Power: ", " %f", this.leftFrontMotor.getPower());
            this.robot.telemetry.update();

            if (currentPosition <= rampUpTicsEnd)
            {
                double newPower = startPower + (((double)currentPosition / (double)rampUpTics) * powerBand);
                this.motorGroup.setPower(newPower);
            }
            else if (currentPosition > rampDownTicsStart)
            {
                double newPower = maxPower - (((double)(currentPosition - rampDownTicsStart) / (double)rampDownTics) * powerBand);
                this.motorGroup.setPower(newPower);
            }
        }

        //-----------------------------------------------------------------

        this.motorGroup.setPower(0);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     *
     * @return
     */
    protected SimpleDriveTrainConfiguration getConfig ()
    {
        return (SimpleDriveTrainConfiguration)super.getConfig();
    }

    /**
     *
     * @param distance The distance in cm to convert to tics
     * @return The number ber tics converted from cm
     */
    private int convertCmToTics(double distance)
    {
        double wheelCircumference = 2 * Math.PI * ( this.getConfig().wheelDiameterCm / 2 );

        double revs = distance / wheelCircumference;

        double tics = revs * this.getConfig().motorTicsPerRev;

        return (int)Math.round(tics);
    }

    /**
     *
     */
    private void resetMotorGroup ()
    {
        this.motorGroup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
