package org.firstinspires.ftc.teamcode.library.winch;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.library.command.AbstractRepeatingCommand;

/**
 *
 */
public class ConstantOutWinchCommand extends AbstractRepeatingCommand {

    /**
     */
    private Winch winch;

    /**
     */
    private TouchSensor sensor;

    /**
     */
    private int increment;

    /**
     */
    private double power;

    /**
     * Constructor
     *
     */
    public ConstantOutWinchCommand(Winch winch, TouchSensor sensor, double power, int increment) {
        super();

        this.winch = winch;
        this.sensor = sensor;
        this.power = power;
        this.increment = increment;
    }


    @Override
    public void init () {
        super.init();

        this.setInitialized(true);
    }


    @Override
    public void run() {

        if (this.sensor.isPressed()) {
            this.winch.move(this.power, this.increment);
        } else {
            this.winch.stop();
        }
    }
}
