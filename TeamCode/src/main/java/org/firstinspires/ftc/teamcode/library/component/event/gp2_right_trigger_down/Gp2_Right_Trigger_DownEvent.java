package org.firstinspires.ftc.teamcode.library.component.event.gp2_right_trigger_down;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Right_Trigger_DownEvent extends Event<Gp2_Right_Trigger_DownHandler> {

    /**
     */
    public final static EventType<Gp2_Right_Trigger_DownHandler> TYPE = new EventType<Gp2_Right_Trigger_DownHandler>();

    /**
     *
     */
    public Gp2_Right_Trigger_DownEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Right_Trigger_DownHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Right_Trigger_DownHandler handler) {
        handler.onGp2_Right_Trigger_Down(this);
    }
}