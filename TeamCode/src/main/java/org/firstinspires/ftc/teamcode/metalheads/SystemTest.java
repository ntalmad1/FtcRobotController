package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.SimpleDriveCompConfig;

@TeleOp(name="System Test", group="Linear OpMode")
//@Disabled
public class SystemTest extends IsaacBot {

    /**
     */
    private SimpleDriveTrain driveTain;

    /**
     */
    private PixelCatcher pixelCatcher;

    /**
     */
    private boolean runDriveTrainTests = false;
    private boolean runPixelCatcherTests = true;

    /**
     * Constructor
     *
     */
    public SystemTest () {
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
                    SimpleDriveTrainConfig driveTrainConfig = new SimpleDriveCompConfig(SystemTest.this);
                    SystemTest.this.voiceLog("Initializing drive train...");
                    SystemTest.this.driveTain = new SimpleDriveTrain(driveTrainConfig);
                    SystemTest.this.driveTain.init();
                    SystemTest.this.voiceLog("Drive train initialized.", 2000);
                    command.markAsCompleted();
                }
            });


            // test left front motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {


                    SystemTest.this.voiceLog("Testing drive train left front motor...");
                    SystemTest.this.sleep(3000);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.1, 0.1, 10);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.2, 0.2, 20);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.3, 0.3, 30);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.4, 0.4, 40);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.5, 0.5, 50);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.6, 0.6, 60);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.7, 0.7, 70);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.8, 0.8, 80);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 0.9, 0.9, 90);
                    SystemTest.this.driveTain.spinLeftFront(Direction.FORWARD, 1, 1, 100);
                    SystemTest.this.driveTain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });
                }
            });

            // test right front motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing drive train right front motor...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.1, 0.1, 10);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.2, 0.2, 20);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.3, 0.3, 30);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.4, 0.4, 40);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.5, 0.5, 50);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.6, 0.6, 60);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.7, 0.7, 70);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.8, 0.8, 80);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 0.9, 0.9, 90);
                    SystemTest.this.driveTain.spinRightFront(Direction.FORWARD, 1, 1, 100);
                    SystemTest.this.driveTain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });
                }
            });

            // test right rear motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing drive train right rear motor...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.1, 0.1, 10);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.2, 0.2, 20);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.3, 0.3, 30);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.4, 0.4, 40);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.5, 0.5, 50);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.6, 0.6, 60);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.7, 0.7, 70);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.8, 0.8, 80);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 0.9, 0.9, 90);
                    SystemTest.this.driveTain.spinRightRear(Direction.FORWARD, 1, 1, 100);
                    SystemTest.this.driveTain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // test right rear motor
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing drive train left rear motor...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.1, 0.1, 10);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.2, 0.2, 20);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.3, 0.3, 30);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.4, 0.4, 40);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.5, 0.5, 50);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.6, 0.6, 60);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.7, 0.7, 70);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.8, 0.8, 80);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 0.9, 0.9, 90);
                    SystemTest.this.driveTain.spinLeftRear(Direction.FORWARD, 1, 1, 100);
                    SystemTest.this.driveTain.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
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
                    PixelCatcherConfig config = new PixelCatcherCompConfig(SystemTest.this);
                    SystemTest.this.voiceLog("Initializing pixel catcher...");
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher = new PixelCatcher(config);
                    SystemTest.this.pixelCatcher.init();
                    SystemTest.this.voiceLog("Pixel catcher initialized.", 2000);
                    command.markAsCompleted();
                }
            });

            // left arm
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing pixel catcher left arm...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.pixelCatcher.toggleLeftArm();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.toggleLeftArm();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // right arm
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing pixel catcher right arm...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.pixelCatcher.toggleRightArm();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.toggleRightArm();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
                            outerCommand.markAsCompleted();
                        }
                    });

                }
            });

            // pixel catcher winch
            this.addCommand(new OneTimeSynchronousCommand() {
                public void runOnce(ICommand outerCommand) {

                    SystemTest.this.voiceLog("Testing pixel winch...", 2000);
                    SystemTest.this.sleep(3000);
                    SystemTest.this.pixelCatcher.toggleWinch();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.toggleWinch();
                    SystemTest.this.sleep(2000);
                    SystemTest.this.pixelCatcher.addCommand(new OneTimeCommand() {
                        @Override
                        public void runOnce(ICommand command) {
                            SystemTest.this.voiceLog("Test complete");
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
                SystemTest.this.voiceLog("All system tests completed", 1500);
                command.markAsCompleted();
            }
        });
    }

    @Override
    public void run() {
        super.run();

        if (this.driveTain != null) {
            this.driveTain.run();
        }

        if (this.pixelCatcher != null) {
            this.pixelCatcher.run();
        }

    }
}
