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
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenPickReady;
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
                .lineToYConstantHeading(-42)
                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                })
                .afterTime(0, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))
                .splineToConstantHeading(new Vector2d(12,-50), Math.toRadians(0))




//              Samples
//              ---------------------------------------------------------------------------------------------------



                /*
                 * First Sample
                 */
                .splineToConstantHeading(new Vector2d(36, -46), Math.toRadians(90)) //Go towards sample

                .splineToConstantHeading(new Vector2d(36, -18.6), Math.toRadians(90))


                .splineToConstantHeading(new Vector2d(44, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(30))

                .splineToConstantHeading(new Vector2d(49, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(25),
                        new ProfileAccelConstraint(-25, 60))

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(49,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-60, 25)
                )


                /*
                 * second sample
                 */
                .lineToYConstantHeading(-22)

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(25)
                )

                .splineToConstantHeading(new Vector2d(53, -9), Math.toRadians(0),
                        new TranslationalVelConstraint(25)
                )

                .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(30)
                )

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(56,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-60,25)
                )


                /*
                 * Third sample
                 */
                .lineToYConstantHeading(-22)

                .lineToYConstantHeading(-18.6,
                        new TranslationalVelConstraint(28)
                )

                .splineToConstantHeading(new Vector2d(59, -10), Math.toRadians(0),
                        new TranslationalVelConstraint(16)
                )

                .splineToConstantHeading(new Vector2d(63, -14), Math.toRadians(270),
                        new TranslationalVelConstraint(18)
                )

                .lineToYConstantHeading(-48)
                .splineToConstantHeading(new Vector2d(63,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-15, 60)
                )












                /*
                 * Specimens
                 */
                .splineToConstantHeading(new Vector2d(52, -48), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(42, -56.8), Math.toRadians(270))


                .waitSeconds(0.3)

                .setTangent(Math.toRadians(140))
                .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))


                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-60, 45)
                )

                .waitSeconds(0.3)

                .setTangent(Math.toRadians(140))
                .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))


                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-60, 45)
                )

                .waitSeconds(0.3)

                .setTangent(Math.toRadians(140))
                .splineToConstantHeading(new Vector2d(15,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))


                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(24, -51), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-60, 45)
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
