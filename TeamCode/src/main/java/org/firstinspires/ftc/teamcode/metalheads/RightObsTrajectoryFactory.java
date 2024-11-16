package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.TrajectoryFactory;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

/**
 */
public class RightObsTrajectoryFactory extends TrajectoryFactory {

    /**
     * @param autoBot
     */
    public RightObsTrajectoryFactory(AutoBot autoBot) {
        super(autoBot);
    }

    /**
     * @return tab2
     */
    public TrajectoryActionBuilder stepOne_placeSpeciman(MecanumDrive drive, Pose2d initialPose) {
        return null;
//        return drive.actionBuilder(initialPose)
//                .lineToY(-39);

    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder releaseSpeciman(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder stepThree_pushSamples(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder stepFour_arcToSpecimanPick(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder splineToPlaceSpeciman(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder splineToSpecimanPick(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @param drive
     * @return
     */
    public TrajectoryActionBuilder parkInObservation(MecanumDrive drive) {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

}
