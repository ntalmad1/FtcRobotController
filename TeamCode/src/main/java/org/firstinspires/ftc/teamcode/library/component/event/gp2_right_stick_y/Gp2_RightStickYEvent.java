package org.firstinspires.ftc.teamcode.library.component.event.gp2_right_stick_y;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_RightStickYEvent extends Event<Gp2_RightStickYHandler> {

    public final static EventType<Gp2_RightStickYHandler> TYPE = new EventType<Gp2_RightStickYHandler>();

    /**
     *
     */
    private double position;

    /**
     *
     * @param pos
     */
    public Gp2_RightStickYEvent(double pos) {
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
    public EventType<Gp2_RightStickYHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(Gp2_RightStickYHandler handler) {
        handler.onGp2_RightStickY(this);
    }
}
