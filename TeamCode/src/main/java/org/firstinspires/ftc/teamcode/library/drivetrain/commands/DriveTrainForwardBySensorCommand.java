package org.firstinspires.ftc.teamcode.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Units;

import java.util.ArrayList;

public class DriveTrainForwardBySensorCommand extends AbstractDriveTrainLineCommand {

    /**
     *
     */
    private DistanceSensor sensor;

    /**
     */
    private ArrayList<Double> pings = new ArrayList<Double>();

    /**
     *
     */
    private double target;

    /**
     * Constructor
     *
     * @param driveTrain
     * @param power
     * @param sensor
     * @param target
     */
    public DriveTrainForwardBySensorCommand(SimpleDriveTrain driveTrain, double power, DistanceSensor sensor, double target) {
        super(driveTrain, power, power, 100, Units.Centimeters);

        this.sensor = sensor;
        this.target = target;
    }

    /**
     *
     */
    public void init () {
        this.driveTrain.getLeftFrontMotor().setDirection(DcMotorSimple.Direction.REVERSE);
        this.driveTrain.getLeftRearMotor().setDirection(DcMotorSimple.Direction.REVERSE);

        this.driveTrain.getRightFrontMotor().setDirection(DcMotorSimple.Direction.FORWARD);
        this.driveTrain.getRightRearMotor().setDirection(DcMotorSimple.Direction.FORWARD);

        super.init();
    }

    /**
     *
     */
    public void run () {

        double distance = this.sensor.getDistance(DistanceUnit.CM);

        if (this.pings.isEmpty()) {
            this.pings.add(distance);
        }
        else {
            if (Math.abs(this.pings.get(0) - distance) < 1) {
                this.pings.add(distance);
            }
            else {
                this.pings.clear();
            }
        }


        if (distance <= this.target || this.pings.size() > 15)
        {
            this.markAsCompleted();

            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            return;
        }

        super.run();
    }
}
