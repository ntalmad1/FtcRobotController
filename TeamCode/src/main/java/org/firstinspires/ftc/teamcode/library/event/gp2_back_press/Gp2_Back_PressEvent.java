package org.firstinspires.ftc.teamcode.library.event.gp2_back_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Back_PressEvent extends Event<Gp2_Back_PressHandler> {

    /**
     */
    public final static EventType<Gp2_Back_PressHandler> TYPE = new EventType<Gp2_Back_PressHandler>();

    /**
     *
     */
    public Gp2_Back_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Back_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Back_PressHandler handler) {
        handler.onGp2_Back_Press(this);
    }
}