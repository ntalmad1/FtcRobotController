package org.firstinspires.ftc.teamcode.competition.base;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.competition.config.ArmCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.Arm;

/**
 *
 */
@TeleOp(name="CompBot", group="Linear OpMode")
@Disabled
public class CompBot extends IsaacBot{

    /**
     */
    public enum ArmPosition {
        HOME,
        PIXEL_READY,
        PIXEL_PICKED,
        PIXEL_TRAVEL,
        PIXEL_PLACE_LOW,
        PIXEL_PLACE_MID,
        PIXEL_PLACE_HIGH,
        TRAVEL_EMPTY
    }

    /**
     */
    protected ArmCompConfig armConfig;

    /**
     */
    protected Servo catcherServo;

    /**
     */
    protected Arm arm;

    /**
     */
    private ArmPosition armPosition = ArmPosition.HOME;

    /**
     * Constructor
     *
     */
    public CompBot() {
        super();

        this.armConfig = new ArmCompConfig(this);

        this.addGp2_A_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.HOME)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
                CompBot.this.moveArm_fromHome_toPixelReady();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_READY) || this.armPosition.equals(ArmPosition.PIXEL_PICKED)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PICKED;
                CompBot.this.moveArm_pickPixels();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_TRAVEL)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_READY;
                CompBot.this.moveArm_fromPixelTravel_toPixelReady();
            }

        });

        this.addGp2_B_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.PIXEL_READY)
             || this.armPosition.equals(ArmPosition.PIXEL_PICKED)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_TRAVEL;
                CompBot.this.moveArm_pixelTravel();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW)
                    || this.armPosition.equals(ArmPosition.PIXEL_PLACE_MID)
                    || this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_TRAVEL;
                CompBot.this.moveArm_fromPixelPlace_toPixelTravel();
            }
        });

        this.addGp2_X_PressHandler(event -> {
            CompBot.this.arm.cancelAllCommands();

            if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_HIGH)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_placePixelLow();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_MID)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_HIGH;
                CompBot.this.moveArm_placePixelHigh();
            }
            else if (this.armPosition.equals(ArmPosition.PIXEL_PLACE_LOW)) {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_MID;
                CompBot.this.moveArm_placePixelMid();
            }
            else {
                CompBot.this.armPosition = ArmPosition.PIXEL_PLACE_LOW;
                CompBot.this.moveArm_placePixelLow();
            }
        });
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.arm = new Arm(armConfig);
        this.arm.init();

        this.catcherServo = this.hardwareMap.get(Servo.class, "catcherServo");
        this.catcherServo.resetDeviceConfigurationForOpMode();
        this.catcherServo.setDirection(Servo.Direction.REVERSE);
        this.catcherServo.setPosition(0);
    }

    /**
     *
     */
    public void go () {
        super.go();

        this.catcherServo.setPosition(0.12);
        this.sleep(250);
        this.catcherServo.setPosition(0);
    }

    /**
     *
     */
    public void run () {
        super.run();
        this.arm.run();
    }

    /**
     *
     */
    public void moveArm_fromHome_toPixelReady () {

            this.arm.moveMiddleDegreesFromCurrentPosition(15)
                    .wait(0)
                    .moveMiddleToDegrees(-90, 1)
                    .moveBottomDegreesFromCurrentPosition(-30)
                    .wait(0)
                    .moveBottomToPosition(0.183, 1)
                    .moveMiddleToPosition(0.793,1)
                    .moveClawToPosition(0.636, 1)
                    .rotateClawToPosition(0.477, 1)
                    .wait(0)
            ;

    }

    /**
     *
     */
    public void moveArm_fromPixelTravel_toPixelReady() {

        this.arm.moveBottomToPosition(0.183, 1)
                .moveMiddleToPosition(0.793,1)
                .moveClawToPosition(0.636, 1)
                .rotateClawToPosition(0.477, 1)
                .wait(0)
        ;
    }

    /**
     *
     */
    public void moveArm_pickPixels () {

            this.arm.closeLeftClaw()
                    .closeRightClaw()
                    .wait(250)
                    .moveBottomToPosition(0.165)
                    .wait(500)
                    .openLeftClaw()
                    .openRightClaw()
                    .wait(250)
                    .moveBottomToPosition(0.183, 1)
                    .wait(0)
            ;
    }

    /**
     *
     */
    public void moveArm_pixelTravel () {

            this.arm.moveMiddleToDegrees(60, 1)
                    .wait(1000)
                    .moveBottomToDegrees(-10, 1)
                    .wait(1000)
                    .moveBottomToPosition(0.568, 1)
                    .moveMiddleToPosition(0.994, 1)
                    .moveClawToPosition(0.562, 1)
                    .wait(0)
            ;

    }

    /**
     *
     */
    public void moveArm_fromPixelPlace_toPixelTravel () {

        this.arm.moveMiddleToPosition(0.994, 1)
                .wait(2000)
                .moveBottomToPosition(0.568, 1)
                .moveClawToPosition(0.562, 1)
                .wait(0)
        ;
    }

    /**
     *
     */
    public void moveArm_placePixelLow() {

        this.arm.moveBottomToPosition(0.406, 1)
                .moveMiddleToPosition(0.318, 1)
                .moveClawToPosition(0.4, 1)
                .rotateClawToPosition(0.486)
                .wait(0);
    }

    /**
     *
     */
    public void moveArm_placePixelMid() {

        this.arm.moveBottomToPosition(0.322, 1)
                .moveMiddleToPosition(0.2938, 1)
                .moveClawToPosition(0.3472, 1)
                .rotateClawToPosition(0.492)
                .wait(0);

    }

    /**
     *
     */
    public void moveArm_placePixelHigh () {

        this.arm.moveBottomToPosition(0.345, 1)
                .moveMiddleToPosition(0.431, 1)
                .moveClawToPosition(0.217, 1)
                .rotateClawToPosition(0.491)
                .wait(0);

    }


}
