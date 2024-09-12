package org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_y;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_LeftStickYEvent extends Event<Gp2_LeftStickYHandler> {

    public final static EventType<Gp2_LeftStickYHandler> TYPE = new EventType<Gp2_LeftStickYHandler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp2_LeftStickYEvent(double pos) {
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
    public EventType<Gp2_LeftStickYHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp2_LeftStickYHandler handler) {
        handler.onGp2_LeftStickY(this);
    }
}
