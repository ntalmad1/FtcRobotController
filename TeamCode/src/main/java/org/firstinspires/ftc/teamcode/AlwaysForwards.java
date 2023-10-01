package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.library.utility.GridUtils;
import org.firstinspires.ftc.teamcode.library.utility.Point;


/**
 *
 */
@TeleOp(name="Always Forwards", group="Linear OpMode")
//@Disabled
public class AlwaysForwards extends LinearOpMode {

    @Override
    public void runOpMode() {
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("backRightMotor");
        frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        IMU imu;
        imu = hardwareMap.get(IMU.class,"imu");
        imu.resetYaw();
        YawPitchRollAngles gyro;

        waitForStart();

        if (isStopRequested()) return;

        while (this.opModeIsActive())
        {
            gyro = imu.getRobotYawPitchRollAngles();
            double yaw = gyro.getYaw(AngleUnit.DEGREES);

            float leftY = gamepad1.left_stick_y * (float) -1;
            float leftX = gamepad1.left_stick_x;
            float ry = gamepad1.right_stick_y * (float) -1;
            float rx = gamepad1.right_stick_x;

            Point newPoint = GridUtils.rotatePointByDegrees(leftX,leftY,yaw);
            double x = newPoint.getX();
            double y = newPoint.getY();

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio,
            // but only if at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeftMotor.setPower(frontLeftPower);
            backLeftMotor.setPower(backLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backRightMotor.setPower(backRightPower);

            telemetry.addData("Left X: ", "%.2f", leftX);
            telemetry.addData("Left Y: ", "%.2f", leftY);
            telemetry.addData("Right Y: ", "%.2f", ry);
            telemetry.addData("Right X: ", "%.2f", rx);
            telemetry.addData("Yaw: ", "%.2f", yaw);
            telemetry.addData("Rotated X: ", "%.2f", x);
            telemetry.addData("Rotated Y: ", "%.2f", y);
            telemetry.update();

        }
    }
}
