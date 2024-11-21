package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
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

import org.firstinspires.ftc.teamcode.metalheads.compbot.AutoBot;

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

                .afterTime(0, new ParallelAction(
                                new SequentialAction(
                                        new MainBoomToSpecimenHighReady(this.bigArm.mainBoom)
                                ),
                            new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide)
                        )
                )

                .lineToY(-30)

                .waitSeconds(3)

                .afterTime(0, new SequentialAction(new ViperSlideToZero(this.bigArm.viperSlide)))
                .afterTime(0, new MainBoomToZero(this.bigArm.mainBoom))



                ;
        Actions.runBlocking(trajectory.build());
    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return this.getAutoBot().getDrive().actionBuilder(this.getAutoBot().getDrive().pose)
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(51,-44), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(46, -57.4), Math.toRadians(270),
                        new TranslationalVelConstraint(8)
                );
    }

}
