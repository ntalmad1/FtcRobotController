package org.firstinspires.ftc.teamcode.library.event.gp1_a_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_A_PressEvent extends Event<Gp1_A_PressHandler> {

    /**
     */
    public final static EventType<Gp1_A_PressHandler> TYPE = new EventType<Gp1_A_PressHandler>();

    /**
     *
     */
    public Gp1_A_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_A_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_A_PressHandler handler) {
        handler.onGp1_A_Press(this);
    }
}