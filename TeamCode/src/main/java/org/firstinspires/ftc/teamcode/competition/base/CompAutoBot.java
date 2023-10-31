package org.firstinspires.ftc.teamcode.competition.base;

import org.firstinspires.ftc.teamcode.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.OneTimeCommand;
import org.firstinspires.ftc.teamcode.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackAdapter;
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

    public CompAutoBot() {
        super();

        this.armConfig.clawConfig.leftClawInitPosition = 0.35;
        this.armConfig.clawConfig.rightClawInitPosition = 0.35;
        this.armConfig.debug = true;

        this.driveTrainConfig = new SimpleDriveCompConfig(this);
        this.setImuName(driveTrainConfig.imuName);
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
        this.driveTrain.init();

        telemetry.addLine("Drive train initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.addCommand(new OneTimeSynchronousCommand(){
            public void runOnce() {
                CompAutoBot.this.arm.moveMiddleDegreesFromCurrentPosition(15)
                        .wait(0)
                        .moveMiddleToDegrees(-90, 1)
                        .moveBottomDegreesFromCurrentPosition(-30)
                        .wait(1000)
                        .moveBottomToPosition(0.147, 1)
                        .moveMiddleToPosition(0.678,1)
                        .moveClawToPosition(0.702, 1)
                        .rotateClawToPosition(0.307, 1)
                        .wait(3000)
                        .moveBottomToPosition(0.130, 1)
                        .wait(0, new CommandCallbackAdapter(this){
                            public void onAfter(CommandAfterEvent event){
                                this.command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeCommand() {
            public void runOnce() {
                CompAutoBot.this.driveTrain.forward(0.1, 0.2, 10, Units.Centimeters);
                CompAutoBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onAfter(CommandAfterEvent event){
                        this.command.markAsCompleted();
                    }
                });
            }
        });
    }

    /**
     *
     */
    public void run () {
        super.run();
        driveTrain.run();
    }
}
