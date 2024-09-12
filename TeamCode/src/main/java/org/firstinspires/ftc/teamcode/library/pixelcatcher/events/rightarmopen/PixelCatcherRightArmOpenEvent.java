package org.firstinspires.ftc.teamcode.library.pixelcatcher.events.rightarmopen;


import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

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
