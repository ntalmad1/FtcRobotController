package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.drivetrain.commands.IScanCommand;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Units;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class CompAutoBot extends CompBot {

    /**
     */
    protected SimpleDriveCompConfig driveTrainConfig;

    /**
     */
    protected SimpleDriveTrain driveTrain;

    /**
     */
    private Rev2mDistanceSensor sonar;

    /**
     */
    protected RobotAutoConfig robotAutoConfig;

    /**
     */
    private List<Double> backdropPingResults = new ArrayList<Double>();

    /**
     */
    protected Direction propLocation;

    /**
     * Constructor
     *
     */
    public CompAutoBot() {
        super();

        RobotAutoConfig robotAutoConfig = new RobotAutoConfig();
        this.robotAutoConfig = robotAutoConfig;

        this.armConfig.clawConfig.leftClawInitPosition = 0.35;
        this.armConfig.clawConfig.rightClawInitPosition = 0.35;
        this.armConfig.debug = true;

        this.driveTrainConfig = new SimpleDriveCompConfig(this);
        this.driveTrainConfig.debug = false;
        this.setImuName(driveTrainConfig.imuName);
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
        this.driveTrain.init();

        DistanceSensor sonarDistanceSensor = hardwareMap.get(DistanceSensor.class, "sonarSensor");
        this.sonar = (Rev2mDistanceSensor) sonarDistanceSensor;

        telemetry.addLine("Robot initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    private OneTimeCommand bumpForwardCommand;
    private OneTimeCommand deployArmCommand;

    double power = 0.1;

    /**
     *
     */
    public void go () {

        this.bumpForwardCommand = new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.forward(0.2, 0.1, 18.5, Units.Centimeters);
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

        this.deployArmCommand = new OneTimeCommand(){
            public void runOnce(ICommand command ) {
                CompAutoBot.this.arm.moveMiddleDegreesFromCurrentPosition(15)
                        .wait(0)
                        .moveMiddleToDegrees(-90, 1)
                        .moveBottomDegreesFromCurrentPosition(-30)
                        .wait(1000, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent event){
                                CompAutoBot.this.pixelCatcher.toggleLeftArm();
                                CompAutoBot.this.pixelCatcher.toggleRightArm();
                            }                        })
                        .moveBottomToPosition(0.135, 1)
                        .moveMiddleToPosition(0.718,1)
                        .moveClawToPosition(0.715, 1)
                        .rotateClawToPosition(0.307, 1)
                        .wait(2500)
                        .moveBottomToPosition(0.121, 1)
                        .wait(100, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent event){
                                this.command.markAsCompleted();

                                if (CompAutoBot.this.bumpForwardCommand.isCompleted()) {
                                    CompAutoBot.this.autoRoutine_scanForTokenForwards();
                                }
                            }
                        });
            }
        };

        // move forward 18.5 cm to scan for token
        // this.addCommand(this.bumpForwardCommand);

        // put arm into purple placing position
        // this.addCommand(this.deployArmCommand);

        // this.autoRoutine_beginStepTwo(0);

        this.addGp1_X_PressHandler(event -> {
            double newPower = CompAutoBot.this.power + 0.1;

            if (newPower > 1) {
                newPower = 0.1;
            }

            CompAutoBot.this.power = newPower;

            CompAutoBot.this.telemetry.addData("New Power: ", "%2f", CompAutoBot.this.power);
            CompAutoBot.this.telemetry.update();
        });

        this.addGp1_Y_PressHandler(event -> {
            CompAutoBot.this.telemetry.addData("Power: ", "%2f", CompAutoBot.this.power);
            CompAutoBot.this.telemetry.update();

            CompAutoBot.this.driveTrain.forward(CompAutoBot.this.power, CompAutoBot.this.power, 20, Units.Centimeters);
        });
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

        this.lightBar.run();

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
    protected void ping (double degrees, int delayMillis, PingHandler handler) {
        this.arm.rotateClawToDegrees(degrees);
        this.arm.wait(delayMillis, new CommandCallbackAdapter() {
            public void onSuccess (CommandSuccessEvent event) {
                handler.onPing(new PingEvent(degrees, CompAutoBot.this.sonar.getDistance(DistanceUnit.CM), DistanceUnit.CM));
            }
        });
    }

    /**
     *
     * @param startDegrees
     * @param endDegrees
     * @param delayMillis
     * @param targetDistance
     * @param handler
     */
    private void scan (double startDegrees, double endDegrees, int delayMillis, double targetDistance, PingHandler handler) {
        if (startDegrees > endDegrees) {
            handler.onPing(new PingEvent(endDegrees, -1, null));
            return;
        }

        this.arm.rotateClawToDegrees(startDegrees);
        this.arm.wait(delayMillis, new CommandCallbackAdapter(){
            public void onSuccess(CommandSuccessEvent successEvent) {

                CompAutoBot.this.telemetry.addLine("Pinging...");




                double distance = CompAutoBot.this.sonar.getDistance(DistanceUnit.CM);

                CompAutoBot.this.telemetry.addData("Degrees: ", "%2f", startDegrees);
                CompAutoBot.this.telemetry.addData("Distance: ", "%2f", distance);
                CompAutoBot.this.telemetry.update();

                if (distance <= targetDistance) {
                    handler.onPing(new PingEvent(startDegrees, distance, DistanceUnit.CM));
                }
                else {
                    double nextDegrees = startDegrees + (double)1;

                    CompAutoBot.this.scan(nextDegrees, endDegrees, 50, targetDistance, handler);
                }
            }
        });

    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelForward () {

        this.propLocation = Direction.FORWARD;

        this.addCommand(new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.rotateClawToDegrees(93)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.sideways(
                        CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.1, 16, Units.Centimeters)
                        .wait(0)
                        .forward(0.2, 0.1, 22, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(0.145,1)
                        .closeLeftClaw()
                        .wait(100, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.3, 38, Units.Centimeters);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        CompAutoBot.this.autoRoutine_beginStepTwo(91);
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_scanForTokenForwards () {

        // scan for token straight ahead
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.scan(-5, 5, 1000, 30, new PingHandler() {
                    public void onPing(PingEvent event) {
                        command.markAsCompleted();

                        CompAutoBot.this.telemetry.addData("Distance:", "%2f", event.getDistance());
                        CompAutoBot.this.telemetry.update();

                        if (event.getDistance() > -1 && event.getDistance() < 30) {
                            CompAutoBot.this.autoRoutine_placePurplePixelForward();
                        }
                        else {
                            CompAutoBot.this.autoRoutine_scanForTokenOppositeTruss();
                        }
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_scanForTokenOppositeTruss () {

        // scan for token straight ahead
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.scan(67, 77, 500, 30, new PingHandler() {
                    public void onPing(PingEvent event) {
                        command.markAsCompleted();

                        CompAutoBot.this.telemetry.addData("Distance:", "%2f", event.getDistance());
                        CompAutoBot.this.telemetry.update();

                        if (event.getDistance() > -1 && event.getDistance() < 30) {
                            CompAutoBot.this.autoRoutine_placePurplePixelOppositeTruss();
                        }
                        else {
                            CompAutoBot.this.autoRoutine_placePurplePixelTruss();
                        }
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelOppositeTruss () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection.invert();


        this.addCommand(new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.rotateClawToDegrees(0)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.sideways(
                                CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                                0.1, 0.3, 37, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(0.145,1)
                        .closeLeftClaw()
                        .wait(100, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });
            }
        });


        // move arm to travel mode
        this.addCommand(new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.wait(1000);
                //CompAutoBot.this.moveArm_toPixelTravel();
                CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                    }
                });
            }
        });


        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.sideways(
                        CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.3, 15, Units.Centimeters);
                CompAutoBot.this.driveTrain.diagFront(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.3, 11, Units.Centimeters);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        CompAutoBot.this.autoRoutine_beginStepTwo(114);
                    }
                });
            }
        });




    }

    /**
     *
     */
    protected void autoRoutine_placePurplePixelTruss () {

        this.propLocation = this.robotAutoConfig.startingTrussDirection;

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.frontAxelPivot(
                                CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                                0.1, 0.2, 44)
                        .forward(0.1, 0.2, 6, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.moveBottomToPosition(0.145,1)
                        .closeLeftClaw()
                        .wait(100, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });
            }
        });


        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain
                        .back(0.1, 0.2, 6, Units.Centimeters)
                        .frontAxelPivot(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.2, 44)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent afterEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        // move arm to travel mode
        this.addCommand(new OneTimeCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.arm.wait(1000);
               // CompAutoBot.this.moveArm_toPixelTravel();
                CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.diagFront(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(),
                        0.1, 0.3, 65, Units.Centimeters);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        CompAutoBot.this.autoRoutine_beginStepTwo(60);
                    }
                });
            }
        });


    }



    /**
     *
     * @param distance
     */
    protected void autoRoutine_beginStepTwo (int distance) {

//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.driveTrain.forward(0.1, 0.4, distance, Units.Centimeters)
//                        .sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection, 0.1, 0.3, 20, Units.Centimeters)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//            }
//        });
//
//
//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.driveTrain
//                .gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection, 0.1, 0.3, 45)
//                .wait(0, new CommandCallbackAdapter(this){
//                    public void onSuccess(CommandSuccessEvent successEvent) {
//                        this.command.markAsCompleted();
//                    }
//                });
//
//            }
//        });
//
//        this.addCommand(new OneTimeCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.driveTrain
//                        .gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection, 0.1, 0.3, 45)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });
//
//        this.addCommand(new OneTimeCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.moveArm_fromPixelReady_toTravel();
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
//                CompAutoBot.this.driveTrain.forward(0.1, 0.4, 130, Units.Centimeters)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });
//
//        this.addCommand(new OneTimeCommand() {
//            public void runOnce(ICommand command) {
//                CompAutoBot.this.moveArm_fromTravel_toPixelPlace();
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
//                CompAutoBot.this.driveTrain.forward(0.1, 0.4, 84, Units.Centimeters)
//                        .wait(0, new CommandCallbackAdapter(this){
//                            public void onSuccess(CommandSuccessEvent successEvent) {
//                                this.command.markAsCompleted();
//                            }
//                        });
//
//            }
//        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.moveArm_fromInit_toPixelPlace();
                   CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this) {
                               public void onAfter(CommandAfterEvent event) {
                                   CompAutoBot.this.pixelCatcher.toggleLeftArm();
                                   CompAutoBot.this.pixelCatcher.toggleRightArm();
                               }
                           });
                    CompAutoBot.this.arm.wait(3000, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
//        CompAutoBot.this.scanForBackdrop(0, 100, CompAutoBot.this.robotAutoConfig.startingTrussDirection, 30, new PingHandler() {
//                    public void onPing(PingEvent event) {
//                        if (event.getDistance() > -1) {
//                            CompAutoBot.this.telemetry.addData("Distance: ", "%2f", event.getDistance());
//                            CompAutoBot.this.telemetry.update();
//                        }
//                    }
//                });
                CompAutoBot.this.driveTrain.scanSideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                        0.2, 0.2, 100, Units.Centimeters, CompAutoBot.this.backdropSensor, 20, new CommandCallbackAdapter(){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                IScanCommand result = (IScanCommand) successEvent.getCommand();
                                outerCommand.markAsCompleted();

                                CompAutoBot.this.telemetry.addData("Distance: ", "%2f", result.getDistance());
                                CompAutoBot.this.telemetry.update();

                                CompAutoBot.this.propLocation = CompAutoBot.this.robotAutoConfig.startingTrussDirection;

                                if (CompAutoBot.this.propLocation.equals(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert())) {
                                    CompAutoBot.this.autoRoutine_placeYellowPixel(result.getDistance());
                                }
                                else if (CompAutoBot.this.propLocation.equals(Direction.FORWARD)) {
                                    CompAutoBot.this.autoRoutine_placeYellowPixelMiddle();
                                }
                                else {
                                    CompAutoBot.this.autoRoutine_placeYellowPixelFar();
                                }

                            }
                        });

            }
        });
    }

    public void autoRoutine_placeYellowPixel (double distance) {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                double targetDistance = Math.abs(3.5 - distance);

                CompAutoBot.this.driveTrain.forward(0.2, 0.2, targetDistance, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                                CompAutoBot.this.autoRoutine_dropYellowPixel();
                            }
                        });
            }
        });





    }

    public void autoRoutine_placeYellowPixelMiddle () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.driveTrain.sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection, 0.2, 0.2, 19.5, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });

            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.pingBackdrop(new PingHandler() {
                    public void onPing(PingEvent event) {
                        command.markAsCompleted();
                        CompAutoBot.this.autoRoutine_placeYellowPixel(event.getDistance());
                    }
                });

            }
        });
    }

    public void autoRoutine_placeYellowPixelFar () {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.driveTrain.sideways(CompAutoBot.this.robotAutoConfig.startingTrussDirection, 0.2, 0.2, 38.5, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });

            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.pingBackdrop(new PingHandler() {
                    public void onPing(PingEvent event) {
                        command.markAsCompleted();
                        CompAutoBot.this.autoRoutine_placeYellowPixel(event.getDistance());
                    }
                });

            }
        });

    }

    public void autoRoutine_dropYellowPixel() {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.arm.closeRightClaw()
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });

            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.driveTrain.back(0.2, 0.2, 15, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });

            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                CompAutoBot.this.arm.moveMiddleToPosition(0.733, 1)
                        .wait(250)
                        .moveClawToPosition(0.096)
                        .wait(250)
                        .moveBottomToPosition(0.078, 0.002)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                this.command.markAsCompleted();
                            }
                        });
            }
        });
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


}
