package org.firstinspires.ftc.teamcode.competition.base;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.competition.auto.BlueFarCompAutoBot;
import org.firstinspires.ftc.teamcode.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.ICommand;
import org.firstinspires.ftc.teamcode.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.teamcode.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.teamcode.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.teamcode.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.utility.Units;

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

    /**
     *
     */
    public void go () {

        this.addGp2_A_PressHandler(event -> {
            telemetry.addData("Distance: ", "%2f", CompAutoBot.this.sonar.getDistance(DistanceUnit.CM));
            telemetry.update();
        });

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
                        .wait(1000)
                        .moveBottomToPosition(0.147, 1)
                        .moveMiddleToPosition(0.678,1)
                        .moveClawToPosition(0.700, 1)
                        .rotateClawToPosition(0.307, 1)
                        .wait(3000)
                        .moveBottomToPosition(0.130, 1)
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
        this.addCommand(this.bumpForwardCommand);

        // put arm into purple placing position
        this.addCommand(this.deployArmCommand);
    }

    /**
     *
     */
    public void run () {
        super.run();
        driveTrain.run();
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
                        .forward(0.2, 0.1, 23.5, Units.Centimeters)
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
                        .closeRightClaw()
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
                CompAutoBot.this.moveArm_toPixelTravel();
                CompAutoBot.this.arm.wait(0, new CommandCallbackAdapter(this){
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
                        CompAutoBot.this.autoRoutine_beginStepTwo(92);
                    }
                });
            }
        });
    }

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
                CompAutoBot.this.scan(67, 77, 1000, 30, new PingHandler() {
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
                        .closeRightClaw()
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
                CompAutoBot.this.moveArm_toPixelTravel();
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

    }

    /**
     *
     * @param distance
     */
    protected void autoRoutine_beginStepTwo (int distance) {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                CompAutoBot.this.driveTrain.forward(0.1, 0.4, distance, Units.Centimeters)
                        .sideways( CompAutoBot.this.robotAutoConfig.startingTrussDirection,
                                0.1, 0.2, 25, Units.Centimeters)
                        .gyroTurn(CompAutoBot.this.robotAutoConfig.startingTrussDirection.invert(), 0.1, 0.3, 90)
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
