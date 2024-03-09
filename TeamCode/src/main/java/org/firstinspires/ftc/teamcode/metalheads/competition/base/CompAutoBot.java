package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandFailureEvent;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.g1_a_press.Gp1_A_PressEvent;
import org.firstinspires.ftc.library.component.event.g1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.library.component.event.gp2_y_press.Gp2_Y_PressEvent;
import org.firstinspires.ftc.library.component.event.gp2_y_press.Gp2_Y_PressHandler;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.commands.AbstractDriveTrainGyroTurnCommand;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CompAutoBot extends CompBot {

    /**
     */
    protected AprilTagProcessor aprilTagProcessor;

    /**
     */
    protected SimpleDriveTrain driveTrain;

    /**
     */
    protected SimpleDriveCompConfig driveTrainConfig;

    /**
     */
    protected RobotAutoConfig robotAutoConfig;

    /**
     */
    protected Rev2mDistanceSensor tokenSensor;

    /**
     */
    protected  Rev2mDistanceSensor leftTokenSensor;

    /**
     */
    protected  Rev2mDistanceSensor rightTokenSensor;

    /**
     */
    protected Rev2mDistanceSensor backdropSensor;

    /**
     */
    protected VisionPortal visionPortal;

    /**
     */
    private final List<Double> backdropPingResults = new ArrayList<Double>();

    /**
     */
    private OneTimeCommand bumpForwardCommand;

    /**
     */
    private OneTimeCommand deployArmCommand;

    /**
     */
    private Direction propLocation;


    /**
     * Constructor
     *
     */
    public CompAutoBot() {
        super();

        // By declaring the configs here in the constructor any class that
        // extends this class can make updates to the configs before the
        // objects are instantiated

        this.robotAutoConfig = new RobotAutoConfig();

        // secure the yellow and purple pixel to start
        this.armConfig.clawConfig.leftClawInitPosition = 0.35;
        this.armConfig.clawConfig.rightClawInitPosition = 0.35;
        this.armConfig.debug = false;

        this.driveTrainConfig = new SimpleDriveCompConfig(this);
        this.driveTrainConfig.debug = false;

        this.setImuName(driveTrainConfig.imuName);
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
        this.driveTrain.init();

        DistanceSensor distanceSensor00 = hardwareMap.get(DistanceSensor.class, "tokenSensor");
        this.tokenSensor = (Rev2mDistanceSensor) distanceSensor00;

        DistanceSensor distanceSensor01 = hardwareMap.get(DistanceSensor.class, "leftTokenSensor");
        this.leftTokenSensor = (Rev2mDistanceSensor) distanceSensor01;

        DistanceSensor distanceSensor02 = hardwareMap.get(DistanceSensor.class, "rightTokenSensor");
        this.rightTokenSensor = (Rev2mDistanceSensor) distanceSensor02;

        DistanceSensor distanceSensor03 = this.hardwareMap.get(DistanceSensor.class, "backdropSensor");
        this.backdropSensor = (Rev2mDistanceSensor) distanceSensor03;

        if (this.robotAutoConfig.useBackdrop) {
            this.initAprilTagProcessor();
        }

        telemetry.addLine("Robot initialized...");
        telemetry.addLine("READY!");
        telemetry.update();

        this.addGp1_A_PressHandler(new Gp1_A_PressHandler() {
            @Override
            public void onGp1_A_Press(Gp1_A_PressEvent event) {
                CompAutoBot.this.moveArm_toHomePosition();
            }
        });

        this.addGp2_Y_PressHandler(new Gp2_Y_PressHandler(){
            @Override
            public void onGp2_Y_Press(Gp2_Y_PressEvent event) {
                CompAutoBot.this.telemetry.addData("Yaw: ", "%2f", CompAutoBot.this.getYaw());
                CompAutoBot.this.telemetry.update();
            }
        });
    }

    /**
     *
     */
    public void go () {

        // use the brake at the start to help with accuracy when placing purple pixel
        this.driveTrain.setBrakeOn();

        // to start autonomous, do two things go forwards so arm can deploy and deploy  arm
        this.bumpForwardCommand = new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.forward(0.2, 0.4, CompAutoBot.this.robotAutoConfig.bumpForwardsDistance, Units.Centimeters);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onAfter(CommandAfterEvent event){
                        this.command.markAsCompleted();

                        if (CompAutoBot.this.deployArmCommand.isCompleted()) {
                            CompAutoBot.this.autoRoutine_scanForTokenForwards();
                        }
                    }
                });
            }
        };

        // deploy the arm to scan for token position
        this.deployArmCommand = new OneTimeCommand(){
            public void runOnce(ICommand command ) {
                CompAutoBot.this.arm
                        .moveBottomToPosition(0.25, 0.001)
                        .moveClawToPosition(0.5)
                        .moveLinearActuatorToPosition(CompAutoBot.this.getRobotConfig().pixelScan_linAct)
                        .wait(250)
                        .moveClawToPosition(CompAutoBot.this.getRobotConfig().pixelScan_clawBoom, 1)
                        .moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom, 1)
                        .moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom, 0.05)
                        .wait(1000, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent event){
                                this.command.markAsCompleted();

                                if (CompAutoBot.this.bumpForwardCommand.isCompleted()) {
                                    CompAutoBot.this.autoRoutine_scanForTokenForwards();
                                }
                            }
                        });
            }
        };

        // move forward to scan for token
        this.addCommand(this.bumpForwardCommand);

        // put arm into purple placing position
        this.addCommand(this.deployArmCommand);
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.driveTrain.run();
        this.driveTrain.run();

        this.arm.run();

        this.driveTrain.run();
        this.driveTrain.run();

        this.driveTrain.run();
        this.driveTrain.run();

        this.pixelCatcher.run();
    }

    /**
     *
     * @param milliseconds
     * @param callbackHandler
     * @return
     */
    public CompAutoBot wait (int milliseconds, CommandCallbackHandler callbackHandler) {
        return (CompAutoBot) super.wait(milliseconds, callbackHandler);
    }

    /**
     *
     */
    protected void autoRoutine_beginStepTwo(int distanceToScanPosition) {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain
                        .setBrakeOff()
                        .gotoDegrees(
                            0.1,
                            0.1,
                                CompAutoBot.this.getDegrees(90))
                        .forward(0.2, 0.5, distanceToScanPosition, Units.Centimeters)
                        .gotoDegrees(
                                0.1,
                                0.1,
                                CompAutoBot.this.getDegrees(90))
                        .endCommand(0, outerCommand);
            }
        });

        if (this.robotAutoConfig.useBackdrop) {

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.moveArm_fromPixelReady_toPixelPlace();
                    CompAutoBot.this.pixelCatcher
                            .wait(200)
                            .toggleWinch();
                    CompAutoBot.this.arm
                            .endCommand(1000, outerCommand);
                }
            });

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    final Direction strafeDirection = CompAutoBot.this.getStrafeDirection();

                    CompAutoBot.this.driveTrain.strafeForAprilTag(
                            CompAutoBot.this.aprilTagProcessor,
                            strafeDirection,
                            CompAutoBot.this.convertPropLocationToAprilTagId(CompAutoBot.this.propLocation),
                            0.2,
                            140,
                            Units.Centimeters,
                            CompAutoBot.this.getAprilTagOffset(), new CommandCallbackAdapter() {
                                public void onSuccess(CommandSuccessEvent successEvent) {
                                    // place yellow token on backboard
                                    CompAutoBot.this.autoRoutine_placeYellowPixelBackdrop();
                                }

                                public void onFailure(CommandFailureEvent failureEvent) {
                                    // park
                                    CompAutoBot.this.onPlaceYellowPixelFailure(strafeDirection.invert());
                                }

                                public void onAfter(CommandAfterEvent afterEvent) {
                                    outerCommand.markAsCompleted();
                                }
                            });
                }
            });

        }
        // Place yellow pixel backstage
        else {
            this.autoRoutine_placeYellowPixelBackstage();
        }
    }

    /**
     *
     */
    protected void autoRoutine_end () {
        // do nothing
    }


//region PlacePurplePixel
    /**
     *
     */
    protected void autoRoutine_placePurplePixelForward () {

        this.propLocation = Direction.FORWARD;

        // move bottom boom to pixel ready
        // move drive train forwards to place pixel
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom);
                CompAutoBot.this.driveTrain
                        .forward(0.2,
                                 0.2,
                                 CompAutoBot.this.robotAutoConfig.placePurplePixelForwardsDistance, Units.Centimeters)
                        .endCommand(command);
            }
        });

        // drop the pixel
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
                CompAutoBot.this.arm.wait(100);
                CompAutoBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.UP);
                CompAutoBot.this.driveTrain.endCommand(outerCommand);
            }
        });

        // Corner :: reverse steps then begin step twp
        if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {
            final int distanceToCorner = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoCornerDistance_Far_forwards : this.robotAutoConfig.gotoCornerDistance_Near_forwards;

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain
                        .back(
                            0.2,
                            0.3,
                            CompAutoBot.this.robotAutoConfig.placePurplePixelForwardsDistance, Units.Centimeters)
                        .endCommand(outerCommand);
                }
            });

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    CompAutoBot.this.driveTrain
                            .back(0.2, 0.3, CompAutoBot.this.robotAutoConfig.corner_backDistance_afterPlacingPurplePixelForwards, Units.Centimeters)
                            .sideways(
                                    CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                    0.3, 0.3, 20, Units.Centimeters)
                            .gyroTurn(CompAutoBot.this.robotAutoConfig.backdropDirection,
                                    0.3, 0.3, 90, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter() {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToCorner);
                        }
                    });
                }
            });

        }
        // Middle :: sideways then begin step two
        else {
            final int distanceToMiddle = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoMiddleDistance_Far_forwards : this.robotAutoConfig.gotoMiddleDistance_Near_forwards;


            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    CompAutoBot.this.driveTrain
                            .sideways(
                                    CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                    0.3, 0.3, 35, Units.Centimeters)
                            .forward(0.2, 0.4, 65, Units.Centimeters)
                            .gyroTurn(CompAutoBot.this.robotAutoConfig.backdropDirection,
                                    0.3, 0.3, 90, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter() {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToMiddle);
                        }
                    });
                }
            });
        }

    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelOppositeTruss () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection.invert();

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain
                        .back(0.2, 0.3,
                                15, Units.Centimeters)
                        .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                0.2, 0.3, 27, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        // drop the pixel
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
                CompAutoBot.this.arm.wait(100);
                CompAutoBot.this.driveTrain.endCommand(outerCommand);
            }
        });

        // Corner :: reverse steps then begin step twp
        if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {
            final int distanceToCorner = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoCornerDistance_Far_oppositeTruss : this.robotAutoConfig.gotoCornerDistance_Near_oppositeTruss;

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain.back(0.2, 0.3,
                           CompAutoBot.this.robotAutoConfig.corner_backDistance_afterPlacingPurplePixelOppositeTruss, Units.Centimeters);
                    CompAutoBot.this.driveTrain.gyroTurn(CompAutoBot.this.robotAutoConfig.backdropDirection,
                            0.3, 0.3, 90, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(){
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToCorner);
                        }
                    });
                }
            });
        }
        // Middle :: sideways then begin step two
        else {
            final int distanceToMiddle = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoMiddleDistance_Far_oppositeTruss : this.robotAutoConfig.gotoMiddleDistance_Near_oppositeTruss;

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain
                            .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                                    0.2, 0.3, 27, Units.Centimeters)
                            .forward(0.2, 0.3,
                                    77, Units.Centimeters)
                            .gotoDegrees(0.3, 0.3,
                                    CompAutoBot.this.getDegrees(90));
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(){
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToMiddle);
                        }
                    });
                }
            });
        }
    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelTrussSide () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection;

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain
//                        .back(0.2, 0.3,
//                                5, Units.Centimeters)
                        .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(), 0.2, 0.3,
                                20, Units.Centimeters)
                        .gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                                0.3, 0.3, 90, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE)
                        .forward(0.2, 0.3,
                                CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideDistance, Units.Centimeters)
                        .endCommand(outerCommand);
            }
        });

        // drop the pixel
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
                CompAutoBot.this.arm.wait(100);
                CompAutoBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.UP);
                CompAutoBot.this.driveTrain.endCommand(outerCommand);
            }
        });

        // Corner :: reverse steps then begin step twp
        if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {
            final int distanceToCorner = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoCornerDistance_Far_trussSide : this.robotAutoConfig.gotoCornerDistance_Near_trussSide;

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain.back(
                            0.2,
                            0.3,
                            CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideDistance, Units.Centimeters);
                    CompAutoBot.this.driveTrain.sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                            0.2, 0.4,
                            65, Units.Centimeters)
                            .endCommand(outerCommand);

                }
            });

            if (CompAutoBot.this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {

                this.addCommand(new OneTimeSynchronousCommand() {
                    public void runOnce(ICommand outerCommand) {
                        CompAutoBot.this.driveTrain
                                .gotoDegrees(0.3, 0.3,
                                        CompAutoBot.this.getDegrees(90))
                                .endCommand(outerCommand);
                    }
                });

            }

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter() {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToCorner);
                        }
                    });
                }
            });
        }
        else {

            final int distanceToMiddle = this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR) ?
                    this.robotAutoConfig.gotoMiddleDistance_Far_trussSide : this.robotAutoConfig.gotoMiddleDistance_Near_trussSide;

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain
                            .back(
                            0.2,0.3,
                                CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideDistance, Units.Centimeters)
                            .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                            0.2, 0.4,
                            70, Units.Centimeters)
                            .endCommand(outerCommand);
                }
            });

            if (CompAutoBot.this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {

                this.addCommand(new OneTimeSynchronousCommand() {
                    public void runOnce(ICommand outerCommand) {
                        CompAutoBot.this.driveTrain
                                .gotoDegrees(0.3, 0.3,
                                        CompAutoBot.this.getDegrees(90))
                                .endCommand(outerCommand);
                    }
                });

            }

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter() {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo(distanceToMiddle);
                        }
                    });
                }
            });

        }
    }


//endregion
//region PlaceYellowPixel
    /**
     *
     */
    protected void autoRoutine_placeYellowPixelBackstage () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain
                        .forward(0.2,
                                0.3,
                                CompAutoBot.this.robotAutoConfig.placeYellowPixelBackstageDistance, Units.Centimeters)
                        .endCommand(command);
            }
        });

        // drop the pixel
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection);
                CompAutoBot.this.arm.wait(100);
                CompAutoBot.this.driveTrain.endCommand(outerCommand);
            }
        });

        // goto to 'home' position with the arm
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.moveArm_toHomePosition();
                CompAutoBot.this.arm.endCommand(outerCommand);
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        outerCommand.markAsCompleted();

                        //** **//
                        CompAutoBot.this.autoRoutine_end();
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_placeYellowPixelBackdrop() {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.moveArm_fromPixelPlace_toPixelPlaceRow2();
                CompAutoBot.this.pixelCatcher.toggleWinch();
                CompAutoBot.this.driveTrain
                        .wait(250)
                        .forwardBySensor(0.2, CompAutoBot.this.backdropSensor, CompAutoBot.this.robotAutoConfig.backboardPlaceTarget)
                        .endCommand(command);
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection);
                CompAutoBot.this.arm.wait(250);
                CompAutoBot.this.arm.endCommand(command);
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {

                Direction parkDirection = CompAutoBot.this.getStrafeDirection().invert();

                int parkPositionDistance = CompAutoBot.this.calculateParkPositionDistance();

                CompAutoBot.this.driveTrain
                        .back(0.2, 0.2, 15, Units.Centimeters)
                        .addCommand(new OneTimeCommand() {
                            @Override
                            public void runOnce(ICommand armHomeCommand) {
                                CompAutoBot.this.moveArm_toHomePosition();
                                armHomeCommand.markAsCompleted();
                            }
                        });
                CompAutoBot.this.driveTrain
                        .sideways(parkDirection, 0.2, 0.4, parkPositionDistance, Units.Centimeters)
                        .gotoDegrees(
                                0.1,
                                0.1,
                                CompAutoBot.this.getDegrees(90))
                        .forward(0.2, 0.2, CompAutoBot.this.robotAutoConfig.parkPositionForwardsDistance_afterPlacingYellowPixelOnBackdrop, Units.Centimeters)
                        .endCommand(outerCommand);
            }
        });

        // goto to 'home' position with the arm
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.moveArm_toHomePosition();
                CompAutoBot.this.arm.endCommand(outerCommand);
            }
        });
    }


//endregion
//region ScanForToken
    /**
     *
     */
    protected void autoRoutine_scanForTokenForwards () {
        //  scan for token
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                command.markAsCompleted();

                double frontDistance = CompAutoBot.this.tokenSensor.getDistance(DistanceUnit.CM);

                CompAutoBot.this.arm.wait(250)
                        .moveLinearActuatorToPosition(CompAutoBot.this.robotConfig.pixelReady_linAct)
                        .moveClawToPosition(CompAutoBot.this.robotConfig.pixelReady_clawBoom, 1);

                if (frontDistance < CompAutoBot.this.robotAutoConfig.frontTokenScanDistance) {

                    CompAutoBot.this.autoRoutine_placePurplePixelForward();

                }
                else {
                    CompAutoBot.this.autoRoutine_scanForTokenSides();
                }
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_scanForTokenSides () {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.arm.moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom);
                CompAutoBot.this.driveTrain
                        .forward(0.2, 0.4, 50, Units.Centimeters)
                        .endCommand(outerCommand);
            }
        });

        //  scan for token
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                command.markAsCompleted();

                double leftDistance = CompAutoBot.this.leftTokenSensor.getDistance(DistanceUnit.CM);
                double rightDistance = CompAutoBot.this.rightTokenSensor.getDistance(DistanceUnit.CM);

                if (leftDistance < CompAutoBot.this.robotAutoConfig.leftTokenScanDistance) {

                    if (CompAutoBot.this.robotAutoConfig.startingTrussDirection.equals(Direction.RIGHT)) {
                        CompAutoBot.this.autoRoutine_placePurplePixelOppositeTruss();
                    }
                    else {
                        CompAutoBot.this.autoRoutine_placePurplePixelTrussSide();
                    }

                }
                else if (rightDistance < CompAutoBot.this.robotAutoConfig.rightTokenScanDistance) {
                    if (CompAutoBot.this.robotAutoConfig.startingTrussDirection.equals(Direction.RIGHT)) {
                        CompAutoBot.this.autoRoutine_placePurplePixelTrussSide();
                    }
                    else {
                        CompAutoBot.this.autoRoutine_placePurplePixelOppositeTruss();
                    }
                }
            }
        });
    }


//endregion
//region PrivateUtilities
    /**
     *
     */
    private int calculateParkPositionDistance () {

        // blue alliance corner
        if (this.robotAutoConfig.backdropDirection.equals(Direction.LEFT) && this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {

            switch (propLocation) {
                case LEFT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId1_toCorner;
                case FORWARD:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId2_toCorner;
                case RIGHT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId3_toCorner;
            }
        }

        // blue alliance middle
        else if (this.robotAutoConfig.backdropDirection.equals(Direction.LEFT) && this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)) {

            switch (propLocation) {
                case LEFT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId1_toMiddle;
                case FORWARD:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId2_toMiddle;
                case RIGHT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Blue.fromId3_toMiddle;
            }
        }

        // red alliance corner
        else if (this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT) && this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {

            switch (propLocation) {
                case LEFT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId4_toCorner;
                case FORWARD:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId5_toCorner;
                case RIGHT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId6_toCorner;
            }
        }

        // red alliance middle
        else if (this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT) && this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)) {

            switch (propLocation) {
                case LEFT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId4_toMiddle;
                case FORWARD:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId5_toMiddle;
                case RIGHT:
                    return RobotAutoConfig.ParkDistFromAprilTags_Red.fromId6_toMiddle;
            }
        }

        // something bad happened
        this.telemetry.log().add("Distance not calculated!");
        return 0;

    }

    /**
     *
     * @param side
     */
    private void closeClaw (Direction side) {

        if (side.equals(Direction.LEFT)) {
            this.arm.closeLeftClaw();
        }
        else {
            this.arm.closeRightClaw();
        }
    }

    /**
     *
     */
    private int convertPropLocationToAprilTagId (Direction propLocation) {

        // blue alliance
        if (this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)) {

            switch (propLocation) {
                case LEFT:
                    return 1;
                case FORWARD:
                    return 2;
                case RIGHT:
                    return 3;
            }
        }

        // red alliance
        else if (this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)) {

            switch (propLocation) {
                case LEFT:
                    return 4;
                case FORWARD:
                    return 5;
                case RIGHT:
                    return 6;
            }
        }

        // something bad happened
        this.telemetry.log().add("Direction of token not converted to April Tag ID!!");
        return 0;
    }

    /**
     *
     * @return
     */
    private double getAprilTagOffset () {

        if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)
            && this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)
            && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR)) {
            return -1.9;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)
                && this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {
            return 1;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)
                && this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR)) {
            return 1;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)
                && this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {

            if (this.propLocation.equals(Direction.LEFT)) {
                return -1.7;
            }
            return -1.9;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)
                && this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR)) {
            return -1.9;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)
                && this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {
            return 1;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)
                && this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {
            return -1.9;
        }
        else if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.MIDDLE)
                && this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)
                && this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR)) {
            return 1;
        }

        return 0;
    }

    /**
     *
     * @param degrees
     * @return
     */
    private int getDegrees (int degrees) {

        if (this.robotAutoConfig.backdropDirection.equals(Direction.LEFT)) {
            return degrees;
        }
        else {
            return -degrees;
        }
    }

    /**
     *
     * @return
     */
    private Direction getStrafeDirection () {

        if (this.robotAutoConfig.parkPosition.equals(RobotAutoConfig.ParkPosition.CORNER)) {

            return this.robotAutoConfig.backdropDirection.invert();

        }
        else {
            return this.robotAutoConfig.backdropDirection;
        }
    }

    /**
     *
     */
    private void initAprilTagProcessor () {
        // Create the AprilTag processor the easy way.
        this.aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();

        // Create the vision portal the easy way.
        this.visionPortal = VisionPortal.easyCreateWithDefaults(
                    hardwareMap.get(WebcamName.class, this.robotAutoConfig.webCamName), aprilTagProcessor);

    }

//endregion

    /**
     *
     * @param handler
     */
    protected void pingBackdrop (PingHandler handler) {
        PingEvent event = new PingEvent(0, this.backdropSensor.getDistance(DistanceUnit.CM), DistanceUnit.CM);
        handler.onPing(event);
    }

    /**
     *
     * @param direction
     */
    private void onPlaceYellowPixelFailure (Direction direction) {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain
                        .sideways(direction, 0.2, 0.4, 140, Units.Centimeters)
                        .gotoDegrees(
                                0.1,
                                0.1,
                                CompAutoBot.this.getDegrees(90))
                        .wait(0, new CommandCallbackAdapter(){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                super.onSuccess(successEvent);
                                outerCommand.markAsCompleted();
                                CompAutoBot.this.autoRoutine_placeYellowPixelBackstage();
                            }
                        });
            }
        });
    }
}
