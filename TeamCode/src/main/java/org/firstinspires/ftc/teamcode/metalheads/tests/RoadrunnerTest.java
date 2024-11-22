package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.metalheads.RightObsBotConfig;
import org.firstinspires.ftc.teamcode.metalheads.RightObsTrajectoryFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoActionFactory;
import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToZero;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToZero;

/**
 *
 */
@Autonomous(name = "Roadrunner Tester", group = "Auto")
//@Disabled
public class RoadrunnerTest extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RoadrunnerTest() {
        super();

        this.setTrajectoryFactory(new RightObsTrajectoryFactory(this));

        this.setConfig(new RightObsBotConfig(this));
        this.configureBot();
    }

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();

        this.autoActionFactory = new AutoActionFactory(this);

        // initialize roadrunner from last op pose
        this.setInitialPose(new Pose2d(8, -61, Math.toRadians(90)));
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();
    }

    @Override
    public void go() {
        super.go();

        TrajectoryActionBuilder trajectory = this.getDrive().actionBuilder(this.initialPose)

                //Go to Bar
                .lineToY(-36,
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-60, 30))


                // Go back away from bar
                .lineToYConstantHeading(-50)




//              Samples
//              ---------------------------------------------------------------------------------------------------



                /*
                 * First Sample
                 */
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(34, -44), Math.toRadians(90)) //Go towards sample

                .splineToConstantHeading(new Vector2d(34, -18.6), Math.toRadians(90))


                .splineToConstantHeading(new Vector2d(43, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(30))

                .splineToConstantHeading(new Vector2d(46, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(25),
                        new ProfileAccelConstraint(-25, 60))

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(46,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-15, 60)
                )


                /*
                 * second sample
                 */
                .lineToYConstantHeading(-22)

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(30)
                )

                .splineToConstantHeading(new Vector2d(52, -9), Math.toRadians(0),
                        new TranslationalVelConstraint(30)
                )

                .splineToConstantHeading(new Vector2d(58, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(30)
                )

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(58,-52), Math.toRadians(90),
                        new TranslationalVelConstraint(25)
                )


                /*
                 * Third sample
                 */
                .lineToYConstantHeading(-22)

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(30)
                )

                .splineToConstantHeading(new Vector2d(58, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(30)
                )

                .splineToConstantHeading(new Vector2d(62, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(25)
                )

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(62,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-15, 60)
                )





                ;
        Actions.runBlocking(trajectory.build());
    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }

}
