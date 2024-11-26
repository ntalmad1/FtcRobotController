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
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToSpecimenPickReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.MainBoomToZero;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToSpecimenHighReady;
import org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ViperSlideToZero;

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

        TrajectoryActionBuilder cycleSpecimen = this.getDrive().actionBuilder(this.getDrive().pose)


                .afterTime(0.0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                })

                .waitSeconds(0.2)

                .afterTime(0, new MainBoomToSpecimenHighReady(this.bigArm.mainBoom))

                .waitSeconds(0.1)

                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                })

                .afterTime(0.1, new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide))

                //Hang Specimen
                .setTangent(140)
                .splineToConstantHeading(new Vector2d(9,-56), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))

                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))




                //Retreat back to next Specimen
                .afterTime(1, ()-> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                })

                .afterTime(1, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))



                .setTangent(270)
                .splineToConstantHeading(new Vector2d(20, -45), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-12, 50)
                );




        TrajectoryActionBuilder mainTrajectory = this.getDrive().actionBuilder(this.initialPose)

//              Arm -> Specimen High Ready
//              ----------------------------------------------------------------------------------------------

                //SPECIMEN PLACE HIGH READY (Main Boom)
                .afterTime(0, new MainBoomToSpecimenHighReady(this.bigArm.mainBoom))

                //SPECIMEN PLACE HIGH READY (Servos)
                .afterTime(0, () -> {

                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());

                })
                // SPECIMEN PLACE HIGH READY (Viper Slide)
                .afterTime(0.13, new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide))


//              Hang Specimen
//              ----------------------------------------------------------------------------------------------


                //Go To bar
                .lineToY(-36,
                        new TranslationalVelConstraint(50),
                        new ProfileAccelConstraint(-60, 30))


                //Open Claw to release Specimen
                .afterTime(0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS);
                })
                //Viper Slide -> 0
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))


                // Go back away from bar
                .lineToYConstantHeading(-42)

                .afterTime(0.2, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                    this.littleArm.clawPincher.setPosition(Constants.SAMPLE_PICK_READY.clawPincherPos.getPos());
                })

                .afterTime(0.2, new MainBoomToZero(this.bigArm.mainBoom))





//              Samples
//              ---------------------------------------------------------------------------------------------------



                /*
                 * First Sample
                 */

                .splineToConstantHeading(new Vector2d(12,-50), Math.toRadians(0))

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

                .splineToConstantHeading(new Vector2d(50, -9), Math.toRadians(0),
                        new TranslationalVelConstraint(25)
                )

                .splineToConstantHeading(new Vector2d(58, -14), Math.toRadians(270),
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
                .splineToConstantHeading(new Vector2d(62,-52), Math.toRadians(90),
                        null,
                        new ProfileAccelConstraint(-15, 60)
                )




//                Specimens
//                ---------------------------------------------------------------------------------------




                /*
                 * First Specimen
                 */
                .afterTime(0, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))

                //Arc To Specimen
                .splineToConstantHeading(new Vector2d(52, -50), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(42, -56.8), Math.toRadians(270),
                        new TranslationalVelConstraint(45),
                        new ProfileAccelConstraint(-15, 60)
                )





//              Cycle Specimen (2nd total)
//              ----------------------------------------------------------------------------







        .afterTime(0.0, () -> {
            this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
        })

                .waitSeconds(0.2)

                .afterTime(0, new MainBoomToSpecimenHighReady(this.bigArm.mainBoom))

                .waitSeconds(0.1)

                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                })

                .afterTime(0.1, new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide))

                //Hang Specimen
                .setTangent(140)
                .splineToConstantHeading(new Vector2d(8,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(6,-36), Math.toRadians(90))

                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))




                //Retreat back to next Specimen
                .afterTime(1, ()-> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                })

                .afterTime(1, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))



                .setTangent(270)
                .splineToConstantHeading(new Vector2d(20, -45), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-12, 50)
                )





//              Cycle Specimen (3rd total)
//              -----------------------------------------------------------------------------------------




                .afterTime(0.0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                })

                .waitSeconds(0.2)

                .afterTime(0, new MainBoomToSpecimenHighReady(this.bigArm.mainBoom))

                .waitSeconds(0.1)

                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                })

                .afterTime(0.1, new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide))

                //Hang Specimen
                .setTangent(140)
                .splineToConstantHeading(new Vector2d(8,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(4,-36), Math.toRadians(90))

                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))




                //Retreat back to next Specimen
                .afterTime(1, ()-> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                })

                .afterTime(1, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))



                .setTangent(270)
                .splineToConstantHeading(new Vector2d(20, -45), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-12, 50)
                )






//              Cycle Specimen (4th total)
//              -----------------------------------------------------------------------------------------







                .afterTime(0.0, () -> {
                    this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_CLOSE_POS);
                })

                .waitSeconds(0.2)

                .afterTime(0, new MainBoomToSpecimenHighReady(this.bigArm.mainBoom))

                .waitSeconds(0.1)

                .afterTime(0, () -> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PLACE_HIGH_READY.clawRotatorPos.getPos());
                })

                .afterTime(0.1, new ViperSlideToSpecimenHighReady(this.bigArm.viperSlide))

                //Hang Specimen
                .setTangent(140)
                .splineToConstantHeading(new Vector2d(8,-60), Math.toRadians(180))
                .splineToConstantHeading(new Vector2d(1,-36), Math.toRadians(90))

                .afterTime(0, () -> this.littleArm.clawPincher.setPosition(Constants.CLAW_PINCHER_OPEN_POS))
                .afterTime(0.1, new ViperSlideToZero(this.bigArm.viperSlide))




                //Retreat back to next Specimen
                .afterTime(1, ()-> {
                    this.littleArm.doubleServos.setPosition(Constants.SPECIMEN_PICK_READY.doubleServosPos.getPos());
                    this.littleArm.middleServo.setPosition(Constants.SPECIMEN_PICK_READY.middleServoPos.getPos());
                    this.littleArm.clawRotator.setPosition(Constants.SPECIMEN_PICK_READY.clawRotatorPos.getPos());
                })

                .afterTime(1, new MainBoomToSpecimenPickReady(this.bigArm.mainBoom))



                .setTangent(270)
                .splineToConstantHeading(new Vector2d(20, -45), Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(42, -56.5), Math.toRadians(270),
                        null,
                        new ProfileAccelConstraint(-12, 50)
                )







                ;
        Actions.runBlocking(mainTrajectory.build());

    }

    /**
     *
     * @return
     */
    protected RightObsTrajectoryFactory getTrajectoryFactory () {
        return (RightObsTrajectoryFactory)super.getTrajectoryFactory();
    }



}
