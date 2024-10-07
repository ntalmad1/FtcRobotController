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

    final double maxVoltage = 10.3;

    public void runOpMode() {

        pot = hardwareMap.get(AnalogInput.class, "pot");

        waitForStart();
        while (this.opModeIsActive()) {

            currentVoltage = pot.getVoltage();

            currentPosition = currentVoltage / maxVoltage;

            telemetry.addData("CurrentPosition : %", currentPosition);
            telemetry.update();

        }
    }
}