package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import org.firstinspires.ftc.teamcode.metalheads.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrain;

/**
 *
 */
public class CompDriverBot extends CompBot {

    /**
     */
    protected MecanumDriveCompConfig driveTrainConfig;

    private Runnable driveTrainThread;

    /**
     */
    private MecanumDriveTrain driveTrain;

    public CompDriverBot () {
        super();

        this.driveTrainConfig = new MecanumDriveCompConfig(this);

        this.driveTrainThread = new Runnable(){
            @Override
            public void run() {
                CompDriverBot.this.driveTrain.run();
            }
        };
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
        this.driveTrain.init();
    }

    private boolean threadStarted = false;

    public void run () {
        super.run();

        this.driveTrain.run();
        this.driveTrain.run();
        this.arm.run();

        this.driveTrain.run();
        this.driveTrain.run();
        this.droneLauncher.run();

        this.driveTrain.run();
        this.driveTrain.run();
        this.pixelCatcher.run();

        this.driveTrain.run();
        this.driveTrain.run();
        this.lightBar.run();
    }
}
