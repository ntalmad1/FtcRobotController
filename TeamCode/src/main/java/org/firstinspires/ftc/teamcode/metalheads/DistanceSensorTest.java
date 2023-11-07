package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Distance Sensor", group="Linear OpMode")
//@Disabled
public class DistanceSensorTest extends IsaacBot {



    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "testDistanceSensor");

        waitForStart();

        while (this.opModeIsActive()) {
            double distance = distanceSensor.getDistance(DistanceUnit.CM);

            this.telemetry.addData("Distance: ", "%2f", distance);
            this.telemetry.update();
        }
    }
}
