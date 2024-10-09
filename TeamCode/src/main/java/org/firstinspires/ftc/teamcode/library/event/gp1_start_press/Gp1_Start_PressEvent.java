package org.firstinspires.ftc.teamcode.library.event.gp1_start_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Start_PressEvent extends Event<Gp1_Start_PressHandler> {

    /**
     */
    public final static EventType<Gp1_Start_PressHandler> TYPE = new EventType<Gp1_Start_PressHandler>();

    /**
     *
     */
    public Gp1_Start_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Start_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Start_PressHandler handler) {
        handler.onGp1_Start_Press(this);
    }
}