package org.firstinspires.ftc.teamcode.competition.base;

import org.firstinspires.ftc.teamcode.competition.base.CompBot;
import org.firstinspires.ftc.teamcode.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;

/**
 *
 */
public class CompDriverBot extends CompBot {

    /**
     */
    protected MecanumDriveCompConfig driveTrainConfig;

    /**
     */
    private MecanumDriveTrain driveTrain;

    public CompDriverBot () {
        super();

        this.driveTrainConfig = new MecanumDriveCompConfig(this);
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
        this.driveTrain.init();
    }

    public void run () {
        super.run();
        this.driveTrain.run();
    }
}
