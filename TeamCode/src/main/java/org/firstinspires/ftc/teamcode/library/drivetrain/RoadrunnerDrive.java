package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 *
 */
public class RoadrunnerDrive extends MecanumDrive {

    /**
     *
     * @param hardwareMap
     * @param pose
     */
    public RoadrunnerDrive(HardwareMap hardwareMap, Pose2d pose) {
        super(hardwareMap, pose);
    }

    /**
     *
     * @param leftFrontPow
     * @param rightFrontPow
     * @param rightBackPow
     * @param leftBackPow
     */
    public void setDrivePowers(double leftFrontPow, double rightFrontPow, double rightBackPow, double leftBackPow) {
        leftFront.setPower(leftFrontPow);
        leftBack.setPower(leftBackPow);
        rightBack.setPower(rightBackPow);
        rightFront.setPower(leftBackPow);
    }
}
