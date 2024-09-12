/* Copyright (c) 2023 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

/*
 * This OpMode illustrates the basics of AprilTag recognition and pose estimation, using
 * the easy way.
 *
 * For an introduction to AprilTags, see the FTC-DOCS link below:
 * https://ftc-docs.firstinspires.org/en/latest/apriltag/vision_portal/apriltag_intro/apriltag-intro.html
 *
 * In this sample, any visible tag ID will be detected and displayed, but only tags that are included in the default
 * "TagLibrary" will have their position and orientation information displayed.  This default TagLibrary contains
 * the current Season's AprilTags and a small set of "test Tags" in the high number range.
 *
 * When an AprilTag in the TagLibrary is detected, the SDK provides location and orientation of the tag, relative to the camera.
 * This information is provided in the "ftcPose" member of the returned "detection", and is explained in the ftc-docs page linked below.
 * https://ftc-docs.firstinspires.org/apriltag-detection-values
 *
 * To experiment with using AprilTags to navigate, try out these two driving samples:
 * RobotAutoDriveToAprilTagOmni and RobotAutoDriveToAprilTagTank
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list.
 */
@TeleOp(name = "CameraTest", group = "Concept")
@Disabled
public class CameraTest extends LinearOpMode {

    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    private DcMotor leftFrontMotor = null;
    private DcMotor rightFrontMotor = null;
    private DcMotor leftRearMotor = null;
    private DcMotor rightRearMotor = null;

    private double power = 0;

    //place values for setting drivetrain to forwards and reverse
    private int forward = 1;
    private int reverse = 2;
    private int left = 3;
    private int right = 4;
    private int rotleft = 5;
    private int rotright = 6;


    /**
     * The variable to store our instance of the AprilTag processor.
     */
    private AprilTagProcessor aprilTag;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    @Override
    public void runOpMode() {

        initDriveTrain();

        initAprilTag();

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                power = 0;

//                telemetryAprilTag();
//                telemetry.update();

                while (gamepad1.dpad_up) {
                    keepDistanceY(12, .15, 0.8, 1);
                }
                while (gamepad1.dpad_down) {
                    keepDistanceX(0, .15, 0.8, 1);
                }
                while (gamepad1.dpad_left) {
                    //gap is in degrees
                    keepDistanceYAW(0, .15, 0.8, 1);
                }
            }

            leftRearMotor.setPower(power);
            leftFrontMotor.setPower(power);
            rightRearMotor.setPower(power);
            rightFrontMotor.setPower(power);
        }

        // Save more CPU resources when camera is no longer needed.
        visionPortal.close();

    }   // end method runOpMode()




    /**
     *
     * @param inches
     * @param ID
     */
    private void keepDistanceY(double inches,double speed, double gap, int ID) {

        //tolerance for distance
        gap = (gap/2);

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        //check if any april tags are seen
        if (currentDetections.isEmpty()) {
            power = 0;
        } else {
            //find desired ID
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == ID) {

                    power = speed;

                    this.telemetry.addData("power: ", "%2f", power);
                    this.telemetry.addData("gap: ", "%2f", gap);
                    this.telemetry.addData("Distance: ", "%2f", detection.ftcPose.y);
                    telemetry.update();

                    //The .2 gives it a .4 inch gap for the target position
                    //move towards desired position
                    if (detection.ftcPose.y < (inches - gap)) {
                        motorDirection(reverse);
                    }
                    if (detection.ftcPose.y > (inches + gap)) {
                        motorDirection(forward);
                    }

                }
            }
        }
        leftRearMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightRearMotor.setPower(power);
        rightFrontMotor.setPower(power);


    }




    private void keepDistanceX(double offset,double speed, double gap, int ID) {

        //tolerance for distance
        gap = (gap/2);

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        //check if any april tags are seen
        if (currentDetections.isEmpty()) {
            power = 0;
        } else {
            //find desired ID
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == ID) {

                    power = speed;

                    this.telemetry.addData("power: ", "%2f", power);
                    this.telemetry.addData("gap: ", "%2f", gap);
                    this.telemetry.addData("Distance: ", "%2f", detection.ftcPose.x);
                    telemetry.update();

                    //The .2 gives it a .4 inch gap for the target position
                    //move towards desired position
                    if (detection.ftcPose.x < (offset - gap)) {
                        motorDirection(left);
                    }
                    if (detection.ftcPose.x > (offset + gap)) {
                        motorDirection(right);
                    }

                }
            }
        }
        leftRearMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightRearMotor.setPower(power);
        rightFrontMotor.setPower(power);
    }


    private void keepDistanceYAW(double offset,double speed, double gap, int ID) {

        //tolerance for distance
        gap = (gap/2);

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        //check if any april tags are seen
        if (currentDetections.isEmpty()) {
            power = 0;
        } else {
            //find desired ID
            for (AprilTagDetection detection : currentDetections) {
                if (detection.id == ID) {

                    power = speed;

                    this.telemetry.addData("power: ", "%2f", power);
                    this.telemetry.addData("gap: ", "%2f", gap);
                    this.telemetry.addData("Distance: ", "%2f", detection.ftcPose.yaw);
                    telemetry.update();

                    //The .2 gives it a .4 inch gap for the target position
                    //move towards desired position
                    if (detection.ftcPose.yaw < (offset - gap)) {
                        motorDirection(rotright);
                    }
                    if (detection.ftcPose.yaw > (offset + gap)) {
                        motorDirection(rotleft);
                    }

                }
            }
        }
        leftRearMotor.setPower(power);
        leftFrontMotor.setPower(power);
        rightRearMotor.setPower(power);
        rightFrontMotor.setPower(power);
    }



    /**
     *
     */
    private void initDriveTrain() {

        leftFrontMotor = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontMotor = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftRearMotor = hardwareMap.get(DcMotor.class, "leftRearDrive");
        rightRearMotor = hardwareMap.get(DcMotor.class, "rightRearDrive");

        motorDirection(forward);

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    private void motorDirection(int direction) {

        if (direction == 1) {

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

        } else if (direction == 2 ) {

            leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
            rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.REVERSE);

        } else if (direction == 3 ) {

            leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

        } else if (direction == 4 ) {

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            rightRearMotor.setDirection(DcMotor.Direction.REVERSE);

        } else if (direction == 5 ) {

            leftFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            leftRearMotor.setDirection(DcMotor.Direction.FORWARD);
            rightFrontMotor.setDirection(DcMotor.Direction.FORWARD);
            rightRearMotor.setDirection(DcMotor.Direction.FORWARD);

        } else if (direction == 6 ) {

            leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            leftRearMotor.setDirection(DcMotor.Direction.REVERSE);
            rightFrontMotor.setDirection(DcMotor.Direction.REVERSE);
            rightRearMotor.setDirection(DcMotor.Direction.REVERSE);
        }

    }




    /**
     * Initialize the AprilTag processor.
     */

    private void initAprilTag() {

        // Create the AprilTag processor the easy way.
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        if (USE_WEBCAM) {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    hardwareMap.get(WebcamName.class, "Webcam 1"), aprilTag);
        } else {
            visionPortal = VisionPortal.easyCreateWithDefaults(
                    BuiltinCameraDirection.BACK, aprilTag);
        }

    }   // end method initAprilTag()

    /**
     * Add telemetry about AprilTag detections.
     */
    private void telemetryAprilTag() {

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();
        telemetry.addData("# AprilTags Detected", currentDetections.size());

        // Step through the list of detections and display info for each one.
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                telemetry.addLine(String.format("\n==== (ID %d) %s", detection.id, detection.metadata.name));
                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f  (inch)", detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z));
                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f  (deg)", detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));
                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f  (inch, deg, deg)", detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            } else {
                telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
                telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            }
        }   // end for() loop

        // Add "key" information to telemetry
        telemetry.addLine("\nkey:\nXYZ = X (Right), Y (Forward), Z (Up) dist.");
        telemetry.addLine("PRY = Pitch, Roll & Yaw (XYZ Rotation)");
        telemetry.addLine("RBE = Range, Bearing & Elevation");

    }   // end method telemetryAprilTag()

}   // end class


