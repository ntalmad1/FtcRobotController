package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 *
 */
@TeleOp(name="Distance Sensor Test", group="Tests")
//@Disabled
public class DistanceSensorTest extends IsaacBot {



    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor pixelLeftSensor = hardwareMap.get(DistanceSensor.class, "leftTokenSensor");
        Rev2mDistanceSensor leftSensor = (Rev2mDistanceSensor) pixelLeftSensor;

        DistanceSensor pixelRightSensor = hardwareMap.get(DistanceSensor.class, "rightTokenSensor");
        Rev2mDistanceSensor rightSensor = (Rev2mDistanceSensor) pixelRightSensor;

        DistanceSensor tokenFrontSensor = hardwareMap.get(DistanceSensor.class, "tokenSensor");
        Rev2mDistanceSensor frontSensor = (Rev2mDistanceSensor) tokenFrontSensor;

        waitForStart();

        while (this.opModeIsActive()) {
            double distanceLeft = leftSensor.getDistance(DistanceUnit.CM);
            double distanceRight = rightSensor.getDistance(DistanceUnit.CM);
            double distanceFront = rightSensor.getDistance(DistanceUnit.CM);

            this.telemetry.addData("Left: ", "%2f", distanceLeft);
            this.telemetry.addData("Right: ", "%2f", distanceRight);
            this.telemetry.addData("Front: ", "%2f", distanceFront);
            this.telemetry.update();
        }
    }
}
