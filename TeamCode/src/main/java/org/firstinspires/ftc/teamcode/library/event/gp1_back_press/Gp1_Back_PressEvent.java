package org.firstinspires.ftc.teamcode.library.event.gp1_back_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Back_PressEvent extends Event<Gp1_Back_PressHandler> {

    /**
     */
    public final static EventType<Gp1_Back_PressHandler> TYPE = new EventType<Gp1_Back_PressHandler>();

    /**
     *
     */
    public Gp1_Back_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Back_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Back_PressHandler handler) {
        handler.onGp1_Back_Press(this);
    }
}