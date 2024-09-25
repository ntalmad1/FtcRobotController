package org.firstinspires.ftc.teamcode.archive.library.claw.events.rightpincherclose;


import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class ClawRightPincherCloseEvent extends Event<ClawRightPincherCloseHandler> {

    /**
     *
     */
    public static EventType<ClawRightPincherCloseHandler> TYPE = new EventType<ClawRightPincherCloseHandler>();

    /**
     * Constructor
     *
     */
    public ClawRightPincherCloseEvent() {

    }

    @Override
    public EventType<ClawRightPincherCloseHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(ClawRightPincherCloseHandler handler) {
        handler.onRightPincherClose(this);
    }
}
