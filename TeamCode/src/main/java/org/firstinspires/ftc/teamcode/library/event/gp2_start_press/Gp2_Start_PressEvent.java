package org.firstinspires.ftc.teamcode.library.event.gp2_start_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Start_PressEvent extends Event<Gp2_Start_PressHandler> {

    /**
     */
    public final static EventType<Gp2_Start_PressHandler> TYPE = new EventType<Gp2_Start_PressHandler>();

    /**
     *
     */
    public Gp2_Start_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Start_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Start_PressHandler handler) {
        handler.onGp2_Start_Press(this);
    }
}