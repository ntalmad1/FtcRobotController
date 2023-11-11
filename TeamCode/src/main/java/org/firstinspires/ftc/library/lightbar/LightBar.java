package org.firstinspires.ftc.library.lightbar;

import org.firstinspires.ftc.library.LEDIndicator;
import org.firstinspires.ftc.library.claw.events.leftpincherclose.ClawLeftPincherCloseEvent;
import org.firstinspires.ftc.library.claw.events.leftpincherclose.ClawLeftPincherCloseHandler;
import org.firstinspires.ftc.library.claw.events.leftpincheropen.ClawLeftPincherOpenEvent;
import org.firstinspires.ftc.library.claw.events.leftpincheropen.ClawLeftPincherOpenHandler;
import org.firstinspires.ftc.library.claw.events.leftpixelping.LeftPixelPingEvent;
import org.firstinspires.ftc.library.claw.events.leftpixelping.LeftPixelPingHandler;
import org.firstinspires.ftc.library.claw.events.rightpincherclose.ClawRightPincherCloseEvent;
import org.firstinspires.ftc.library.claw.events.rightpincherclose.ClawRightPincherCloseHandler;
import org.firstinspires.ftc.library.claw.events.rightpincheropen.ClawRightPincherOpenEvent;
import org.firstinspires.ftc.library.claw.events.rightpincheropen.ClawRightPincherOpenHandler;
import org.firstinspires.ftc.library.claw.events.rightpixelping.RightPixelPingEvent;
import org.firstinspires.ftc.library.claw.events.rightpixelping.RightPixelPingHandler;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmopen.PixelCatcherLeftArmOpenEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmopen.PixelCatcherLeftArmOpenHandler;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmclose.PixelCatcherLeftArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.leftarmclose.PixelCatcherLeftArmCloseHandler;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmclose.PixelCatcherRightArmCloseHandler;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmclose.PixelCatcherRightArmCloseEvent;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmopen.PixelCatcherRightArmOpenHandler;
import org.firstinspires.ftc.library.pixelcatcher.events.rightarmopen.PixelCatcherRightArmOpenEvent;

/**
 *
 */
public class LightBar extends Component {

    /**
     *
     */
    protected LightBarConfig config;

    /**
     *
     */
    private LEDIndicator leftPixelCatcherArmLED;
    private LEDIndicator rightPixelCatcherArmLED;

    /**
     *
     */
    private LEDIndicator leftPixelLED;
    private LEDIndicator rightPixelLED;

    /**
     *
     */
    private LEDIndicator leftClawPincherLED;
    private LEDIndicator rightClawPincherLED;

    /**
     *
     */
    private LEDIndicator driveTrainModeLED;

    /**
     * Constructor
     *
     * @param config the configuration values for the light bar
     */
    public LightBar (LightBarConfig config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.leftPixelCatcherArmLED = new LEDIndicator(this.config.leftPixelCatcherArmLED);
        this.rightPixelCatcherArmLED = new LEDIndicator(this.config.rightPixelCatcherArmLED);

        this.leftPixelLED = new LEDIndicator(this.config.leftPixelLED);
        this.rightPixelLED = new LEDIndicator(this.config.rightPixelLED);

        this.leftClawPincherLED = new LEDIndicator(this.config.leftClawPincherLED);
        this.rightClawPincherLED = new LEDIndicator(this.config.rightClawPincherLED);

        this.driveTrainModeLED = new LEDIndicator(this.config.driveTrainModeLED);

        this.addHandler(PixelCatcherLeftArmOpenEvent.TYPE, new PixelCatcherLeftArmOpenHandler(){
            public void onLeftArmOpen (PixelCatcherLeftArmOpenEvent event) {
                LightBar.this.leftPixelCatcherArmLED.red();
            }
        });

        this.addHandler(PixelCatcherLeftArmCloseEvent.TYPE, new PixelCatcherLeftArmCloseHandler(){
            public void onLeftArmClose (PixelCatcherLeftArmCloseEvent event) {
                LightBar.this.leftPixelCatcherArmLED.green();
            }
        });

        this.addHandler(PixelCatcherRightArmOpenEvent.TYPE, new PixelCatcherRightArmOpenHandler(){
            public void onRightArmOpen (PixelCatcherRightArmOpenEvent event) {
                LightBar.this.rightPixelCatcherArmLED.red();
            }
        });

        this.addHandler(PixelCatcherRightArmCloseEvent.TYPE, new PixelCatcherRightArmCloseHandler(){
            public void onRightArmClose (PixelCatcherRightArmCloseEvent event) {
                LightBar.this.rightPixelCatcherArmLED.green();
            }
        });

        this.addHandler(LeftPixelPingEvent.TYPE, new LeftPixelPingHandler(){
            public void onLeftPixelPing (LeftPixelPingEvent event) {
                if (event.getDistance() < LightBar.this.config.leftPixelCapturedTolerance) {
                    LightBar.this.leftPixelLED.green();
                }
                else if (event.getDistance() < LightBar.this.config.leftPixelInRangeTolerance) {
                    LightBar.this.leftPixelLED.amber();
                }
                else {
                    LightBar.this.leftPixelLED.red();
                }
            }
        });

        this.addHandler(RightPixelPingEvent.TYPE, new RightPixelPingHandler(){
            public void onRightPixelPing (RightPixelPingEvent event) {
                if (event.getDistance() < LightBar.this.config.rightPixelCapturedTolerance) {
                    LightBar.this.rightPixelLED.green();
                }
                else if (event.getDistance() < LightBar.this.config.rightPixelInRangeTolerance) {
                    LightBar.this.rightPixelLED.amber();
                }
                else {
                    LightBar.this.rightPixelLED.red();
                }
            }
        });

        this.addHandler(ClawLeftPincherOpenEvent.TYPE, new ClawLeftPincherOpenHandler(){
            public void onLeftPincherOpen (ClawLeftPincherOpenEvent event) {
                LightBar.this.leftClawPincherLED.red();
            }
        });

        this.addHandler(ClawLeftPincherCloseEvent.TYPE, new ClawLeftPincherCloseHandler(){
            public void onLeftPincherClose (ClawLeftPincherCloseEvent event) {
                LightBar.this.leftClawPincherLED.green();
            }
        });

        this.addHandler(ClawRightPincherOpenEvent.TYPE, new ClawRightPincherOpenHandler(){
            public void onRightPincherOpen (ClawRightPincherOpenEvent event) {
                LightBar.this.rightClawPincherLED.red();
            }
        });

        this.addHandler(ClawRightPincherCloseEvent.TYPE, new ClawRightPincherCloseHandler(){
            public void onRightPincherClose (ClawRightPincherCloseEvent event) {
                LightBar.this.rightClawPincherLED.green();
            }
        });

        this.driveTrainModeLED.green();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
