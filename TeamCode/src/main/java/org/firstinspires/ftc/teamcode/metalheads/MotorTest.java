package org.firstinspires.ftc.teamcode.metalheads;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.library.IsaacBot;

@TeleOp(name="MotorTest", group="Linear OpMode")
//@Disabled
public class MotorTest extends IsaacBot {

    //537.7 tick per revolution

    private DcMotor motor = null;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        motor = hardwareMap.get(DcMotor.class, "winchMotor");
        motor.setDirection(DcMotor.Direction.FORWARD);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();

        while (this.opModeIsActive()) {

            int softMax = 10000;
            int softMin = -10000;

            if (gamepad1.right_stick_x > 0) {

                motor.setTargetPosition(softMax);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setPower(1*gamepad1.right_stick_x);
            }

            if (gamepad1.right_stick_x < 0) {

                motor.setTargetPosition(softMin);
                motor.setDirection(DcMotor.Direction.REVERSE);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                motor.setPower(Math.abs(1*gamepad1.right_stick_x));
            }


            telemetry.addData("Current Position",  "%7d", motor.getCurrentPosition());
            telemetry.addData("Target Position",  "%7d", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
