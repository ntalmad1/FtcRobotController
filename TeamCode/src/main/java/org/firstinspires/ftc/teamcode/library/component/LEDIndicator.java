package org.firstinspires.ftc.teamcode.library.component;

import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.library.IsaacBot;

/**
 *
 */
public class LEDIndicator {

    /**
     *
     */
    public enum Color {

        /**
         */
        AMBER,

        /**
         */
        GREEN,

        /**
         */
        RED,

        /**
         */
        OFF
    }

    /**
     */
    private final DigitalChannel greenState;

    /**
     */
    private final DigitalChannel redState;

    /**
     * Constructor
     *
     * @param ledIndicatorName In the robot config the LED takes up two channels e.g.:
     *      "ledOneGreen" and "ledOneRed"
     */
    public LEDIndicator (String ledIndicatorName) {
        this.greenState = IsaacBot.getInstance().hardwareMap.get(DigitalChannel.class, ledIndicatorName + "Green");
        this.redState = IsaacBot.getInstance().hardwareMap.get(DigitalChannel.class, ledIndicatorName + "Red");

        this.greenState.setMode(DigitalChannel.Mode.OUTPUT);
        this.redState.setMode(DigitalChannel.Mode.OUTPUT);
    }

    /**
     *
     * @param color RED, GREEN, or AMBER
     */
    public void setColor (Color color) {
        switch (color) {
            case AMBER:
                this.greenState.setState(false);
                this.redState.setState(false);
                break;
            case GREEN:
                this.greenState.setState(true);
                this.redState.setState(false);
                break;
            case RED:
                this.greenState.setState(false);
                this.redState.setState(true);
                break;
            case OFF:
                this.greenState.setState(true);
                this.redState.setState(true);
                break;
        }
    }

    /**
     *
     */
    public void amber () {
        this.setColor(Color.AMBER);
    }

    /**
     *
     */
    public void green () {
        this.setColor(Color.GREEN);
    }

    /**
     *
     */
    public void red () {
        this.setColor(Color.RED);
    }

    /**
     *
     */
    public void off () {
        this.setColor(Color.OFF);
    }
}
