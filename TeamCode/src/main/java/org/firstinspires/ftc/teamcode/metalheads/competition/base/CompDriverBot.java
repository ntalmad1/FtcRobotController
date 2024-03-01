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
    public enum HangPosition {

        /**
         */
        NONE,

        /**
         */
        READY,

        /**
         */
        HANG,

        /**
         */
        STOP

    }

    /**
     */
    protected MecanumDriveCompConfig driveTrainConfig;

    /**
     */
    private MecanumDriveTrain driveTrain;

    /**
     */
    private HangPosition hangPosition = HangPosition.NONE;

    public CompDriverBot () {
        super();

        this.driveTrainConfig = new MecanumDriveCompConfig(this);

        //this.armConfig.bottomBoomConfig.homePosition = this.robotConfig.rest_bottomBoom;
        //this.armConfig.midBoomConfig.homePosition = this.robotConfig.rest_midBoom;
        //this.armConfig.clawConfig.clawBoomConfig.homePosition = this.robotConfig.rest_clawBoom;

//        this.pixelCatcherConfig.leftArmInitPos = PixelCatcher.ArmPosition.CLOSED;
//        this.pixelCatcherConfig.rightArmInitPos = PixelCatcher.ArmPosition.CLOSED;
//        this.pixelCatcherConfig.leftArmServoInitPos = 1;
//        this.pixelCatcherConfig.rightArmServoInitPos = 1;

        // this.armPosition = ArmPosition.REST;
    }

    public void initBot () {
        super.initBot();

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
        this.driveTrain.init();

        // launch the drone
        this.addGp1_Dpad_Down_PressHandler(event -> {
            CompDriverBot.this.droneLauncher.launchDrone();
        });

        // hang ready
        this.addGp1_X_PressHandler(event -> {
            CompDriverBot.this.toggleHangPosition();
        });

        this.addGp1_Y_PressHandler(event -> {
            CompDriverBot.this.toggleHangPosition();
        });
    }

    /**
     *
     */
    public void go() {
        super.go();

        this.moveArm_fromInit_toPixelReady();
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
        this.winch.run();
    }

    /**
     *
     */
    public void toggleHangPosition () {
        if (this.hangPosition.equals(HangPosition.NONE)) {
            this.hangPosition = HangPosition.READY;
            this.moveArm_toHangReady();
        }
        else if (this.hangPosition.equals(HangPosition.READY)) {
            this.hangPosition = HangPosition.HANG;
            this.doHang();
        }
        else if (this.hangPosition.equals(HangPosition.HANG)) {
            this.hangPosition = HangPosition.STOP;
            this.doHangStop();
        }
    }
}
