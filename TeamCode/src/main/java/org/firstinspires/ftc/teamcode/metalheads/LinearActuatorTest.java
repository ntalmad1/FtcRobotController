package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.IsaacBot;

@TeleOp(name="Linear Actuator Test", group="Linear OpMode")
//@Disabled
public class LinearActuatorTest extends LinearOpMode {

    private DcMotor linearActMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        linearActMotor  = hardwareMap.get(DcMotor.class, "actuatorMotor");

        linearActMotor.setDirection(DcMotor.Direction.FORWARD);
        linearActMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearActMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //linearActMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int targetPosition = 0;
        double power = 0;

        this.waitForStart();

        while (this.opModeIsActive()) {

            // max position 3530 - hard stop
            // soft max stop 3400 - soft stop
            // soft min 1500

            int ticIncrements = 200;
            int softMax = 3400;
            int softMin = 1500;

            if (gamepad1.right_stick_x > 0) {
                targetPosition = linearActMotor.getCurrentPosition() + ticIncrements;
                if (targetPosition > softMax) {
                    targetPosition = softMax;
                }

                linearActMotor.setTargetPosition(targetPosition);

                linearActMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                power = 1 * gamepad1.right_stick_x;

                linearActMotor.setPower(power);
            }
            if (gamepad1.right_stick_x < 0) {
                targetPosition = linearActMotor.getCurrentPosition() - ticIncrements;
                if (targetPosition < softMin) {
                    targetPosition = softMin;
                }

                linearActMotor.setTargetPosition(targetPosition);

                linearActMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                power = 1 * gamepad1.right_stick_x;

                linearActMotor.setPower(power);
            }
            else {
                linearActMotor.setPower(0);
                linearActMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }

            telemetry.addData("Power: ", "%2f", power);
            telemetry.addData("Position: ", "%7d", linearActMotor.getCurrentPosition());
            telemetry.addData("TargetPosition: ", "%7d", targetPosition);
            telemetry.update();
        }
    }
}
