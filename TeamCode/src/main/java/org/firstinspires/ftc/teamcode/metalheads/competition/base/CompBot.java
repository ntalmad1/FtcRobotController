package org.firstinspires.ftc.teamcode.metalheads.competition.base;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
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
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.ArmCompConfig;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.arm.Arm;
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
        PIXEL_READY,
        PIXEL_PLACE_ROW1,
        PIXEL_PLACE_ROW2,
        PIXEL_PLACE_ROW3,
        TRAVEL,
        REST
    }

    /**
     */
    protected ArmCompConfig armConfig;

    /**
     */
    protected Arm arm;

    /**
     */
    protected ArmPosition armPosition = ArmPosition.INIT;

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

    private boolean pickingPixel = false;

    /**
     * Constructor
     *
     */
    public CompBot() {
        super();

        this.robotConfig = new RobotConfig();

        this.armConfig = new ArmCompConfig(this);
        this.armConfig.debug = true;

        this.droneLauncherConfig = new DroneLauncherCompConfig(this);

        this.pixelCatcherConfig = new PixelCatcherCompConfig(this);

        this.lightBarConfig = new LightBarCompConfig(this);

        //-------------------------------------------------
        // A Button
        //-------------------------------------------------
        this.addGp2_A_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();
            this.onAPress();

        });

        //-------------------------------------------------
        // B Button - Travel
        //-------------------------------------------------
        this.addGp2_B_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.pickingPixel) {
                this.addCommand(new OneTimeSynchronousCommand() {
                    public void runOnce(ICommand command) {
                        CompBot.this.arm.moveBottomToPosition(CompBot.this.robotConfig.pixelReady_bottomBoom, 1)
                                .wait(250, new CommandCallbackAdapter(){
                                    public void onSuccess(CommandSuccessEvent successEvent) {
                                        CompBot.this.pickingPixel = false;
                                        CompBot.this.onBPress();
                                    }
                                });
                    }
                });
            }
            else {
                this.onBPress();
            }



        });

        //-------------------------------------------------------
        // X Button
        //-------------------------------------------------------
        this.addGp2_X_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.pickingPixel) {
                this.addCommand(new OneTimeSynchronousCommand() {
                    public void runOnce(ICommand command) {
                        CompBot.this.arm.moveBottomToPosition(CompBot.this.robotConfig.pixelReady_bottomBoom, 1)
                                .wait(250, new CommandCallbackAdapter(){
                                    public void onSuccess(CommandSuccessEvent successEvent) {
                                        CompBot.this.pickingPixel = false;
                                        CompBot.this.onXPress();
                                    }
                                });
                    }
                });
            }
            else {
                this.onXPress();
            }


        });
    }

    private void onAPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromInit_toPixelReady();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromPixelPlace_toPixelReady();
        }
        else if (this.armPosition.equals(ArmPosition.TRAVEL)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromTravel_toPixelReady();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_READY;
            CompBot.this.moveArm_fromPixelReady_doPixelPick();
        }
    }

    private void onBPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.TRAVEL;
            CompBot.this.moveArm_fromInit_toTravel();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.armPosition = ArmPosition.TRAVEL;
            CompBot.this.moveArm_fromPixelReady_toTravel();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2) ||
                this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.TRAVEL;
            CompBot.this.moveArm_fromPixelPlace_toTravel();
        }
    }

    private void onXPress () {
        if (this.armPosition.equals(ArmPosition.INIT)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromInit_toPixelPlace();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW1)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW2;
            CompBot.this.moveArm_fromPixelPlace_toPixelPlaceRow2();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW2)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW3;
            CompBot.this.moveArm_fromPixelPlaceRow2_toPixelPlaceRow3();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_ROW3)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromPixelPlaceHigh_toPixelPlace();
        }
        else if (this.armPosition.equals(ArmPosition.PIXEL_READY)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromPixelReady_toPixelPlace();
        }
        else if (this.armPosition.equals(ArmPosition.TRAVEL)) {
            CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_ROW1;
            CompBot.this.moveArm_fromTravel_toPixelPlace();
        }
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

    public void moveArm_fromPixelReady_toPixelPlace () { _moveArm_fromPixelReady_toPixelPlace(); }
    public void moveArm_fromPixelReady_toTravel () { _moveArm_fromPixelReady_toTravel(); }

    public void moveArm_fromPixelReady_doPixelPick () { _moveArm_fromPixelReady_doPickPixels(); }

    public void moveArm_fromPixelPlace_toPixelReady () { _moveArm_fromPixelPlace_toPixelReady(); }
    public void moveArm_fromPixelPlace_toTravel () { _moveArm_fromPixelPlace_toTravel(); }

    public void moveArm_fromPixelPlace_toPixelPlaceRow2 () { _moveArm_fromPixelPlace_toPixelPlaceRow2(); }
    public void moveArm_fromPixelPlaceRow2_toPixelPlaceRow3 () { _moveArm_fromPixelPlaceRow2_toPixelPlaceRow3(); }
    public void moveArm_fromPixelPlaceHigh_toPixelPlace () { _moveArm_fromPixelPlaceHigh_toPixelPlace(); }

    public void moveArm_fromTravel_toPixelReady () { _moveArm_fromTravel_toPixelReady(); }
    public void moveArm_fromTravel_toPixelPlace () { _moveArm_fromTravel_toPixelPlace(); }

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

    private void _moveArm_fromPixelPlace_toPixelPlaceRow2 () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(this.robotConfig.pixelPlaceRow2_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlaceRow2_midBoom, 1)
                .moveClawToPosition(this.robotConfig.pixelPlaceRow2_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlaceRow2_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlaceRow2_toPixelPlaceRow3 () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(this.robotConfig.pixelPlaceRow3_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlaceRow3_midBoom, 1)
                .moveClawToPosition(this.robotConfig.pixelPlaceRow3_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlaceRow3_clawRotator, 1)
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
                .moveBottomToPosition(0.075, CompBot.this.robotConfig.bottomBoomTravelIncrement)
                .wait(500)
                .moveBottomToPosition(0.047, CompBot.this.robotConfig.bottomBoomTravelIncrement)
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
                .moveBottomToPosition(0.200, 1)
                .wait(500)
                .moveMiddleToPosition(0.689,1)
                .wait(500)
                .moveMiddleToPosition(0.527,1)
                .wait(500)
                .moveBottomToPosition(0.075, CompBot.this.robotConfig.bottomBoomTravelIncrement)
                .wait(500)
                .moveBottomToPosition(0.047, CompBot.this.robotConfig.bottomBoomTravelIncrement)
                .moveClawToPosition(0.828, 1)
                .rotateClawToPosition(0.307, 1)
                .wait(0);
    }

    private void _moveArm_fromPixelPlaceHigh_toPixelPlace () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(this.robotConfig.pixelPlace_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.pixelPlace_midBoom,1)
                .moveClawToPosition(this.robotConfig.pixelPlace_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.pixelPlace_clawRotator, 1)
                .wait(0);
    }

    private void _moveArm_fromTravel_toPixelReady() {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(0.180, 1)
                .wait(500)
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

    /**
     *
     */
    public void _moveArm_fromPixelReady_doPickPixels () {



            this.arm.wait(0, new CommandCallbackAdapter () {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            CompBot.this.pickingPixel = true;
                        }
                    })
                    .closeLeftClaw()
                    .closeRightClaw()
                    .wait(350)
                    .moveBottomToPosition(0.150)
                    .wait(500)
                    .openLeftClaw()
                    .openRightClaw()
                    .wait(250)
                    .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                    .wait(500, new CommandCallbackAdapter () {
                        public void onSuccess(CommandSuccessEvent successEvent) {
                            CompBot.this.pickingPixel = false;
                        }
                    })
            ;
    }

    public void moveArm_toHangReady () {
        this.arm.rotateClawToPosition(0.307, 1)
                .moveBottomToPosition(this.robotConfig.hangReady_bottomBoom, 1)
                .moveMiddleToPosition(this.robotConfig.hangReady_midBoom, 1)
                .moveClawToPosition(this.robotConfig.hangReady_clawBoom, 1)
                .rotateClawToPosition(this.robotConfig.hangReady_clawRotator, 1)
                .wait(0);
    }

    public void moveArm_toHang () {
        this.arm.moveBottomToPosition(this.robotConfig.hang_bottomBoomPos1, 1)
                .wait(500)
                .moveMiddleToPosition(this.robotConfig.hang_midBoom, 1)
                .moveBottomToPosition(this.robotConfig.hang_bottomBoomPos2, 1)
                .wait(0);
    }


    public void moveArm_fromRest_toPixelReady () {
        this.armPosition = ArmPosition.PIXEL_READY;

        this.arm.moveBottomToPosition(0.200, 1)
                .rotateClawToPosition(this.robotConfig.pixelReady_clawRotator, 1)
                .wait(500)
                .moveMiddleToPosition(this.robotConfig.pixelReady_midBoom, 1)
                .moveClawToPosition(this.robotConfig.pixelReady_clawBoom, 1)
                .wait(500)
                .moveBottomToPosition(this.robotConfig.pixelReady_bottomBoom, 1)
                .wait(0);
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
