package org.firstinspires.ftc.teamcode.library.event.gp1_right_trigger_up;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class Gp1_Right_Trigger_UpEvent extends Event<Gp1_Right_Trigger_UpHandler> {

    /**
     */
    public final static EventType<Gp1_Right_Trigger_UpHandler> TYPE = new EventType<Gp1_Right_Trigger_UpHandler>();

    /**
     *
     */
    public Gp1_Right_Trigger_UpEvent() {

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp1_Right_Trigger_UpHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp1_Right_Trigger_UpHandler handler) {
        handler.onGp1_Right_Trigger_Up(this);
    }
}