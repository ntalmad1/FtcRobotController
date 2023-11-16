package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbstractDriveTrainScanCommand extends AbstractDriveTrainLineCommand implements IScanCommand {

    /**
     *
     */
    protected double threshold;

    /**
     *
     */
    protected DistanceSensor sensor;

    protected List<Double> pingResults = new ArrayList<Double>();

    protected double distance;

    /**
     * Constructor
     *
     * @param driveTrain
     * @param startPower
     * @param maxPower
     * @param distance
     * @param units
     */
    public AbstractDriveTrainScanCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units, DistanceSensor sensor, double threshold) {
        super(driveTrain, startPower, maxPower, distance, units);

        this.sensor = sensor;
        this.threshold = threshold;
    }

    /**
     *
     * @return
     */
    public double getDistance () {
        return this.distance;
    }


    /**
     *
     */
    public void run () {

        double distance = this.sensor.getDistance(DistanceUnit.CM);

        if (distance < threshold) {

            if (this.pingResults.isEmpty()) {
                this.pingResults.add(distance);
            }
            else {

                double prevDist = this.pingResults.get(pingResults.size() - 1);

                if (Math.abs(distance - prevDist) < 3) {
                    this.pingResults.add(distance);
                }

                if (this.pingResults.size() > 10) {

                    this.distance = distance;
                    this.driveTrain.getMotorGroup().setPower(0);
                    this.markAsCompleted();
                    return;
                }
            }
        }

        super.run();
    }
}
