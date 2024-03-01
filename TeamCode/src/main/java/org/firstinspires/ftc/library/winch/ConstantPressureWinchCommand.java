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
     * Constructor
     *
     */
    public ConstantPressureWinchCommand (Winch winch, TouchSensor sensor, int increment) {
        this.winch = winch;
        this.sensor = sensor;
        this.increment = increment;
    }


    @Override
    public void init () {
        super.init();
    }


    @Override
    public void run() {

        if (this.sensor.isPressed()) {
            this.winch.stop();
        }
        else {

        }

    }
}
