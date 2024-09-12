package org.firstinspires.ftc.teamcode.library.claw.events.leftpixelping;

import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.EventType;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;

/**
 *
 */
public class LeftPixelPingEvent extends Event<LeftPixelPingHandler> {

    /**
     *
     */
    public static EventType<LeftPixelPingHandler> TYPE = new EventType<LeftPixelPingHandler>();

    /**
     *
     */
    private double distance;

    /**
     *
     */
    private PixelCatcher.ArmPosition armPosition;

    /**
     * Constructor
     *
     */
    public LeftPixelPingEvent (double distance, PixelCatcher.ArmPosition armPosition) {
        this.distance = distance;
        this.armPosition = armPosition;
    }

    /**
     *
     * @return
     */
    public PixelCatcher.ArmPosition getArmPosition () {
        return this.armPosition;
    }

    /**
     *
     * @return the ping distance
     */
    public double getDistance () {
        return this.distance;
    }

    @Override
    public EventType<LeftPixelPingHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(LeftPixelPingHandler handler) {
        handler.onLeftPixelPing(this);
    }
}
