package org.firstinspires.ftc.teamcode.archive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.archive.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.archive.competition.config.DroneLauncherCompConfig;
import org.firstinspires.ftc.teamcode.archive.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.archive.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.archive.library.arm.Arm;
import org.firstinspires.ftc.teamcode.archive.library.arm.ArmConfig;
import org.firstinspires.ftc.teamcode.library.command.ICommand;
import org.firstinspires.ftc.teamcode.library.command.OneTimeCommand;
import org.firstinspires.ftc.teamcode.library.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.command.WaitCommand;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.teamcode.archive.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.teamcode.archive.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.teamcode.library.utility.Direction;
import org.firstinspires.ftc.teamcode.archive.library.winch.Winch;

@TeleOp(name="System Tests", group="Tests")
@Disabled
public class SystemTests extends IsaacBot {

    /**
     */
    private SimpleDriveTrain driveTrain;

    /**
     */
    private PixelCatcher pixelCatcher;

    /**
     */
    private Arm arm;

    /**
     */
    private Winch winch;

    /**
     */
    private DroneLauncher droneLauncher;

    /**
     */
    private boolean runDriveTrainTests = false;
    private boolean runPixelCatcherTests = true;
    private boolean runArmTests = false;
    private boolean runWinchTest = false;
    private boolean runDroneLauncherTests = false;

    /**
     * Constructor
     *
     */
    public SystemTests() {
        super();
    }

    @Override
    public void initBot() {
        super.initBot();
        this.voiceLog("Ready to run system tests.");
    }

    @Override
    public void go() {
        super.go();

        if (this.runDriveTrainTests) {

            // drive train
            this.addCommand(new OneTimeSynchronousCommand() {
                @Override
                public void runOnce(ICommand command) {
                    SimpleDriveTrainConfig driveTrainConfig = new SimpleDriveCompConfig(SystemTests.this);
                    SystemTests.this.voiceLog("Initializing drive train...");
                    SystemTests.this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
                    SystemTests.this.driveTrain.init();
                    SystemTests.this.voiceLog("Drive train initialized.");
                    command.markAsCompleted();
                }
            });


            // test left front motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing drive train left front motor...", 3000);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.1, 0.1, 5);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.2, 0.2, 10);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.3, 0.3, 15);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.4, 0.4, 20);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.5, 0.5, 25);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.6, 0.6, 30);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.7, 0.7, 35);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.8, 0.8, 40);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 0.9, 0.9, 45);
                    SystemTests.this.driveTrain.spinLeftFront(Direction.FORWARD, 1, 1, 50);
                    SystemTests.this.driveTrain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });
                }
            });

            // test right front motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing drive train right front motor...", 3000);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.1, 0.1, 5);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.2, 0.2, 10);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.3, 0.3, 15);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.4, 0.4, 20);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.5, 0.5, 25);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.6, 0.6, 30);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.7, 0.7, 35);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.8, 0.8, 40);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 0.9, 0.9, 45);
                    SystemTests.this.driveTrain.spinRightFront(Direction.FORWARD, 1, 1, 50);
                    SystemTests.this.driveTrain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });
                }
            });

            // test right rear motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing drive train right rear motor...", 3000);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.1, 0.1, 5);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.2, 0.2, 10);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.3, 0.3, 15);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.4, 0.4, 20);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.5, 0.5, 25);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.6, 0.6, 30);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.7, 0.7, 35);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.8, 0.8, 40);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 0.9, 0.9, 45);
                    SystemTests.this.driveTrain.spinRightRear(Direction.FORWARD, 1, 1, 50);
                    SystemTests.this.driveTrain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // test right rear motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing drive train left rear motor...", 3000);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.1, 0.1, 5);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.2, 0.2, 10);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.3, 0.3, 15);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.4, 0.4, 20);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.5, 0.5, 25);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.6, 0.6, 30);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.7, 0.7, 35);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.8, 0.8, 40);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 0.9, 0.9, 45);
                    SystemTests.this.driveTrain.spinLeftRear(Direction.FORWARD, 1, 1, 50);
                    SystemTests.this.driveTrain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });
        }

        if (this.runPixelCatcherTests) {

            // pixel catcher
            this.addCommand(new OneTimeSynchronousCommand() {
                @Override
                public void runOnce(ICommand command) {
                    PixelCatcherConfig config = new PixelCatcherCompConfig(SystemTests.this);
                    SystemTests.this.voiceLog("Initializing pixel catcher...");
                    SystemTests.this.pixelCatcher = new PixelCatcher(config);
                    SystemTests.this.pixelCatcher.init();
                    SystemTests.this.voiceLog("Pixel catcher initialized.");
                    command.markAsCompleted();
                }
            });

            // left arm
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing pixel catcher left arm...", 3000);
                    SystemTests.this.pixelCatcher.toggleLeftArm();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.toggleLeftArm();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // right arm
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing pixel catcher right arm...", 3000);
                    SystemTests.this.pixelCatcher.toggleRightArm();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.toggleRightArm();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // pixel catcher winch
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing pixel catcher winch...", 3000);
                    SystemTests.this.pixelCatcher.toggleWinch();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.toggleWinch();
                    SystemTests.this.sleep(2000);
                    SystemTests.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });
        }

        if (this.runArmTests) {

            // arm
            this.addCommand(new OneTimeSynchronousCommand() {
                @Override
                public void runOnce(ICommand command) {
                    ArmConfig config = new ArmCompConfig(SystemTests.this);
                    config.debug = true;
                    SystemTests.this.voiceLog("Initializing arm...");
                    SystemTests.this.arm = new Arm(config);
                    SystemTests.this.arm.init();
                    SystemTests.this.voiceLog("Arm initialized.");
                    command.markAsCompleted();
                }
            });

            // left claw
             this.addCommand(new OneTimeSynchronousCommand() {
               public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing left claw pincher...", 1500);
                    SystemTests.this.arm.getClaw().toggleLeftClaw();
                    SystemTests.this.sleep(1000);
                    SystemTests.this.arm.getClaw().toggleLeftClaw();
                    SystemTests.this.sleep(1000);
                    SystemTests.this.arm.getClaw().addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // right claw
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing right claw pincher...", 1500);
                    SystemTests.this.arm.getClaw().toggleRightClaw();
                    SystemTests.this.sleep(1000);
                    SystemTests.this.arm.getClaw().toggleRightClaw();
                    SystemTests.this.sleep(1000);
                    SystemTests.this.arm.getClaw().addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // claw base
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing claw base...", 1000);
                    SystemTests.this.arm.getClaw().getBase().gotoPosition(SystemTests.this.arm.getClaw().getConfig().clawBoomConfig.zeroDegreePosition);
                    SystemTests.this.arm.getClaw().getBase().addCommand(new WaitCommand(1000));
                    SystemTests.this.arm.getClaw().getBase().gotoPosition(SystemTests.this.arm.getClaw().getConfig().clawBoomConfig.homePosition);
                    SystemTests.this.arm.getClaw().getBase().addCommand(new WaitCommand(1000));
                    SystemTests.this.arm.getClaw().getBase().addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // bottom boom
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing bottom boom...", 1000);
                    SystemTests.this.arm.getBottomBoom().gotoPosition(0.3);
                    SystemTests.this.arm.getBottomBoom().addCommand(new WaitCommand(1000));
                    SystemTests.this.arm.getBottomBoom().gotoPosition(SystemTests.this.arm.getBottomBoom().getConfig().homePosition);
                    SystemTests.this.arm.getBottomBoom().addCommand(new WaitCommand(1000));
                    SystemTests.this.arm.getBottomBoom().addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // linear actuator
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing linear actuator...", 1000);
                    SystemTests.this.arm.moveBottomToPosition(0.3);
                    SystemTests.this.arm.moveClawToPosition(0.3);
                    SystemTests.this.arm.wait(1000);
                    SystemTests.this.arm.moveLinearActuatorToPosition(1000);
                    SystemTests.this.arm.moveLinearActuatorToPosition(0);
                    SystemTests.this.arm.wait(1000);
                    SystemTests.this.arm.moveBottomToPosition(SystemTests.this.arm.getBottomBoom().getConfig().homePosition);
                    SystemTests.this.arm.moveClawToPosition(SystemTests.this.arm.getClaw().getConfig().clawBoomConfig.homePosition);
                    SystemTests.this.arm.wait(1000);
                    SystemTests.this.arm.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });
        }

        if (this.runDroneLauncherTests) {

            // drone launcher
            this.addCommand(new OneTimeSynchronousCommand() {
                @Override
                public void runOnce(ICommand command) {
                    DroneLauncherConfig config = new DroneLauncherCompConfig(SystemTests.this);
                    SystemTests.this.voiceLog("Initializing drone launcher...");
                    SystemTests.this.droneLauncher = new DroneLauncher(config);
                    SystemTests.this.droneLauncher.init();
                    SystemTests.this.voiceLog("Drone launcher initialized.");
                    command.markAsCompleted();
                }
            });

            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTests.this.voiceLog("Testing drone launcher...", 1500);
                    SystemTests.this.droneLauncher.launchDrone();
                    SystemTests.this.droneLauncher.addCommand(new WaitCommand(2000));
                    SystemTests.this.droneLauncher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTests.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });
        }

        // END - all test done
        this.addCommand(new OneTimeSynchronousCommand() {
            @Override
            public void runOnce(ICommand command) {
                SystemTests.this.voiceLog("All system tests completed.");
                command.markAsCompleted();
            }
        });
    }

    @Override
    public void run() {
        super.run();

        if (this.driveTrain != null) {
            this.driveTrain.run();
        }

        if (this.pixelCatcher != null) {
            this.pixelCatcher.run();
        }

        if (this.arm != null) {
            this.arm.run();
        }

        if (this.winch != null) {
            this.winch.run();
        }

        if (this.droneLauncher != null) {
            this.droneLauncher.run();
        }

    }
}
