package org.firstinspires.ftc.teamcode.metalheads;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;

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
        return this.getAutoBot().getDrive().actionBuilder(initialPose)
           .lineToY(-39);
    }

    /**
     *
     * @return
     */
    public TrajectoryActionBuilder stepTwo_Three_releaseSpeciman_pushSamples() {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                /*
                 * First Sample
                 */
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -44), Math.toRadians(0))//away + right of bar
                .splineToConstantHeading(new Vector2d(38, -18.6), Math.toRadians(0))// up torwards sample
                .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0))//arc onto sample (left)
                .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270))//arc onto sample (right)
                .lineToYConstantHeading(-52) //Go to Observation zone

                /*
                 * Second Sample
                 */
                .setTangent(Math.toRadians(270))
                .lineToYConstantHeading(-18.6) //towards sample
                .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0)) //arc onto sample (left)
                .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270)) //arc onto sample (right)
                .lineToYConstantHeading(-52); //Go to Observation zone
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
