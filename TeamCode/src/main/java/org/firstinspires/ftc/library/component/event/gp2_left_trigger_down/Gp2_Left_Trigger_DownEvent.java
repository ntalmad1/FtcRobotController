package org.firstinspires.ftc.library.component.event.gp2_left_trigger_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Left_Trigger_DownEvent extends Event<Gp2_Left_Trigger_DownHandler> {

    /**
     */
    public final static EventType<Gp2_Left_Trigger_DownHandler> TYPE = new EventType<Gp2_Left_Trigger_DownHandler>();

    /**
     *
     */
    public Gp2_Left_Trigger_DownEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Left_Trigger_DownHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Left_Trigger_DownHandler handler) {
        handler.onGp2_Left_Trigger_Down(this);
    }
}