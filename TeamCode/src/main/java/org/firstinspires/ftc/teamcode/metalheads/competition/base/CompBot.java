package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.library.lightbar.LightBarConfig;
import org.firstinspires.ftc.library.lightbar.LightBar;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.arm.Arm;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.DroneLauncherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.LightBarCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;

/**
 *
 */
@TeleOp(name="CompBot", group="Linear OpMode")
@Disabled
public class CompBot extends IsaacBot{

    /**
     */
    public enum ArmPosition {
        INIT,
        HOME,
        PIXEL_READY,
        PIXEL_PICK,
        PIXEL_PLACE_LOW,
        PIXEL_PLACE_HIGH,
        TRAVEL
    }

    /**
     */
    protected ArmCompConfig armConfig;

    /**
     */
    protected Arm arm;

    /**
     */
    private ArmPosition armPosition = ArmPosition.HOME;

    /**
     */
    protected DroneLauncher droneLauncher;

    /**
     */
    protected DroneLauncherConfig droneLauncherConfig;

    /**
     */
    protected PixelCatcherConfig pixelCatcherConfig;

    /**
     */
    protected PixelCatcher pixelCatcher;

    /**
     */
    protected LightBarConfig lightBarConfig;

    /**
     */
    protected LightBar lightBar;

    /**
     * Constructor
     *
     */
    public CompBot() {
        super();

        this.armConfig = new ArmCompConfig(this);
        this.armConfig.debug = true;

        this.droneLauncherConfig = new DroneLauncherCompConfig(this);

        this.pixelCatcherConfig = new PixelCatcherCompConfig(this);

        this.lightBarConfig = new LightBarCompConfig(this);

        this.addGp2_A_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.INIT)) {

            }
//
//            if (this.armPosition.equals(ArmPosition.HOME)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromHome_toPixelReady();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_READY) || this.armPosition.equals(ArmPosition.PIXEL_PICKED)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_PICKED;
//                CompBot.this.moveArm_pickPixels();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_TRAVEL)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromPixelTravel_toPixelReady();
//            }

        });

        this.addGp2_B_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();
//
//            if (this.armPosition.equals(ArmPosition.PIXEL_READY)
//             || this.armPosition.equals(ArmPosition.PIXEL_PICKED)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_TRAVEL;
//                CompBot.this.moveArm_toPixelTravel();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW)
//                    || this.armPosition.equals(ArmPosition.PIXEL_PLACE_MID)
//                    || this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_TRAVEL;
//                CompBot.this.moveArm_fromPixelPlace_toPixelTravel();
//            }
        });

        this.addGp2_X_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

//            if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
//                CompBot.this.moveArm_placePixelLow();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_MID)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_HIGH;
//                CompBot.this.moveArm_placePixelHigh();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_MID;
//                CompBot.this.moveArm_placePixelMid();
//            }
//            else {
//                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
//                CompBot.this.moveArm_placePixelLow();
//            }
        });

        this.addGp2_Y_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();
        });
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.lightBar = new LightBar(this.lightBarConfig);
        this.lightBar.init();

        this.arm = new Arm(armConfig);
        this.arm.init();

        this.droneLauncher = new DroneLauncher(this.droneLauncherConfig);
        this.droneLauncher.init();

        this.pixelCatcher = new PixelCatcher(this.pixelCatcherConfig);
        this.pixelCatcher.init();
    }

    /**
     *
     */
    public void go () {
        super.go();
    }

    /**
     *
     */
    public void run () {
        super.run();
//        this.arm.run();
//        this.droneLauncher.run();
//        this.pixelCatcher.run();
//        this.lightBar.run();
    }

    public void moveArm_fromInit_toPixelReady() {}
    public void moveArm_fromInit_toPixelPlace() {}
    public void moveArm_fromInit_toTravel() {}
    public void moveArm_fromInit_toHome () {}

    public void moveArm_fromPixelReady_toPixelPlace () {}
    public void moveArm_fromPixelReady_toTravel () {}
    public void moveArm_fromPixelReady_toHome () {}
    public void moveArm_fromPixelReady_doPixelPick () {}



//    /**
//     *
//     */
//    public void moveArm_fromHome_toPixelReady () {
//
//            this.arm.moveMiddleDegreesFromCurrentPosition(15)
//                    .wait(0)
//                    .moveMiddleToDegrees(-90, 1)
//                    .moveBottomDegreesFromCurrentPosition(-30)
//                    .wait(0)
//                    .moveBottomToPosition(0.166, 1)
//                    .moveMiddleToPosition(0.824,1)
//                    .moveClawToPosition(0.636, 1)
//                    .rotateClawToPosition(0.307, 1)
//                    .wait(0)
//            ;
//
//    }
//
//    /**
//     *
//     */
//    public void moveArm_fromPixelTravel_toPixelReady() {
//
//        this.arm.moveBottomToPosition(0.183, 1)
//                .moveMiddleToPosition(0.793,1)
//                .moveClawToPosition(0.636, 1)
//                .rotateClawToPosition(0.477, 1)
//                .wait(0)
//        ;
//    }
//
//    /**
//     *
//     */
//    public void moveArm_pickPixels () {
//
//            this.arm.closeLeftClaw()
//                    .closeRightClaw()
//                    .wait(250)
//                    .moveBottomToPosition(0.165)
//                    .wait(500)
//                    .openLeftClaw()
//                    .openRightClaw()
//                    .wait(250)
//                    .moveBottomToPosition(0.183, 1)
//                    .wait(0)
//            ;
//    }
//
//    /**
//     *
//     */
//    public void moveArm_toPixelTravel () {
//
//            this.arm.rotateClawToDegrees(0, 1)
//                    .moveClawToDegrees(60, 1)
//                    .moveMiddleToDegrees(60, 1)
//                    .wait(1000)
//                    .moveBottomToDegrees(-10, 1)
//                    .wait(1000)
//                    .moveBottomToPosition(0.568, 1)
//                    .moveMiddleToPosition(0.994, 1)
//                    .wait(2000)
//                    .moveClawToDegrees(-5, 1)
//                    .wait(0)
//            ;
//
//    }
//
//    /**
//     *
//     */
//    public void moveArm_fromPixelPlace_toPixelTravel () {
//
//        this.arm.moveMiddleToPosition(0.994, 1)
//                .wait(2000)
//                .moveBottomToPosition(0.568, 1)
//                .moveClawToPosition(0.562, 1)
//                .wait(0)
//        ;
//    }
//
//    /**
//     *
//     */
//    public void moveArm_placePixelLow() {
//
//        this.arm.moveBottomToPosition(0.406, 1)
//                .moveMiddleToPosition(0.318, 1)
//                .moveClawToPosition(0.4, 1)
//                .rotateClawToPosition(0.486)
//                .wait(0);
//    }
//
//    /**
//     *
//     */
//    public void moveArm_placePixelMid() {
//
//        this.arm.moveBottomToPosition(0.322, 1)
//                .moveMiddleToPosition(0.2938, 1)
//                .moveClawToPosition(0.3472, 1)
//                .rotateClawToPosition(0.492)
//                .wait(0);
//
//    }
//
//    /**
//     *
//     */
//    public void moveArm_placePixelHigh () {
//
//        this.arm.moveBottomToPosition(0.345, 1)
//                .moveMiddleToPosition(0.431, 1)
//                .moveClawToPosition(0.217, 1)
//                .rotateClawToPosition(0.491)
//                .wait(0);
//
//    }

    /**
     *
     * @param milliseconds
     * @param callbackHandler
     * @return
     */
    public CompBot wait (int milliseconds, CommandCallbackHandler callbackHandler) {
        return (CompBot) super.wait(milliseconds, callbackHandler);
    }


}
