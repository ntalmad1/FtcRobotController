package org.firstinspires.ftc.teamcode.library.component.event.gp2_a_press;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_A_PressEvent extends Event<Gp2_A_PressHandler> {

    /**
     */
    public final static EventType<Gp2_A_PressHandler> TYPE = new EventType<Gp2_A_PressHandler>();

    /**
     *
     */
    public Gp2_A_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_A_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_A_PressHandler handler) {
        handler.onGp2_A_Press(this);
    }
}