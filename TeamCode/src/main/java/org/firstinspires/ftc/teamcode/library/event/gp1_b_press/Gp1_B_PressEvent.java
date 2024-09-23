package org.firstinspires.ftc.teamcode.library.event.gp1_b_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_B_PressEvent extends Event<Gp1_B_PressHandler> {

    /**
     */
    public final static EventType<Gp1_B_PressHandler> TYPE = new EventType<Gp1_B_PressHandler>();

    /**
     *
     */
    public Gp1_B_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_B_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_B_PressHandler handler) {
        handler.onGp1_B_Press(this);
    }
}
