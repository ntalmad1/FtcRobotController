package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp(name = "TouchSensorTest", group = "Tests")
//@Disabled
public class TouchSensorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RevTouchSensor touchSensor = this.hardwareMap.get(RevTouchSensor.class, "viperSlideStopper");
        touchSensor.resetDeviceConfigurationForOpMode();

        waitForStart();

        while (this.opModeIsActive()) {
            telemetry.addData("Touch Sensor: ", touchSensor.isPressed());
            telemetry.update();
        }
    }
}
