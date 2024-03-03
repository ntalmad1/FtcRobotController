package org.firstinspires.ftc.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.drivetrain.commands.AbstractDriveTrainGyroTurnCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainBackwardsCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainDiagFrontLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainDiagFrontRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainDiagRearLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainDiagRearRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainForwardBySensorCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainForwardsCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroFrontAxlePivotLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroFrontAxlePivotRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroRearAxlePivotLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroRearAxlePivotRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainScanSidewaysLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainScanSidewaysRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainSidewaysLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainSidewaysRightCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroTurnLeftCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainGyroTurnRightCommand;
import org.firstinspires.ftc.library.drivetrain.commands.DriveTrainSpinMotorCommand;
import org.firstinspires.ftc.library.drivetrain.commands.GotoDegreesCommand;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;

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

        telemetry.addLine("Imu Name: " + config.imuName);
        telemetry.update();
    }

    /**
     */
    public void init ()
    {
        super.init();
        if (this.getConfig().brakeOn) {
            this.motorGroup.setBrakeOn();
        }
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain gotoDegrees (Direction direction, double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation) {
        this.addCommand(new GotoDegreesCommand(this, direction, startPower, maxPower, degrees, orientation));
        return this;
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
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @return
     */
    public SimpleDriveTrain diagFront (Direction direction, double startPower, double maxPower, double distance, Units units) {

        switch (direction) {
            case RIGHT:
                return this.diagFrontRight(startPower, maxPower, distance, units);
            case LEFT:
                return this.diagFrontLeft(startPower, maxPower, distance, units);
        }

        return this;
    }


    /**
     *
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @return
     */
    public SimpleDriveTrain diagFrontLeft (double startPower, double maxPower, double distance, Units units) {
        this.addCommand(new DriveTrainDiagFrontLeftCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    public SimpleDriveTrain diagFrontRight (double startPower, double maxPower, double distance, Units units) {
        this.addCommand(new DriveTrainDiagFrontRightCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    public SimpleDriveTrain diagRearLeft (double startPower, double maxPower, double distance, Units units) {
        this.addCommand(new DriveTrainDiagRearLeftCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    public SimpleDriveTrain diagRearRight (double startPower, double maxPower, double distance, Units units) {
        this.addCommand(new DriveTrainDiagRearRightCommand(this, startPower, maxPower, distance, units));
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
     * @param power
     * @param sensor
     * @param target
     * @return
     */
    public SimpleDriveTrain forwardBySensor (double power, DistanceSensor sensor, double target)
    {
        this.addCommand(new DriveTrainForwardBySensorCommand(this, power, sensor, target));
        return this;
    }

    public SimpleDriveTrain forward (double startPower, double maxPower, double distance, Units units)
    {
        this.addCommand(new DriveTrainForwardsCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @return
     */
    public SimpleDriveTrain spinLeftFront (Direction direction, double startPower, double maxPower, double distance)
    {
        if (!direction.equals(Direction.FORWARD) && !direction.equals(Direction.REVERSE)) {
            direction = Direction.FORWARD;
        }

        DcMotorSimple.Direction translatedDirection = direction.equals(Direction.FORWARD) ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;

        this.addCommand(new DriveTrainSpinMotorCommand(
                this,
                1, 0, 0, 0,
                translatedDirection, startPower, maxPower, distance, Units.Centimeters));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @return
     */
    public SimpleDriveTrain spinRightFront (Direction direction, double startPower, double maxPower, double distance)
    {
        if (!direction.equals(Direction.FORWARD) && !direction.equals(Direction.REVERSE)) {
            direction = Direction.FORWARD;
        }

        DcMotorSimple.Direction translatedDirection = direction.equals(Direction.FORWARD) ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;

        this.addCommand(new DriveTrainSpinMotorCommand(
                this,
                0, 1, 0, 0,
                translatedDirection, startPower, maxPower, distance, Units.Centimeters));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @return
     */
    public SimpleDriveTrain spinRightRear (Direction direction, double startPower, double maxPower, double distance)
    {
        if (!direction.equals(Direction.FORWARD) && !direction.equals(Direction.REVERSE)) {
            direction = Direction.FORWARD;
        }

        DcMotorSimple.Direction translatedDirection = direction.equals(Direction.FORWARD) ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;

        this.addCommand(new DriveTrainSpinMotorCommand(
                this,
                0, 0, 1, 0,
                translatedDirection, startPower, maxPower, distance, Units.Centimeters));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @return
     */
    public SimpleDriveTrain spinLeftRear (Direction direction, double startPower, double maxPower, double distance)
    {
        if (!direction.equals(Direction.FORWARD) && !direction.equals(Direction.REVERSE)) {
            direction = Direction.FORWARD;
        }

        DcMotorSimple.Direction translatedDirection = direction.equals(Direction.FORWARD) ? DcMotorSimple.Direction.FORWARD : DcMotorSimple.Direction.REVERSE;

        this.addCommand(new DriveTrainSpinMotorCommand(
                this,
                0, 0, 0, 1,
                translatedDirection, startPower, maxPower, distance, Units.Centimeters));
        return this;
    }

    /**
     *
     *
     */
    public double getYaw () {
        return this.robot.getYaw();
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain gyroTurn (Direction direction, double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        switch (direction) {
            case RIGHT:
                return this.gyroTurnRight(startPower, maxPower, degrees, orientation);
            case LEFT:
                return this.gyroTurnLeft(startPower, maxPower, degrees, orientation);
        }

       return this;
    }


    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain gyroTurnLeft (double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroTurnLeftCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain gyroTurnRight (double startPower, double maxPower, double degrees,
                                           AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroTurnRightCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain frontAxelPivot (Direction direction, double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        switch (direction) {
            case RIGHT:
                return this.frontAxelPivotRight(startPower, maxPower, degrees, orientation);
            case LEFT:
                return this.frontAxelPivotLeft(startPower, maxPower, degrees, orientation);
        }

        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain frontAxelPivotLeft (double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroFrontAxlePivotLeftCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain frontAxelPivotRight (double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroFrontAxlePivotRightCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    public boolean isBrakeOn () {
        return this.motorGroup.isBrakeOn();
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain rearAxelPivotLeft (double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroRearAxlePivotLeftCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param degrees
     * @return
     */
    public SimpleDriveTrain rearAxelPivotRight (double startPower, double maxPower, double degrees, AbstractDriveTrainGyroTurnCommand.Orientation orientation)
    {
        this.addCommand(new DriveTrainGyroRearAxlePivotRightCommand(this, startPower, maxPower, degrees, orientation));
        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @return
     */
    public SimpleDriveTrain sideways (Direction direction, double startPower, double maxPower, double distance, Units units) {

        switch (direction) {
            case RIGHT:
                return this.sidewaysRight(startPower, maxPower, distance, units);
            case LEFT:
                return this.sidewaysLeft(startPower, maxPower, distance, units);
        }

        return this;
    }

    /**
     *
     * @param direction
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @param sensor
     * @param threshold
     * @return
     */
    public SimpleDriveTrain scanSideways (
            Direction direction,
            double startPower,
            double maxPower,
            double distance,
            Units units,
            DistanceSensor sensor,
            double threshold,
            CommandCallbackHandler handler) {
        switch (direction) {
            case RIGHT:
                return this.scanSidewaysRight(startPower, maxPower, distance, units, sensor, threshold, handler);
            case LEFT:
                return this.scanSidewaysLeft(startPower, maxPower, distance, units, sensor, threshold, handler);
        }

        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @return
     */
    public SimpleDriveTrain sidewaysLeft (double startPower, double maxPower, double distance, Units units)
    {
        this.addCommand(new DriveTrainSidewaysLeftCommand(this, startPower, maxPower, distance, units));
        return this;
    }


    /**
     *
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @param sensor
     * @param threshold
     * @param handler
     * @return
     */
    public SimpleDriveTrain scanSidewaysLeft (double startPower, double maxPower, double distance, Units units, DistanceSensor sensor, double threshold, CommandCallbackHandler handler)
    {
        DriveTrainScanSidewaysLeftCommand command = new DriveTrainScanSidewaysLeftCommand(this, startPower, maxPower, distance, units, sensor, threshold);
        command.addCallbackHandler(handler);

        this.addCommand(command);
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @return
     */
    public SimpleDriveTrain sidewaysRight (double startPower, double maxPower, double distance, Units units)
    {
        this.addCommand(new DriveTrainSidewaysRightCommand(this, startPower, maxPower, distance, units));
        return this;
    }

    /**
     *
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     * @param sensor
     * @param threshold
     * @param handler
     * @return
     */
    public SimpleDriveTrain scanSidewaysRight (double startPower, double maxPower, double distance, Units units, DistanceSensor sensor, double threshold, CommandCallbackHandler handler)
    {
        DriveTrainScanSidewaysRightCommand command = new DriveTrainScanSidewaysRightCommand(this, startPower, maxPower, distance, units, sensor, threshold);
        command.addCallbackHandler(handler);

        this.addCommand(command);
        return this;
    }

    /**
     *
     */
    public void resetYaw () {
        this.robot.resetYaw();
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
     */
    public void setBrakeOn () {
        this.motorGroup.setBrakeOn();
    }

    public void setBrakeOff() {
        this.motorGroup.setBrakeOff();
    }

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
        this.motorGroup.setPower(0);
        this.motorGroup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.motorGroup.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     *
     * @param milliseconds
     * @return
     */
    public SimpleDriveTrain wait (int milliseconds) {
        return (SimpleDriveTrain) super.wait(milliseconds);
    }

    /**
     *
     * @param milliseconds
     * @param handler
     * @return
     */
    public SimpleDriveTrain wait (int milliseconds, CommandCallbackHandler handler) {
        return (SimpleDriveTrain) super.wait(milliseconds, handler);
    }
}
