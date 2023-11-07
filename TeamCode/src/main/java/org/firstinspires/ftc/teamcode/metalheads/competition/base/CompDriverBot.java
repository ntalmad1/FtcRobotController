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
