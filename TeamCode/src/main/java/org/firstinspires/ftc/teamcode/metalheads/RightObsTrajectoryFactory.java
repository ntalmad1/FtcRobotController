package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.TrajectoryFactory;

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
    public TrajectoryActionBuilder stepOne_placeSpeciman(Pose2d initialPose) {
        return null;
//        return drive.actionBuilder(initialPose)
//                .lineToY(-39);

    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder releaseSpeciman() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder stepThree_pushSamples() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder stepFour_arcToSpecimanPick() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder splineToPlaceSpeciman() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder splineToSpecimanPick() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder parkInObservation() {
        return null;
//        return drive.actionBuilder(drive.pose)
//                .lineToY(-33.5, new TranslationalVelConstraint(10));
    }

}
