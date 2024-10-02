package org.firstinspires.ftc.teamcode.library.event.gp2_left_trigger_up;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp2_Left_Trigger_UpEvent extends Event<Gp2_Left_Trigger_UpHandler> {

    /**
     */
    public final static EventType<Gp2_Left_Trigger_UpHandler> TYPE = new EventType<Gp2_Left_Trigger_UpHandler>();

    /**
     *
     */
    public Gp2_Left_Trigger_UpEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Left_Trigger_UpHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Left_Trigger_UpHandler handler) {
        handler.onGp2_Left_Trigger_Up(this);
    }
}