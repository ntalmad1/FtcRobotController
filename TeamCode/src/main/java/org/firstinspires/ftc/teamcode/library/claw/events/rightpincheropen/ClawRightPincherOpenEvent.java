package org.firstinspires.ftc.teamcode.library.claw.events.rightpincheropen;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;


/**
 *
 */
public class ClawRightPincherOpenEvent extends Event<ClawRightPincherOpenHandler> {

    /**
     *
     */
    public static EventType<ClawRightPincherOpenHandler> TYPE = new EventType<ClawRightPincherOpenHandler>();

    /**
     * Constructor
     *
     */
    public ClawRightPincherOpenEvent() {

    }

    @Override
    public EventType<ClawRightPincherOpenHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(ClawRightPincherOpenHandler handler) {
        handler.onRightPincherOpen(this);
    }
}
