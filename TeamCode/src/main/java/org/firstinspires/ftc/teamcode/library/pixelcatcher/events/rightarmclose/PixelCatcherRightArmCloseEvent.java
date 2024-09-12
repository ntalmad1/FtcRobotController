package org.firstinspires.ftc.teamcode.library.pixelcatcher.events.rightarmclose;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class PixelCatcherRightArmCloseEvent extends Event<PixelCatcherRightArmCloseHandler> {

    /**
     *
     */
    public static EventType<PixelCatcherRightArmCloseHandler> TYPE = new EventType<PixelCatcherRightArmCloseHandler>();

    /**
     * Constructor
     *
     */
    public PixelCatcherRightArmCloseEvent() {

    }

    @Override
    public EventType<PixelCatcherRightArmCloseHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(PixelCatcherRightArmCloseHandler handler) {
        handler.onRightArmClose(this);
    }
}
