package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.drivetrain.commands.AbstractDriveTrainLineCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.commands.DriveTrainBackwardsCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.commands.DriveTrainForwardsCommand;
import org.firstinspires.ftc.teamcode.library.utility.Units;
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
    public SimpleDriveTrain(SimpleDriveTrainConfig config)
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
    public SimpleDriveTrain back (double startPower, double maxPower, double distance)
    {
        return this.back(startPower, maxPower, distance, this.getConfig().defaultUnits);
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The unit type for distance
     */
    public SimpleDriveTrain back (double startPower, double maxPower, double distance, Units units)
    {
        this.addCommand(new DriveTrainBackwardsCommand(this, startPower, maxPower, distance, units));
        return this;
    }


    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel - Units will default to the default units defined int he
     *                 drive train configuration
     */
    public SimpleDriveTrain forward (double startPower, double maxPower, double distance)
    {
        return this.forward(startPower, maxPower, distance, this.getConfig().defaultUnits);
    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The unit type for distance
     */
    public SimpleDriveTrain forward (double startPower, double maxPower, double distance, Units units)
    {
        this.addCommand(new DriveTrainForwardsCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public void gyroTurnLeft (double startPower, double maxPower, double degrees)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        this.motorGroup.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.gyroTurn(startPower, maxPower, degrees);
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    public void gyroTurnRight (double startPower, double maxPower, double degrees)
    {
        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.motorGroup.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.gyroTurn(startPower, maxPower, degrees);
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
    protected void gyroTurn (double startPower, double maxPower, double degrees)
    {
        double rampUpDegrees = degrees / 2;
        double rampDownDegrees = degrees / 2;
        double powerBand = maxPower - startPower;

        this.robot.resetYaw();
        this.motorGroup.setPower(startPower);

        double currentDegrees = Math.abs(this.robot.getYaw());
        while (currentDegrees < degrees)
        {
            if (currentDegrees <= rampUpDegrees)
            {
                double newPower = startPower + ((currentDegrees / rampUpDegrees) * powerBand);
                this.motorGroup.setPower(newPower);
            }
            else if (currentDegrees > rampUpDegrees)
            {
                double newPower = maxPower - (((currentDegrees - rampUpDegrees) / rampDownDegrees) * powerBand);
                this.motorGroup.setPower(newPower);
            }

            currentDegrees = Math.abs(this.robot.getYaw());

            if (this.getConfig().isDebug())
            {
                this.robot.telemetry.addData("Current Degrees: ", "%2f", currentDegrees);
                this.robot.telemetry.addData("Motor Power: ", "%2f", this.leftFrontMotor.getPower());
                this.robot.telemetry.update();
            }
        }

        this.motorGroup.setPower(0);
    }

    /**
     *
     */
    public void run () {
        super.run();
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
//    public void turnLeft (double startPower, double maxPower, double degrees)
//    {
//        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        this.leftRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//        this.rightRearMotor.setDirection(DcMotorSimple.Direction.FORWARD);
//
//        this.turn(startPower, maxPower, degrees);
//    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
//    public void turnRight (double startPower, double maxPower, double degrees)
//    {
//        this.leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        this.leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        this.rightFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//        this.rightRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
//
//        this.turn(startPower, maxPower, degrees);
//    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     */
//    protected void turn (double startPower, double maxPower, double degrees)
//    {
//        // double radius = this.getConfig().wheelBaseCm; // / Math.tan(0);
//        double radius = this.getConfig().turningRadiusCm;
//        double arcLength = GridUtils.arcLength(radius, degrees);
//        this.line(startPower, maxPower, arcLength, Units.Centimeters);
//    }

    /**
     *
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The units for distance, defaults to default units from configuration
     */
//    protected void line (double startPower, double maxPower, double distance, Units units)
//    {
//
//    }

    /**
     *
     * @return
     */
    public SimpleDriveTrainConfig getConfig ()
    {
        return (SimpleDriveTrainConfig)super.getConfig();
    }

    /**
     *
     * @param distance The distance in cm to convert to tics
     * @return The number ber tics converted from cm
     */
    public int convertCmToTics(double distance)
    {
        double wheelCircumference = 2 * Math.PI * ( this.getConfig().wheelDiameterCm / 2 );

        double revs = distance / wheelCircumference;

        double tics = revs * this.getConfig().motorTicsPerRev;

        return (int)Math.round(tics);
    }

    /**
     *
     */
    public void resetMotorGroup ()
    {
        this.motorGroup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
