package org.firstinspires.ftc.library.component.event.gp2_b_press;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_B_PressEvent extends Event<Gp2_B_PressHandler> {

    /**
     */
    public final static EventType<Gp2_B_PressHandler> TYPE = new EventType<Gp2_B_PressHandler>();

    /**
     *
     */
    public Gp2_B_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_B_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_B_PressHandler handler) {
        handler.onGp2_B_Press(this);
    }
}
