package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;
import java.util.ArrayList;

import org.firstinspires.ftc.teamcode.library.utility.Converter;

/**
 *
 */
public class DriveTrain
{
    /**
     */
    private final DriveTrainConfiguration config;

    /**
     */
    private MotorGroup motorGroup;

    /**
     */
    private final ElapsedTime runtime = new ElapsedTime();

    /**
     */
    private final LinearOpMode robot;

    /**
     */
    private DcMotor leftFrontMotor;
    private DcMotor rightFrontMotor;
    private DcMotor rightRearMotor;
    private DcMotor leftRearMotor;


    /**
     *
     * @param config The drive train configuration, because of the number of variables, instead of
     *               passing all the variables as parameters, pass the variables in a configurable
     *               object
     */
    public DriveTrain (DriveTrainConfiguration config)
    {
        this.config = config;

        this.robot = config.robot;
    }


    /**
     */
    public void init ()
    {
        this.leftFrontMotor = this.initMotor(config.leftFrontDeviceName, DcMotor.Direction.REVERSE);
        // this.rightFrontMotor = this.initMotor(config.rightFrontDeviceName, DcMotor.Direction.FORWARD);
        // this.rightRearMotor = this.initMotor(config.leftRearDeviceName, DcMotor.Direction.REVERSE);
        // this.leftRearMotor = this.initMotor(config.rightRearDeviceName, DcMotor.Direction.FORWARD);

        this.motorGroup = new MotorGroup();
        this.motorGroup.add(this.leftFrontMotor);
        //this.motorGroup.add(this.rightFrontMotor);
        //this.motorGroup.add(this.rightRearMotor);
        //this.motorGroup.add(this.leftRearMotor);
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
        this.back(startPower, maxPower, distance, this.config.defaultUnits);
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
        this.forward(startPower, maxPower, distance, this.config.defaultUnits);
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
     * @param startPower Between 0.01 and 1
     * @param maxPower Between 0.01 and 1
     * @param distance The distance to travel
     * @param units The units for distance, defaults to default units from configuration
     */
    protected void line (double startPower, double maxPower, double distance, Units units)
    {
        if (units == null) {
            units = this.config.defaultUnits;
        }

        //-----------------------------------------------------------------

        distance = Converter.convertToCm(distance, units);
        int tics = this.convertCmToTics(distance);

        this.motorGroup.reset();
        this.motorGroup.setTargetPosition(tics);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        runtime.reset();

        this.motorGroup.setPower(startPower);

        // TODO: future - more than one acceleration algorithm that is configurable (e.g. linear vs logarithmic)

        int rampUpTics = this.convertCmToTics(this.config.rampUpDistanceCm);
        int rampDownTics = this.convertCmToTics(this.config.rampDownDistanceCm);

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
     * @param distance The distance in cm to convert to tics
     * @return The number ber tics converted from cm
     */
    private int convertCmToTics(double distance)
    {
        double wheelCircumference = 2 * Math.PI * ( this.config.wheelDiameterCm / 2 );

        double revs = distance / wheelCircumference;

        double tics = revs * this.config.motorTicsPerRev;

        return (int)Math.round(tics);
    }

    /**
     *
     * @param deviceName The name of the device in the control hub
     * @param direction The initial direction of the motor. NOTE: The direction will be reset for each
     *                  operation
     */
    private DcMotor initMotor (String deviceName, DcMotor.Direction direction)
    {
        DcMotor motor = this.robot.hardwareMap.get(DcMotor.class, deviceName);

        motor.setDirection(direction);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        return motor;
    }

    /**
     *
     */
    private static class MotorGroup
    {
        /**
         */
        private final List<DcMotor> motors = new ArrayList<>();

        /**
         * Adds a motor to the group
         * 
         * @param motor The motor to add to the group
         */
        public void add (DcMotor motor)
        {
            this.motors.add(motor);
        }

        /**
         *
         * @param mode Sets the mode of all motors in the group
         */
        public void setMode (DcMotor.RunMode mode)
        {
            for (DcMotor motor : this.motors ) {
                motor.setMode(mode);
            }
        }

        /**
         *
         * @param tics The number of tics to rotate to
         */
        public void setTargetPosition (int tics)
        {
            for (DcMotor motor : this.motors ) {
                motor.setTargetPosition(tics);
            }
        }

        /**
         *
         * @param power Sets the power on each motor in the group
         */
        public void setPower (double power)
        {
            for (DcMotor motor : this.motors ) {
                motor.setPower(power);
            }
        }

        /**
         *
         * @return True: if all of the motors in group are busy, False: if one of the motors is not busy
         */
        public boolean isBusy ()
        {
            if (this.motors.size() == 0) {
                return false;
            }

            for (DcMotor motor : this.motors ) {
                if (!motor.isBusy()) {
                    return false;
                }
            }

            return true;
        }

        /**
         *
         */
        public void reset ()
        {
            this.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
}
