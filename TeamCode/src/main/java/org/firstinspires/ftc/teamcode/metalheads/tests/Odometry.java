package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Odometry Test")
public class Odometry extends LinearOpMode {

    // Declare hardware components
    private DcMotor leftEncoder;
    private DcMotor rightEncoder;
    private DcMotor centerEncoder;
    private IMU imu;

    // Robot's current position and orientation
    private double delta_y = 0;
    private double delta_x = 0;
    private double x = 0;
    private double y = 0;
    private double heading = 0;

    // Constants for odometry calculations
    //private static final double WHEEL_RADIUS = 0.024;
    private static final double TRACK_WIDTH = 938;
    private static final double FORWARD_OFFSET = 834;

    // Previous encoder values
    private double prev_left_encoder_pos = 0;
    private double prev_right_encoder_pos = 0;
    private double prev_center_encoder_pos = 0;

    @Override
    public void runOpMode() {
        // Initialize hardware components
        leftEncoder = hardwareMap.get(DcMotor.class, "leftOmni");
        rightEncoder = hardwareMap.get(DcMotor.class, "rightOmni");
        centerEncoder = hardwareMap.get(DcMotor.class, "rearOmni");
        imu = hardwareMap.get(IMU.class, "imu");

        // Wait for the start command
        waitForStart();

        /*

        delta_left_encoder_pos = left_encoder_pos - prev_left_encoder_pos
        delta_right_encoder_pos = right_encoder_pos - prev_right_encoder_pos
        delta_center_encoder_pos = center_encoder_pos - prev_center_encoder_pos

        phi = (delta_left_encoder_pos - delta_right_encoder_pos) / trackwidth
        delta_middle_pos = (delta_left_encoder_pos + delta_right_encoder_pos) / 2
        delta_perp_pos = delta_center_encoder_pos - forward_offset * phi

        delta_x = delta_middle_pos * cos(heading) - delta_perp_pos * sin(heading)
        delta_y = delta_middle_pos * sin(heading) + delta_perp_pos * cos(heading)

        x_pos += delta_x
        y_pos += delta_y
        heading += phi

        prev_left_encoder_pos = left_encoder_pos
        prev_right_encoder_pos = right_encoder_pos
        prev_center_encoder_pos = center_encoder_pos


         */

        // Main loop
        while (opModeIsActive()) {

            // Calculate deltas
            double delta_left_encoder_pos = leftEncoder.getCurrentPosition() - prev_left_encoder_pos;
            double delta_right_encoder_pos = -rightEncoder.getCurrentPosition() - prev_right_encoder_pos;
            double delta_center_encoder_pos = centerEncoder.getCurrentPosition() - prev_center_encoder_pos;

            telemetry.addData("delta_left_encoder_pos", delta_left_encoder_pos);
            telemetry.addData("delta_right_encoder_pos", delta_right_encoder_pos);
            telemetry.addData("delta_center_encoder_pos", delta_center_encoder_pos);

            double phi = (delta_left_encoder_pos - delta_right_encoder_pos) / TRACK_WIDTH;
            double delta_middle_pos = (delta_left_encoder_pos + delta_right_encoder_pos) / 2;
            double delta_perp_pos = delta_center_encoder_pos - (FORWARD_OFFSET * phi);

            telemetry.addData("phi", phi);
            telemetry.addData("delta_middle_pos", delta_middle_pos);
            telemetry.addData("delta_perp_pos", delta_perp_pos);

            delta_x = delta_middle_pos * Math.cos(heading) - delta_perp_pos * Math.sin(heading);
            delta_y = delta_middle_pos * Math.sin(heading) - delta_perp_pos * Math.cos(heading);

            telemetry.addData("delta_x", delta_x);
            telemetry.addData("delta_y", delta_y);

            x += delta_x;
            y += delta_y;
            heading += phi;

            // Update previous values
            prev_left_encoder_pos = leftEncoder.getCurrentPosition();
            prev_right_encoder_pos = -rightEncoder.getCurrentPosition();
            prev_center_encoder_pos = centerEncoder.getCurrentPosition();

            // Output the current position
            telemetry.addData("Heading: ", heading);
            telemetry.addData("X: ", x);
            telemetry.addData("Y: ", y);
            telemetry.addData("X cm: ", x * 0.24);
            telemetry.addData("Y cm: ", y * 0.24);

            telemetry.addData("IMU Degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));

            telemetry.update();

        }
    }
}
