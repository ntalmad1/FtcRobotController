package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp(name="ArmTest", group="Tests")
// @Disabled
public class ArmTest extends LinearOpMode {



    public void runOpMode() {

        CRServo intake = hardwareMap.get(CRServo.class, "intakeMain");

        waitForStart();
        while (this.opModeIsActive()) {

            intake.setPower(gamepad1.left_stick_y);

        }
    }
}