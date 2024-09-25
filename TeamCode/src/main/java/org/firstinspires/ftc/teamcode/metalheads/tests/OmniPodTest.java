package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="OmniPodTest", group="Tests")
@Disabled
public class OmniPodTest extends LinearOpMode {

    private DcMotor motor;

    private DcMotor encoder;

    /**
     *
     */
    public void runOpMode() {

        motor  = hardwareMap.get(DcMotor.class, "motor3");

        encoder = hardwareMap.get(DcMotor.class, "motor3");

        encoder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.waitForStart();

        while (this.opModeIsActive()) {

            motor.setPower(this.gamepad1.left_stick_y);

            telemetry.addData("Power (%.2f)", this.motor.getPower());
            telemetry.addData("Pos (%.2f)", this.motor.getCurrentPosition());

            telemetry.update();
        }
    }
}
