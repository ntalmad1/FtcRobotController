package org.firstinspires.ftc.teamcode.archive.competition.base;

import org.firstinspires.ftc.teamcode.archive.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp1_a_press.Gp1_A_PressHandler;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressEvent;
import org.firstinspires.ftc.teamcode.library.event.gp2_y_press.Gp2_Y_PressHandler;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;

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

        this.pixelCatcherConfig.winchServoInitPos = this.pixelCatcherConfig.winchDownPosition;
        this.pixelCatcherConfig.winchInitPosition = PixelCatcher.WinchPosition.DOWN;
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

        this.addGp1_A_PressHandler(new Gp1_A_PressHandler() {
            @Override
            public void onGp1_A_Press(Gp1_A_PressEvent event) {
                CompDriverBot.this.moveArm_toHomePosition();
            }
        });

        this.addGp2_Y_PressHandler(new Gp2_Y_PressHandler() {
            @Override
            public void onGp2_Y_Press(Gp2_Y_PressEvent event) {
                CompDriverBot.this.arm.getLinearActuator().resetEncoder();
                CompDriverBot.this.armPosition = ArmPosition.INIT;
            }
        });
    }

    /**
     *
     */
    public void go() {
        super.go();

        //this.moveArm_fromInit_toPixelReady(PixelCatcher.WinchPosition.DOWN);
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.driveTrain.run();
        this.arm.run();
        this.pixelCatcher.run();
        this.winch.run();

        this.driveTrain.run();
        this.droneLauncher.run();
        this.pixelCatcher.run();
        this.winch.run();

        this.driveTrain.run();
        this.pixelCatcher.run();
        this.winch.run();

        this.driveTrain.run();
        this.pixelCatcher.run();
        this.winch.run();
    }

    /**
     *
     */
    public void toggleHangPosition () {
        if (this.hangPosition.equals(HangPosition.NONE)) {
            this.hangPosition = HangPosition.READY;

            this.telemetry.log().add("Doing hang 'READY'");

            this.moveArm_toHangReady();
        }
        else if (this.hangPosition.equals(HangPosition.READY)) {
            this.hangPosition = HangPosition.HANG;

            this.telemetry.log().add("Doing hang 'HANG'");

            this.doHang();
        }
        else if (this.hangPosition.equals(HangPosition.HANG)) {
            this.hangPosition = HangPosition.STOP;

            this.telemetry.log().add("Doing hang 'HANG STOP'");

            this.doHangStop();
        }
    }
}
