package org.firstinspires.ftc.teamcode.library.claw.events.leftpincherclose;


import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class ClawLeftPincherCloseEvent extends Event<ClawLeftPincherCloseHandler> {

    /**
     *
     */
    public static EventType<ClawLeftPincherCloseHandler> TYPE = new EventType<ClawLeftPincherCloseHandler>();

    /**
     * Constructor
     *
     */
    public ClawLeftPincherCloseEvent () {

    }

    @Override
    public EventType<ClawLeftPincherCloseHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(ClawLeftPincherCloseHandler handler) {
        handler.onLeftPincherClose(this);
    }
}
