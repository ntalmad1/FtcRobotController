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
     * @param powers
     */
    public void setDrivePowers(Powers powers) {
        leftFront.setPower(powers.leftFront);
        leftBack.setPower(powers.leftBack);
        rightBack.setPower(powers.rightBack);
        rightFront.setPower(powers.rightFront);
    }
}
