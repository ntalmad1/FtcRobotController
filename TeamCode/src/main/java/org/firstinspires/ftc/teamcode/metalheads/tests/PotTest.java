package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.AnalogInput;

@TeleOp(name="PotentiometerTest", group="Tests")
public class PotTest extends LinearOpMode {

    AnalogInput pot;
    double currentVoltage;
    double currentPosition; //In percentage

    final double maxVoltage = 3.327;

    public void runOpMode() {

        pot = hardwareMap.get(AnalogInput.class, "pot");

        waitForStart();
        while (this.opModeIsActive()) {

            currentVoltage = pot.getVoltage();

            currentPosition = 100.0 * (currentVoltage / maxVoltage);

            telemetry.addData("CurrentPosition: %", currentPosition);
            telemetry.addData("CurrentVoltage: ", currentVoltage);
            telemetry.update();

        }
    }
}