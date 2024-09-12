package org.firstinspires.ftc.teamcode.library.event.gp2_x_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_X_PressEvent extends Event<Gp2_X_PressHandler> {

    /**
     */
    public final static EventType<Gp2_X_PressHandler> TYPE = new EventType<Gp2_X_PressHandler>();

    /**
     *
     */
    public Gp2_X_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_X_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_X_PressHandler handler) {
        handler.onGp2_X_Press(this);
    }
}
