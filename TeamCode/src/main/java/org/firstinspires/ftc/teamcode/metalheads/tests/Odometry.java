package org.firstinspires.ftc.teamcode.metalheads.tests;

import android.os.Trace;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp(name = "Odometry Test")
// @Disabled
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
    private static double TRACK_WIDTH = 938;
    private static double FORWARD_OFFSET = 834;

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

        String changing = "Track Width";

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

            //Button Delcration
            //A
            boolean aToggle = false;
            boolean aLoop = false;
            //B
            boolean bToggle = false;
            boolean bloop = false;
            //Dpad_Right
            boolean padrToggle = false;
            boolean padrLoop = false;
            //Dpad_Left
            boolean padlToggle = false;
            boolean padlLoop = false;

            boolean switcher = false;


            //Main Math
            double delta_left_encoder_pos = leftEncoder.getCurrentPosition() - prev_left_encoder_pos;
            double delta_right_encoder_pos = -rightEncoder.getCurrentPosition() - prev_right_encoder_pos;
            double delta_center_encoder_pos = -centerEncoder.getCurrentPosition() - prev_center_encoder_pos;

            double phi = (delta_left_encoder_pos - delta_right_encoder_pos) / TRACK_WIDTH;
            double delta_middle_pos = (delta_left_encoder_pos + delta_right_encoder_pos) / 2;
            double delta_perp_pos = delta_center_encoder_pos - (FORWARD_OFFSET * phi);

            delta_x = delta_middle_pos * Math.cos(heading) - delta_perp_pos * Math.sin(heading);
            delta_y = delta_middle_pos * Math.sin(heading) - delta_perp_pos * Math.cos(heading);

            x += delta_x;
            y += delta_y;
            heading += phi;

            // Update previous values
            prev_left_encoder_pos = leftEncoder.getCurrentPosition();
            prev_right_encoder_pos = -rightEncoder.getCurrentPosition();
            prev_center_encoder_pos = centerEncoder.getCurrentPosition();


            //Odometry Tuning

            //Button A Toggle
            boolean[] buttonA = toggleButton(gamepad1.a,aLoop,aToggle);
            aLoop = buttonA[0];
            aToggle = buttonA[1];

            //button B toggle
            boolean[] buttonB = toggleButton(gamepad1.b,bloop,bToggle);
            bloop = buttonB[0];
            bToggle = buttonB[1];

            //button Dpad_Left toggle
            boolean[] buttonDpadl = toggleButton(gamepad1.dpad_left,padlLoop,padlToggle);
            padlLoop = buttonDpadl[0];
            padlToggle = buttonDpadl[1];

            //button Dpad_Right toggle
            boolean[] buttonDpadr = toggleButton(gamepad1.dpad_right,padrLoop,padrToggle);
            padrLoop = buttonDpadr[0];
            padrToggle = buttonDpadr[1];


            if (aToggle) {
                if (switcher) {
                    switcher = false;
                } else switcher = true;
            }

            if (aToggle) {

                changing = "Forward Offset";

                if (padrToggle) FORWARD_OFFSET += 1;
                if (gamepad1.dpad_up) FORWARD_OFFSET += 5;
                if (padlToggle) FORWARD_OFFSET -= 1;
                if (gamepad1.dpad_down) FORWARD_OFFSET -= 5;

            } else {

                changing = "Track Width";

                if (padrToggle) TRACK_WIDTH += 1;
                if (gamepad1.dpad_up) TRACK_WIDTH += 5;
                if (padlToggle) TRACK_WIDTH -= 1;
                if (gamepad1.dpad_down) TRACK_WIDTH -= 5;

            }

            if (bToggle) {
                heading = 0;
                x = 0;
                y = 0;
            }



            // Output the current position
            telemetry.addData("Changing: ", changing);
            telemetry.addData("Track Width", TRACK_WIDTH);
            telemetry.addData("Forward Offset", FORWARD_OFFSET);
            telemetry.addData("Heading: ", heading);
            telemetry.addData("X: ", x);
            telemetry.addData("Y: ", y);
            telemetry.addData("X cm: ", x * .024);
            telemetry.addData("Y cm: ", y * .024);

            telemetry.addData("Left Omni: ", leftEncoder.getCurrentPosition());
            telemetry.addData("Right Omni: ", rightEncoder.getCurrentPosition());
            telemetry.addData("Front Omni : ", centerEncoder.getCurrentPosition());

            telemetry.addData("IMU Degrees ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
            telemetry.addData("IMU Radians: ", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

            telemetry.update();

        }
    }

    /**
     * A loop Memory variable (boolean[0]), and the actual toggle that when put in a if() loop will run 1 singular time until button is pressed again
     * @return LoopMemory and buttonToggle
     */
    private boolean[] toggleButton(boolean button, boolean loopoff, boolean toggle) {

        if (!loopoff) {
            if (button) {
                loopoff = true;
                toggle = true;
                return new boolean[]{loopoff,toggle};
            }
        }

        toggle = false;
        if (!button) {loopoff = false;}

        return new boolean[]{loopoff,toggle};
    }

}
