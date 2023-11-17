package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
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

        this.armConfig.bottomBoomConfig.homePosition = this.robotConfig.rest_bottomBoom;
        this.armConfig.midBoomConfig.homePosition = this.robotConfig.rest_midBoom;
        this.armConfig.clawConfig.clawBoomConfig.homePosition = this.robotConfig.rest_clawBoom;
        this.armConfig.clawConfig.clawRotatorConfig.homePosition = this.robotConfig.pixelReady_clawRotator;

        this.pixelCatcherConfig.leftArmInitPos = PixelCatcher.ArmPosition.CLOSED;
        this.pixelCatcherConfig.rightArmInitPos = PixelCatcher.ArmPosition.CLOSED;
        this.pixelCatcherConfig.leftArmServoInitPos = 1;
        this.pixelCatcherConfig.rightArmServoInitPos = 1;

        this.armPosition = ArmPosition.REST;
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
        this.driveTrain.init();

        this.moveArm_fromRest_toPixelReady();

        // launch the drone
        this.addGp1_Dpad_Down_PressHandler(event -> {
            CompDriverBot.this.droneLauncher.launchDrone();
        });

        // hang ready
        this.addGp1_X_PressHandler(event -> {
            CompDriverBot.this.moveArm_toHangReady();
        });

        this.addGp1_Y_PressHandler(event -> {
            CompDriverBot.this.moveArm_toHang();
        });
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.driveTrain.run();
        this.arm.run();

        this.driveTrain.run();
        this.droneLauncher.run();

        this.driveTrain.run();
        this.pixelCatcher.run();

        this.driveTrain.run();
        this.lightBar.run();
    }
}
