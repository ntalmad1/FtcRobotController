package org.firstinspires.ftc.teamcode.library.claw.events.leftpincheropen;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;


/**
 *
 */
public class ClawLeftPincherOpenEvent extends Event<ClawLeftPincherOpenHandler> {

    /**
     *
     */
    public static EventType<ClawLeftPincherOpenHandler> TYPE = new EventType<ClawLeftPincherOpenHandler>();

    /**
     * Constructor
     *
     */
    public ClawLeftPincherOpenEvent() {

    }

    @Override
    public EventType<ClawLeftPincherOpenHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(ClawLeftPincherOpenHandler handler) {
        handler.onLeftPincherOpen(this);
    }
}
