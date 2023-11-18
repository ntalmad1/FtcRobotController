package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Distance Sensor", group="Linear OpMode")
@Disabled
public class DistanceSensorTest extends IsaacBot {



    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor pixelLeftSensor = hardwareMap.get(DistanceSensor.class, "leftPixelSensor");
        DistanceSensor pixelRightSensor = hardwareMap.get(DistanceSensor.class, "rightPixelSensor");

        waitForStart();

        while (this.opModeIsActive()) {
            double distanceLeft = pixelLeftSensor.getDistance(DistanceUnit.MM);
            double distanceRight = pixelRightSensor.getDistance(DistanceUnit.MM);

            this.telemetry.addData("Left: ", "%2f", distanceLeft);
            this.telemetry.addData("Right: ", "%2f", distanceRight);
            this.telemetry.update();
        }
    }
}
