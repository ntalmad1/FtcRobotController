package org.firstinspires.ftc.teamcode.library.component.event.gp2_right_trigger;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class Gp2_Right_Trigger_Event extends Event<Gp2_Right_Trigger_Handler> {

    /**
     */
    public final static EventType<Gp2_Right_Trigger_Handler> TYPE = new EventType<Gp2_Right_Trigger_Handler>();

    /**
     */
    private float position;

    /**
     * Constructor
     *
     */
    public Gp2_Right_Trigger_Event(float position) {
        this.position = position;
    }

    /**
     *
     * @return float
     */
    public float getPosition () {
        return this.position;
    }

    /**
     *
     * @return
     */
    @Override
    public EventType<Gp2_Right_Trigger_Handler> getType() {
        return TYPE;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(Gp2_Right_Trigger_Handler handler) {
        handler.onGp2_Right_Trigger(this);
    }
}