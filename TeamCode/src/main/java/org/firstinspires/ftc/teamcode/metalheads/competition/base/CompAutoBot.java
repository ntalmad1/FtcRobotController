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
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.commands.AbstractDriveTrainGyroTurnCommand;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
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

        DistanceSensor distanceSensor = hardwareMap.get(DistanceSensor.class, "tokenSensor");
        this.tokenSensor = (Rev2mDistanceSensor) distanceSensor;

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
                CompAutoBot.this.driveTrain.forward(0.2, 0.4, 18.5, Units.Centimeters);
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
                        .moveLinearActuatorToPosition(CompAutoBot.this.getRobotConfig().pixelReady_linAct)
                        .wait(250)
                        .moveClawToPosition(CompAutoBot.this.getRobotConfig().pixelReady_clawBoom, 1)
                        .moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom, 1)
                        .moveLinearActuatorToPosition(3400)
                        .moveBottomToPosition(0.236, 0.05)
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
     * @param degrees
     * @param delayMillis
     * @param handler
     */
//    protected void ping (double degrees, int delayMillis, PingHandler handler) {
//        //this.arm.rotateClawToDegrees(degrees);
//        this.arm.wait(delayMillis, new CommandCallbackAdapter() {
//            public void onSuccess (CommandSuccessEvent event) {
//                handler.onPing(new PingEvent(degrees, CompAutoBot.this.sonar.getDistance(DistanceUnit.CM), DistanceUnit.CM));
//            }
//        });
//    }

    /**
     *
     * @param startDegrees
     * @param endDegrees
     * @param delayMillis
     * @param targetDistance
     * @param handler
     */
//    private void scanLeftToRight (double startDegrees, double endDegrees, int delayMillis, double targetDistance, PingHandler handler) {
//        if (startDegrees > endDegrees) {
//            handler.onPing(new PingEvent(endDegrees, -1, null));
//            return;
//        }
//
//        //this.arm.rotateClawToDegrees(startDegrees);
//        this.arm.wait(delayMillis, new CommandCallbackAdapter(){
//            public void onSuccess(CommandSuccessEvent successEvent) {
//
//                CompAutoBot.this.telemetry.addLine("Pinging...");
//
//                double distance = CompAutoBot.this.sonar.getDistance(DistanceUnit.CM);
//
//                CompAutoBot.this.telemetry.addData("Degrees: ", "%2f", startDegrees);
//                CompAutoBot.this.telemetry.addData("Distance: ", "%2f", distance);
//                CompAutoBot.this.telemetry.update();
//
//                if (distance <= targetDistance) {
//                    handler.onPing(new PingEvent(startDegrees, distance, DistanceUnit.CM));
//                }
//                else {
//                    double nextDegrees = startDegrees + (double)1;
//
//                    CompAutoBot.this.scanLeftToRight(nextDegrees, endDegrees, 50, targetDistance, handler);
//                }
//            }
//        });
//
//    }

//    private void scanRightToLeft (double startDegrees, double endDegrees, int delayMillis, double targetDistance, PingHandler handler) {
//        if (startDegrees < endDegrees) {
//            handler.onPing(new PingEvent(endDegrees, -1, null));
//            return;
//        }
//
//        //this.arm.rotateClawToDegrees(startDegrees);
//        this.arm.wait(delayMillis, new CommandCallbackAdapter(){
//            public void onSuccess(CommandSuccessEvent successEvent) {
//
//                CompAutoBot.this.telemetry.addLine("Pinging...");
//
//                double distance = CompAutoBot.this.sonar.getDistance(DistanceUnit.CM);
//
//                CompAutoBot.this.telemetry.addData("Degrees: ", "%2f", startDegrees);
//                CompAutoBot.this.telemetry.addData("Distance: ", "%2f", distance);
//                CompAutoBot.this.telemetry.update();
//
//                if (distance <= targetDistance) {
//                    handler.onPing(new PingEvent(startDegrees, distance, DistanceUnit.CM));
//                }
//                else {
//                    double nextDegrees = startDegrees - 1d;
//
//                    CompAutoBot.this.scanRightToLeft(nextDegrees, endDegrees, 50, targetDistance, handler);
//                }
//            }
//        });
//
//    }

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
    protected void autoRoutine_beginStepTwo_Corner(int distanceBack, int distanceToScanPosition) {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain
                        .sideways(
                                CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                0.3, 0.3, 20, Units.Centimeters)
                        .gyroTurn(CompAutoBot.this.robotAutoConfig.backdropDirection,
                                0.2, 0.2, 90, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE)
                        .endCommand(outerCommand);
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain
                        .setBrakeOff()
                        .forward(0.2, 0.5, distanceToScanPosition, Units.Centimeters)
                        .gotoDegrees(
                                0.1,
                                0.1,
                                90)
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
                    CompAutoBot.this.driveTrain.strafeForAprilTag(
                            CompAutoBot.this.aprilTagProcessor,
                            CompAutoBot.this.robotAutoConfig.backdropDirection.invert(),
                            CompAutoBot.this.convertPropLocationToAprilTagId(CompAutoBot.this.propLocation),
                            0.2,
                            140,
                            Units.Centimeters, new CommandCallbackAdapter() {
                                public void onSuccess(CommandSuccessEvent successEvent) {
                                    // place yellow token on backboard
                                    CompAutoBot.this.autoRoutine_placeYellowPixelBackdrop();
                                }

                                public void onFailure(CommandFailureEvent failureEvent) {
                                    // park
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

        }
    }

    /**
     *
     */
    protected void autoRoutine_beginStepTwo_Middle (int distanceToMiddle, int distanceToScanPosition) {

//        this.addCommand(new OneTimeCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.moveArm_fromPixelReady_toPixelPlace();
//                CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this){
//                    public void onSuccess(CommandSuccessEvent successEvent) {
//                        this.command.markAsCompleted();
//                    }
//                });
//            }
//        });
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.driveTrain.forward(0.3, 0.4, distance, Units.Centimeters);
//                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
//                    public void onSuccess(CommandSuccessEvent successEvent) {
//                        this.command.markAsCompleted();
//                    }
//                });
//            }
//        });
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                CompAutoBot.this.driveTrain
//                        .gotoDegrees(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(), 0.2, 0.2, 90)
//                        .wait(500)
//                        .gotoDegrees(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(), 0.2, 0.2, 90);
//
//                if (forwards > 0) {
//                    CompAutoBot.this.driveTrain.forward(0.2, 0.2, forwards, Units.Centimeters);
//                }
//
//                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                if (CompAutoBot.this.propLocation.equals(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert())) {
//                    CompAutoBot.this.autoRoutine_placeYellowPixel(
//                            CompAutoBot.this.robotAutoConfig.routine.equals(Routine.FAR) ? CompAutoBot.this.robotAutoConfig.placeYellowPixelDistance_near : CompAutoBot.this.robotAutoConfig.placeYellowPixelDistance_far);
//                }
//                else if (CompAutoBot.this.propLocation.equals(Direction.FORWARD)) {
//                    CompAutoBot.this.autoRoutine_placeYellowPixel(CompAutoBot.this.robotAutoConfig.placeYellowPixelDistance_middle);
//                }
//                else {
//                    CompAutoBot.this.autoRoutine_placeYellowPixel(CompAutoBot.this.robotAutoConfig.routine.equals(Routine.FAR) ? CompAutoBot.this.robotAutoConfig.placeYellowPixelDistance_far : CompAutoBot.this.robotAutoConfig.placeYellowPixelDistance_near);
//                }
//                command.markAsCompleted();
//            }
//        });

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
                                 0.4,
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
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {
                    CompAutoBot.this.driveTrain.back(
                            0.2,
                            0.3,
                            CompAutoBot.this.robotAutoConfig.placePurplePixelForwardsDistance, Units.Centimeters);
                    CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(){
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            outerCommand.markAsCompleted();

                            //** **//
                            CompAutoBot.this.autoRoutine_beginStepTwo_Corner(13, CompAutoBot.this.robotAutoConfig.gotoCornerDistance_Far);
                        }
                    });
                }
            });
        }
        // Middle :: sideways then begin step two
        else {

        }




                // 13 cm to back


    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelOppositeTruss () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection.invert();

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom);
                CompAutoBot.this.driveTrain
                        .forward(0.2, 0.4,
                                CompAutoBot.this.robotAutoConfig.placePurplePixelOppositeTrussDistance, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
                CompAutoBot.this.arm.wait(100, new CommandCallbackAdapter(){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        outerCommand.markAsCompleted();
                    }
                });
                CompAutoBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.UP);
                CompAutoBot.this.driveTrain.back(0.2, 0.3,
                        CompAutoBot.this.robotAutoConfig.placePurplePixelOppositeTrussDistance, Units.Centimeters);
                CompAutoBot.this.driveTrain.gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                        0.2, 0.2, 0, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();

                        if (CompAutoBot.this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {
                            //CompAutoBot.this.autoRoutine_beginStepTwo_near(81, 0);
                        }
                        else {
                            //CompAutoBot.this.autoRoutine_beginStepTwo_far();
                        }
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelTrussSide () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection;

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain
                        .gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                                0.2, 0.2, CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideRotationDegrees, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain
                        .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                0.2, 0.2,
                                10, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(CompAutoBot.this.getRobotConfig().pixelReady_bottomBoom);
                CompAutoBot.this.driveTrain
                        .forward(0.2, 0.4, CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideDistance, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
                CompAutoBot.this.arm.wait(100, new CommandCallbackAdapter(){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        outerCommand.markAsCompleted();
                    }
                });
                CompAutoBot.this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.UP);
                CompAutoBot.this.driveTrain.back(
                        0.2,
                        0.3,
                        CompAutoBot.this.robotAutoConfig.placePurplePixelTrussSideDistance, Units.Centimeters);
                CompAutoBot.this.driveTrain.sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                        0.2, 0.2,
                        10, Units.Centimeters);
                CompAutoBot.this.driveTrain.gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.2, 0.2, 0, AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();

                        if (CompAutoBot.this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.NEAR)) {
                            //CompAutoBot.this.autoRoutine_beginStepTwo_near(81, 0);
                        }
                        else {
                            //CompAutoBot.this.autoRoutine_beginStepTwo_far();
                        }
                    }
                });
            }
        });
    }

//endregion
//region PlaceYellowPixel


    /**
     *
     * @param distance
     */
    protected void autoRoutine_placeYellowPixelBackstage (double distance) {


    }

    /**
     *
     */
    protected void autoRoutine_placeYellowPixelBackdrop() {

//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                final Direction direction = CompAutoBot.this.robotAutoConfig.startPosition.equals(RobotAutoConfig.StartPosition.FAR)
//                        ? CompAutoBot.this.robotAutoConfig.startingTrussDirection : CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert();
//
//                if (direction == null) {
//                    CompAutoBot.this.telemetry.log().add("ERROR: CompAutoBot.this.robotAutoConfig.startPosition - NOT SET");
//                    command.markAsCompleted();
//                    return;
//                }
//
//                CompAutoBot.this.driveTrain.sideways(direction, 0.2, 0.2, distance, Units.Centimeters)
//                        .forwardBySensor(0.2, CompAutoBot.this.backdropSensor, CompAutoBot.this.robotAutoConfig.backboardPlaceTarget)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                                CompAutoBot.this.autoRoutine_placeYellowPixelBackdrop();
//                            }
//                        });
//
//            }
//        });

//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                CompAutoBot.this.closeClaw(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert());
//                CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                final Direction direction = CompAutoBot.this.robotAutoConfig.routine.equals(Routine.FAR) ?
//                        CompAutoBot.this.robotAutoConfig.startingTrussDirection: CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert();
//
//                CompAutoBot.this.driveTrain.back(0.2, 0.2, 15, Units.Centimeters)
//                        .wait(250);
//
//                if (CompAutoBot.this.robotAutoConfig.autoCorrectAtEnd) {
//                    CompAutoBot.this.driveTrain.gotoDegrees(direction, 0.2, 0.2, 90);
//                }
//
//                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//
//                CompAutoBot.this.arm//.moveMiddleToPosition(0.733, 1)
//                        .wait(250)
//                        .moveClawToPosition(0.250)
//                        .wait(250)
//                        .moveBottomToPosition(0.078, 0.01)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//            }
//        });
    }

//endregion
//region ScanForToken

    /**
     *
     */
    protected void autoRoutine_scanForTokenForwards () {
        //  scan for token straight ahead
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                command.markAsCompleted();

                double distance = CompAutoBot.this.tokenSensor.getDistance(DistanceUnit.CM);

                if (distance < 35) {
                    CompAutoBot.this.autoRoutine_placePurplePixelForward();
                }
                else {
                    CompAutoBot.this.autoRoutine_scanForTokenOppositeTruss();
                }

            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_scanForTokenOppositeTruss () {

        this.addCommand(new OneTimeSynchronousCommand() {
            @Override
            public void runOnce(ICommand outerCommand) {
                CompAutoBot.this.driveTrain.gyroTurn(
                        CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.2, 0.2,
                        CompAutoBot.this.robotAutoConfig.placePurplePixelOppositeTrussRotationDegrees,
                        AbstractDriveTrainGyroTurnCommand.Orientation.ABSOLUTE);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(){
                    @Override
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        //  scan for token straight ahead
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                command.markAsCompleted();

                double distance = CompAutoBot.this.tokenSensor.getDistance(DistanceUnit.CM);

                if (distance < 35) {
                    CompAutoBot.this.autoRoutine_placePurplePixelOppositeTruss();
                }
                else {
                    CompAutoBot.this.autoRoutine_placePurplePixelTrussSide();
                }

            }
        });
    }

//endregion
//region PrivateUtilities

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
        if (this.robotAutoConfig.backdropDirection.equals(Direction.RIGHT)) {

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
     * @param degrees
     * @return
     */
    private int getDegrees (int degrees) {

        if (this.robotAutoConfig.startingTrussDirection.equals(Direction.LEFT)) {
            return degrees;
        }
        else {
            return -degrees;
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

}
