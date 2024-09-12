package org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_x;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_RightStickXEvent extends Event<Gp2_RightStickXHandler> {

    public final static EventType<Gp2_RightStickXHandler> TYPE = new EventType<Gp2_RightStickXHandler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp2_RightStickXEvent(double pos) {
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
    public EventType<Gp2_RightStickXHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp2_RightStickXHandler handler) {
        handler.onGp2_RightStickX(this);
    }
}
