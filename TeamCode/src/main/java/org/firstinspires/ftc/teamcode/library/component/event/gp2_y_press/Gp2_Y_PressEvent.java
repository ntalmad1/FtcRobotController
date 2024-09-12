package org.firstinspires.ftc.teamcode.library.component.event.gp2_y_press;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Y_PressEvent extends Event<Gp2_Y_PressHandler> {

    /**
     */
    public final static EventType<Gp2_Y_PressHandler> TYPE = new EventType<Gp2_Y_PressHandler>();

    /**
     *
     */
    public Gp2_Y_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Y_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Y_PressHandler handler) {
        handler.onGp2_Y_Press(this);
    }
}
