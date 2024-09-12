package org.firstinspires.ftc.teamcode.library.component.event.g2_x_press;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp1_X_PressEvent extends Event<Gp1_X_PressHandler> {

    /**
     */
    public final static EventType<Gp1_X_PressHandler> TYPE = new EventType<Gp1_X_PressHandler>();

    /**
     *
     */
    public Gp1_X_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_X_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_X_PressHandler handler) {
        handler.onGp1_X_Press(this);
    }
}
