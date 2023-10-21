package org.firstinspires.ftc.teamcode.competition.base;

import org.firstinspires.ftc.teamcode.competition.config.SimpleDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;

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

        this.driveTrainConfig = new SimpleDriveCompConfig(this);
        this.setImuName(driveTrainConfig.imuName);
    }

    public void initBot () {
        //super.initBot();

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


    }

    /**
     *
     */
    public void run () {

    }
}
