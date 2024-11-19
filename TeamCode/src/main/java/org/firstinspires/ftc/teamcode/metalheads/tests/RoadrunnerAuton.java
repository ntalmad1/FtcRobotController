package org.firstinspires.ftc.teamcode.metalheads.tests;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.InstantAction;
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

/**
 *
 */
@Autonomous(name = "Autonomous Testing", group = "Auto")
//@Disabled
public class RoadrunnerAuton extends AutoBot {

    private AutoActionFactory autoActionFactory;

    /**
     * Constructor
     *
     */
    public RoadrunnerAuton() {
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

        this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        this.bigArm.mainBoom.setTargetPosition(525);
        this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.bigArm.mainBoom.setPower(1);
        this.setArmPos(ArmPos.INIT);
        this.telemetry.log().add("INIT DONE");

    }

    @Override
    public void go() {
        super.go();

        TrajectoryActionBuilder trajectory = this.getDrive().actionBuilder(this.initialPose)

                //SPECIMEN PLACE HIGH READY (Main Boom)
                .afterTime(0, new SequentialAction(new MainBoomToSpecimenHighReady(this.bigArm.mainBoom)))

                //SPECIMEN PLACE HIGH READY (Servos)
                .afterTime(0, () -> {

                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());



                })
                // SPECIMEN PLACE HIGH READY (Viper Slide)
                .afterTime(0, new ParallelAction(new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide)))


                .waitSeconds(0.4)

                .lineToY(-37,
                        new TranslationalVelConstraint(25),
                        new ProfileAccelConstraint(-20, 40))



                //Open Claw to release Specimen
                .afterTime(0, this.littleArm.clawPincher.gotoPositionAction(Constants.CLAW_PINCHER_OPEN_POS, 1))
                //Viper Slide -> 0
                .afterTime(0.15, this.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.VIPER_SLIDES_MIN_TICS))

                .waitSeconds(0.15)
                .lineToYConstantHeading(-42)// Go back away from bar




                .afterTime(0.55, () -> {
                    this.littleArm.doubleServos.setPosition(this.littleArm.doubleServos.getConfig().homePosition);
                    this.littleArm.middleServo.setPosition(this.littleArm.middleServo.getConfig().homePosition);
                    this.littleArm.clawRotator.setPosition(this.littleArm.clawRotator.getConfig().homePosition);
                })
                .afterTime(0.8, ()-> {
                    this.bigArm.mainBoom.setTargetPosition(0);
                    this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.mainBoom.setPower(1);
                })

                .afterTime(1, ()-> {
                    this.bigArm.viperSlide.viperSlidesGotoPositionAction(Constants.SAMPLE_PICK_READY.vSlidePos);
                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SAMPLE_PICK_READY.clawRotatorPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SAMPLE_PICK_READY.middleServoPos.getPos());
                    this.littleArm.doubleServos.setPosition(Constants.SAMPLE_PICK_READY.doubleServosPos.getPos());
                })


                /*
                 * first sample
                 */
                .turnTo(Math.toRadians(24))

                //ViperSlide Extend
                .afterTime(0, ()-> {
                    this.bigArm.viperSlide.setTargetPosition(Constants.VIPER_SLIDES_MAX_TICS-25);
                    this.bigArm.viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    this.bigArm.viperSlide.setPower(1);
                })


                //.setTangent(24)
                //.splineTo(new Vector2d(15,15), Math.toRadians(24))

//                .splineToConstantHeading(new Vector2d(35, -44), Math.toRadians(90)) //Go towards sample

//                .splineToConstantHeading(new Vector2d(35, -18.6), Math.toRadians(90))
//
//                .splineToConstantHeading(new Vector2d(43, -12), Math.toRadians(0),
//                        new TranslationalVelConstraint(20),
//                        new ProfileAccelConstraint(-15, 15))
//
//                .splineToConstantHeading(new Vector2d(45.3, -14), Math.toRadians(270),
//                        new TranslationalVelConstraint(20),
//                        new ProfileAccelConstraint(-15, 15))
//
//                .lineToYConstantHeading(-52,
//                        null,
//                        new ProfileAccelConstraint(-20, 20))
//
//
//                /*
//                 * second sample
//                 */
//                .lineToYConstantHeading(-18.6)
//
//                .splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))
//                //.splineToConstantHeading(new Vector2d(52, -12), Math.toRadians(0))
//
//                .splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))
//                //.splineToConstantHeading(new Vector2d(56, -14), Math.toRadians(270))
//
//                .lineToYConstantHeading(-52)
//                //.lineToYConstantHeading(-52)
//
//
//                /*
//                 * Third sample
//                 */
//                .lineToYConstantHeading(-18.6)
//
//                .splineToConstantHeading(new Vector2d(58, -12), Math.toRadians(0),
//                        new TranslationalVelConstraint(20),
//                        new ProfileAccelConstraint(-15, 15))
//
//                .splineToConstantHeading(new Vector2d(61.7, -14), Math.toRadians(270),
//                        new TranslationalVelConstraint(20),
//                        new ProfileAccelConstraint(-15, 15))
//
//                .lineToYConstantHeading(-52,
//                        new TranslationalVelConstraint(20),
//                        new ProfileAccelConstraint(-15, 15))
//                .stopAndAdd(() -> {
//                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
//
//                    this.bigArm.mainBoom.setTargetPosition(0);
//                    this.bigArm.mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    this.bigArm.mainBoom.setPower(1);
//
//                    this.bigArm.viperSlide.setTargetPosition(0);
//                    this.bigArm.viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//                    this.bigArm.viperSlide.setPower(1);
//                });

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
