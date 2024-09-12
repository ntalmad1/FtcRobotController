package org.firstinspires.ftc.teamcode.library.event.gp2_left_stick_x;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_LeftStickXEvent extends Event<Gp2_LeftStickXHandler> {

    public final static EventType<Gp2_LeftStickXHandler> TYPE = new EventType<Gp2_LeftStickXHandler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp2_LeftStickXEvent (double pos) {
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
    public EventType<Gp2_LeftStickXHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp2_LeftStickXHandler handler) {
        handler.onGp2_LeftStickX(this);
    }
}
