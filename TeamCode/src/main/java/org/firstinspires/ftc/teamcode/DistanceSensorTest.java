package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorREV2mDistance;
import org.firstinspires.ftc.teamcode.library.IsaacBot;

/**
 *
 */
@TeleOp(name="Distance Sensor", group="Linear OpMode")
//@Disabled
public class DistanceSensorTest extends IsaacBot {



    @Override
    public void runOpMode() throws InterruptedException {

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "distanceSensor");

        waitForStart();

        while (this.opModeIsActive()) {
            double distance = distanceSensor.getDistance(DistanceUnit.CM);

            this.telemetry.addData("Distance: ", "%2f", distance);
            this.telemetry.update();
        }
    }
}
