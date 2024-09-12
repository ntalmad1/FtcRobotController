package org.firstinspires.ftc.teamcode.library.event.g2_y_press;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Y_PressEvent extends Event<Gp1_Y_PressHandler> {

    /**
     */
    public final static EventType<Gp1_Y_PressHandler> TYPE = new EventType<Gp1_Y_PressHandler>();

    /**
     *
     */
    public Gp1_Y_PressEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Y_PressHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Y_PressHandler handler) {
        handler.onGp2_A_Press(this);
    }
}
