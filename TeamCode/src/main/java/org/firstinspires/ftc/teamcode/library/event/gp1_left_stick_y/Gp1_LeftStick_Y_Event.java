package org.firstinspires.ftc.teamcode.library.event.gp1_left_stick_y;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_LeftStick_Y_Event extends Event<Gp1_LeftStick_Y_Handler> {

    public final static EventType<Gp1_LeftStick_Y_Handler> TYPE = new EventType<Gp1_LeftStick_Y_Handler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp1_LeftStick_Y_Event(double pos) {
        this.position = pos;
    }

    /**
     *
     * @return
     */
    public double getPosition () {
        return this.position;
    }

    @Override
    public EventType<Gp1_LeftStick_Y_Handler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp1_LeftStick_Y_Handler handler) {
        handler.onGp1_LeftStick_Y(this);
    }
}
