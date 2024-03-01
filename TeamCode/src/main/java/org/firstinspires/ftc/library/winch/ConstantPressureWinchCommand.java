package org.firstinspires.ftc.library.winch;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.library.component.command.Command;

/**
 *
 */
public class ConstantPressureWinchCommand extends Command {

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
    public ConstantPressureWinchCommand (Winch winch, TouchSensor sensor, double power, int increment) {
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
            this.winch.stop();
        } else {
            this.winch.move(this.power, this.increment);
        }
    }
}
