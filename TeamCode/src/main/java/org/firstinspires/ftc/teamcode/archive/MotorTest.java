package org.firstinspires.ftc.teamcode.archive;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.IsaacBot;


@TeleOp(name="MotorTest", group="Linear OpMode")
@Disabled
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

        int targetPositition = 0;

        waitForStart();

        while (this.opModeIsActive()) {

            if (gamepad1.right_stick_x > 0) {
                targetPositition = motor.getCurrentPosition() + 20;

                motor.setTargetPosition(targetPositition);

                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                motor.setPower(gamepad1.right_stick_x);
            }
            else if (gamepad1.right_stick_x < 0) {
                targetPositition = motor.getCurrentPosition() - 20;
                motor.setTargetPosition(targetPositition);
                motor.setDirection(DcMotor.Direction.REVERSE);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                motor.setPower(Math.abs(gamepad1.right_stick_x));
            }
            else {
                motor.setTargetPosition(targetPositition);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(1);
            }


            telemetry.addData("Current Position",  "%7d", motor.getCurrentPosition());
            telemetry.addData("Target Position",  "%7d", targetPositition);
            telemetry.addData("Power",  "%7d", motor.getPower());
            telemetry.update();
        }
    }
}
