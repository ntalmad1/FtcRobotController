package org.firstinspires.ftc.teamcode.library.pixelcatcher.events.leftarmclose;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class PixelCatcherLeftArmCloseEvent extends Event<PixelCatcherLeftArmCloseHandler> {

    /**
     *
     */
    public static EventType<PixelCatcherLeftArmCloseHandler> TYPE = new EventType<PixelCatcherLeftArmCloseHandler>();

    /**
     * Constructor
     *
     */
    public PixelCatcherLeftArmCloseEvent () {

    }

    @Override
    public EventType<PixelCatcherLeftArmCloseHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(PixelCatcherLeftArmCloseHandler handler) {
        handler.onLeftArmClose(this);
    }
}
