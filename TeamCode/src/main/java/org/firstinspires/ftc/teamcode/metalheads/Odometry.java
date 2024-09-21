package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Odometry Test")
public class Odometry extends LinearOpMode {

    // Declare hardware components
    private DcMotor leftOdometryWheel;
    private DcMotor rightOdometryWheel;
    private DcMotor perpendicularWheel;
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
    private double previousLeftDistance = 0;
    private double previousRightDistance = 0;
    private double previousRearDistance = 0;

    @Override
    public void runOpMode() {
        // Initialize hardware components
        leftOdometryWheel = hardwareMap.get(DcMotor.class, "leftOmni");
        rightOdometryWheel = hardwareMap.get(DcMotor.class, "rightOmni");
        perpendicularWheel = hardwareMap.get(DcMotor.class, "rearOmni");
        imu = hardwareMap.get(IMU.class, "imu");

        // Wait for the start command
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Read encoder values
            double leftDistance = leftOdometryWheel.getCurrentPosition();
            double rightDistance = rightOdometryWheel.getCurrentPosition();
            double rearDistance = perpendicularWheel.getCurrentPosition();


            // Calculate deltas
            double deltaLeft = leftDistance - previousLeftDistance;
            double deltaRight = rightDistance - previousRightDistance;
            double deltaRear = rearDistance - previousRearDistance;


            double phi = (deltaLeft - deltaRight) / TRACK_WIDTH;
            double deltaMiddle = (deltaLeft + deltaRight) / 2;
            double deltaPerp = (deltaRear - FORWARD_OFFSET) * phi;


            delta_x += deltaMiddle * Math.cos(heading) - deltaPerp * Math.sin(heading);
            delta_y += deltaMiddle * Math.sin(heading) - deltaPerp * Math.cos(heading);


            x += delta_x; // Update X based on perpendicular wheel
            y += delta_y; // Update Y based on perpendicular wheel
            heading -= phi;

            // Update previous values
            previousLeftDistance = leftDistance;
            previousRightDistance = rightDistance;
            previousRearDistance = rearDistance;

            // Output the current position
            telemetry.addData("DeltaLeft: ", deltaLeft);
            telemetry.addData("DeltaRight: ", deltaRight);
            telemetry.addData("DeltaRear: ", deltaRear);

            telemetry.addData("Phi: ", phi);
            telemetry.addData("Heading: ", heading);
            telemetry.addData("Delta_x: ", delta_x);
            telemetry.addData("Delta_y: ", delta_y);
            telemetry.addData("DeltaPerp: ", deltaPerp);

            telemetry.addData("X: ", x);
            telemetry.addData("Y: ", y);
            telemetry.addData("Degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.update();

        }
    }
}
