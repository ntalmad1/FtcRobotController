package org.firstinspires.ftc.library.pixelcatcher.events.rightarmopen;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;

/**
 *
 */
public class PixelCatcherRightArmOpenEvent extends Event<PixelCatcherRightArmOpenHandler> {

    /**
     *
     */
    public static EventType<PixelCatcherRightArmOpenHandler> TYPE = new EventType<PixelCatcherRightArmOpenHandler>();

    /**
     * Constructor
     *
     */
    public PixelCatcherRightArmOpenEvent() {

    }

    @Override
    public EventType<PixelCatcherRightArmOpenHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(PixelCatcherRightArmOpenHandler handler) {
        handler.onRightArmOpen(this);
    }
}
