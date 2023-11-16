package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncher;
import org.firstinspires.ftc.library.dronelauncher.DroneLauncherConfig;
import org.firstinspires.ftc.library.lightbar.LightBarConfig;
import org.firstinspires.ftc.library.lightbar.LightBar;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.arm.Arm;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.DroneLauncherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.LightBarCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.PixelCatcherCompConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotConfig;

/**
 *
 */
@TeleOp(name="CompBot", group="Linear OpMode")
@Disabled
public class CompBot extends IsaacBot{

    /*I*

     */
    protected RobotConfig robotConfig;

    /**
     */
    public enum ArmPosition {
        INIT,
        HOME,
        PIXEL_READY,
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
    private ArmPosition armPosition = ArmPosition.INIT;

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
     */
    protected DistanceSensor backdropSensor;

    /**
     * Constructor
     *
     */
    public CompBot() {
        super();

        this.robotConfig = new RobotConfig();

        this.armConfig = new ArmCompConfig(this);
        this.armConfig.debug = false;

        this.droneLauncherConfig = new DroneLauncherCompConfig(this);

        this.pixelCatcherConfig = new PixelCatcherCompConfig(this);

        this.lightBarConfig = new LightBarCompConfig(this);

        //-------------------------------------------------
        // A Button
        //-------------------------------------------------
//        this.addGp2_A_PressHandler(event -> {
//            CompBot.this.arm.cancelAllCommands();
//
//            if (this.armPosition.equals(ArmPosition.INIT)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromInit_toPixelReady();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW) ||
//                    this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromPixelPlace_toPixelReady();
//            }
//            else if (this.armPosition.equals(ArmPosition.TRAVEL)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromTravel_toPixelReady();
//            }
//            else if (this.armPosition.equals(ArmPosition.PIXEL_READY) ||
//                    this.armPosition.equals(ArmPosition.HOME)) {
//                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
//                CompBot.this.moveArm_fromPixelReady_doPixelPick();
//            }
//
//        });
        this.addGp2_A_PressHandler(event -> {
            CompBot.this.pingBackdrop(new PingHandler() {
                @Override
                public void onPing(PingEvent event) {
                    CompBot.this.telemetry.addData("Ping Backdrop: ", "%2f", event.getDistance());
                    CompBot.this.telemetry.update();
                }
            });
        });

        //-------------------------------------------------
        // B Button - Travel
        //-------------------------------------------------
        this.addGp2_B_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.INIT)) {
                CompBot.this.armPosition = ArmPosition.TRAVEL;
                CompBot.this.moveArm_fromInit_toTravel();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
                CompBot.this.armPosition = ArmPosition.TRAVEL;
                CompBot.this.moveArm_fromPixelReady_toTravel();
            }
            else if (this.armPosition.equals(ArmPosition.HOME)) {
                CompBot.this.armPosition = ArmPosition.TRAVEL;
                CompBot.this.moveArm_fromHome_toTravel();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW) ||
                     this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
                CompBot.this.armPosition = ArmPosition.TRAVEL;
                CompBot.this.moveArm_fromPixelPlace_toTravel();
            }

        });

        //-------------------------------------------------------
        // X Button
        //-------------------------------------------------------
        this.addGp2_X_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.INIT)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_fromInit_toPixelPlace();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_HIGH;
                CompBot.this.moveArm_fromPixelPlace_toPixelPlaceHigh();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_fromPixelPlaceHigh_toPixelPlace();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_fromPixelReady_toPixelPlace();
            }
            else if (this.armPosition.equals(ArmPosition.HOME)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_fromHome_toPixelPlace();
            }
            else if (this.armPosition.equals(ArmPosition.TRAVEL)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_fromTravel_toPixelPlace();
            }
        });

        //-----------------------------------------------------
        // Y Button - Home
        //-----------------------------------------------------
        this.addGp2_Y_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.INIT)) {
                CompBot.this.armPosition = ArmPosition.HOME;
                CompBot.this.moveArm_fromInit_toHome();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW) ||
                     this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
                CompBot.this.armPosition = ArmPosition.HOME;
                CompBot.this.moveArm_fromPixelPlace_toHome();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
                CompBot.this.armPosition = ArmPosition.HOME;
                CompBot.this.moveArm_fromPixelReady_toHome();
            }
            else if (this.armPosition.equals(ArmPosition.TRAVEL)) {
                CompBot.this.armPosition = ArmPosition.HOME;
                CompBot.this.moveArm_fromTravel_toHome();
            }
        });
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.backdropSensor = this.hardwareMap.get(DistanceSensor.class, "backdropSensor");

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
    }

    public void moveArm_fromInit_toPixelReady() { _moveArm_fromInit_toPixelReady(); }
    public void moveArm_fromInit_toPixelPlace() { _moveArm_fromInit_toPixelPlace(); }
    public void moveArm_fromInit_toTravel() { _moveArm_fromInit_toTravel(); }
    public void moveArm_fromInit_toHome () { _moveArm_fromInit_toHome();}

    public void moveArm_fromPixelReady_toPixelPlace () { _moveArm_fromPixelReady_toPixelPlace(); }
    public void moveArm_fromPixelReady_toTravel () { _moveArm_fromPixelReady_toTravel(); }
    public void moveArm_fromPixelReady_toHome () { _moveArm_fromPixelReady_toHome(); }

    public void moveArm_fromPixelReady_doPixelPick () { _moveArm_fromPixelReady_doPickPixels(); }

    public void moveArm_fromHome_toPixelPlace () { _moveArm_fromHome_toPixelPlace(); }
    public void moveArm_fromHome_toTravel () { _moveArm_fromHome_toTravel(); }

    public void moveArm_fromPixelPlace_toPixelPlaceHigh () { _moveArm_fromPixelPlace_toPixelPlaceHigh(); }
    public void moveArm_fromPixelPlace_toPixelReady () { _moveArm_fromPixelPlace_toPixelReady(); }
    public void moveArm_fromPixelPlace_toHome () { _moveArm_fromPixelPlace_toHome(); }
    public void moveArm_fromPixelPlace_toTravel () { _moveArm_fromPixelPlace_toTravel(); }
    public void moveArm_fromPixelPlaceHigh_toPixelPlace ( ) { _moveArm_fromPixelPlaceHigh_toPixelPlace(); }

    public void moveArm_fromTravel_toPixelReady () { _moveArm_fromTravel_toPixelReady(); }
    public void moveArm_fromTravel_toHome () { _moveArm_fromTravel_toHome(); }
    public void moveArm_fromTravel_toPixelPlace () { _moveArm_fromTravel_toPixelPlace(); }

    public void moveArm_fromTravel_toBackdropScan () { _moveArm_fromTravel_toBackdropScan(); }

    private void _moveArm_fromInit_toPixelReady () {
            this.arm.rotateClawToPosition(0.307, 1)
                    .moveMiddleDegreesFromCurrentPosition(15)
                    .wait(0)
                    .moveMiddleToDegrees(-90, 1)
                    .moveBottomDegreesFromCurrentPosition(-30)
                    .wait(0)
                    .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                    .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                    .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                    .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                    .wait(0);
    }

    private void _moveArm_fromInit_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleDegreesFromCurrentPosition(15)
                .wait(0)
                .moveMiddleToDegrees(-90, 1)
                .moveBottomDegreesFromCurrentPosition(-30)
                .wait(0)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromInit_toTravel () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleDegreesFromCurrentPosition(15)
                .wait(0)
                .moveMiddleToDegrees(-90, 1)
                .moveBottomDegreesFromCurrentPosition(-30)
                .wait(0)
                .moveBottomToPosition(0.047, 1)
                .moveMiddleToPosition(0.527,1)
                .moveClawToPosition(0.828, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromInit_toHome () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleDegreesFromCurrentPosition(15)
                .wait(0)
                .moveMiddleToDegrees(-90, 1)
                .moveBottomDegreesFromCurrentPosition(-30)
                .wait(0)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlace_toPixelPlaceHigh () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.135, 1)
                .moveMiddleToPosition(0.606, 1)
                .moveClawToPosition(0.593, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlace_toHome() {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlace_toPixelReady() {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlace_toTravel () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleToPosition(0.527,1)
                .wait(500)
                .moveBottomToPosition(0.075, 0.015)
                .wait(500)
                .moveBottomToPosition(0.047, 0.015)
                .moveClawToPosition(0.828, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelReady_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelReady_toTravel () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleToPosition(0.689,1)
                .moveBottomToPosition(0.150, 1)
                .wait(500)
                .moveMiddleToPosition(0.527,1)
                .wait(500)
                .moveBottomToPosition(0.075, 0.015)
                .wait(500)
                .moveBottomToPosition(0.047, 0.015)
                .moveClawToPosition(0.828, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelReady_toHome () {

    }

    private void _moveArm_fromPixelPlaceHigh_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromHome_toTravel () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveMiddleToPosition(0.689,1)
                .moveBottomToPosition(0.150, 1)
                .wait(500)
                .moveMiddleToPosition(0.527,1)
                .wait(500)
                .moveBottomToPosition(0.075, 0.015)
                .wait(500)
                .moveBottomToPosition(0.047, 0.015)
                .moveClawToPosition(0.828, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromHome_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromHome_toPixelReady () {

    }

    private void _moveArm_fromTravel_toPixelReady() {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.180, 1)
                .moveMiddleToPosition(0.731, 1)
                .moveClawToPosition(0.507, 1)
                .wait(1000)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromTravel_toHome() {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.180, 1)
                .moveMiddleToPosition(0.731, 1)
                .moveClawToPosition(0.507, 1)
                .wait(1000)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromTravel_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromTravel_toBackdropScan () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveBottomToPosition(0.180, 1)
                .moveMiddleToPosition(0.731,1)
                .moveClawToPosition(0.507, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    /**
     *
     */
    public void _moveArm_fromPixelReady_doPickPixels () {

            this.arm.closeLeftClaw()
                    .closeRightClaw()
                    .wait(350)
                    .moveBottomToPosition(0.153)
                    .wait(500)
                    .openLeftClaw()
                    .openRightClaw()
                    .wait(250)
                    .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                    .wait(0)
            ;
    }

    /**
     *
     * @param handler
     */
    protected void pingBackdrop (PingHandler handler) {
        PingEvent event = new PingEvent(0, this.backdropSensor.getDistance(DistanceUnit.CM), DistanceUnit.CM);
        handler.onPing(event);
    }

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
