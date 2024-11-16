package org.firstinspires.ftc.teamcode.metalheads.compbot;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 * Private class
 *
 */
public class TrajectoryFactory {

    /**
     */
    private AutoBot autoBot;

    /**
     */
    public TrajectoryFactory(AutoBot autoBot) {
        this.autoBot = autoBot;
    }

    /**
     * First Line forwards form starting position
     * @return tab2
     */
    public TrajectoryActionBuilder lineToChamber(MecanumDrive drive, Pose2d initialPose) {
        return drive.actionBuilder(initialPose)
                .lineToY(-39);
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder lineToPlaceSpeciman(MecanumDrive drive) {
        return drive.actionBuilder(drive.pose)
                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder pushSamples(MecanumDrive drive) {

        double velocityLow = 25;

        return drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(0))




                ;
    }
}
