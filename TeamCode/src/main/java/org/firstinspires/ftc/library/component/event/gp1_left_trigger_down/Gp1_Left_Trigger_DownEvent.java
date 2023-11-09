package org.firstinspires.ftc.library.component.event.gp1_left_trigger_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp1_Left_Trigger_DownEvent extends Event<Gp1_Left_Trigger_DownHandler> {

    /**
     */
    public final static EventType<Gp1_Left_Trigger_DownHandler> TYPE = new EventType<Gp1_Left_Trigger_DownHandler>();

    /**
     *
     */
    public Gp1_Left_Trigger_DownEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Left_Trigger_DownHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Left_Trigger_DownHandler handler) {
        handler.onGp1_Left_Trigger_Down(this);
    }
}