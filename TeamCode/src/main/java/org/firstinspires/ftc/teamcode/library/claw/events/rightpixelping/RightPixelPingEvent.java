package org.firstinspires.ftc.teamcode.library.claw.events.rightpixelping;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;
import org.firstinspires.ftc.teamcode.library.pixelcatcher.PixelCatcher;

/**
 *
 */
public class RightPixelPingEvent extends Event<RightPixelPingHandler> {

    /**
     *
     */
    public static EventType<RightPixelPingHandler> TYPE = new EventType<RightPixelPingHandler>();

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
    public RightPixelPingEvent(double distance, PixelCatcher.ArmPosition armPosition) {
        this.distance = distance;
        this.armPosition = armPosition;
    }

    /**
     *
     * @return the ping distance
     */
    public double getDistance () {
        return this.distance;
    }

    /**
     *
     * @return
     */
    public PixelCatcher.ArmPosition getArmPosition () {
        return this.armPosition;
    }

    @Override
    public EventType<RightPixelPingHandler> getType() {
        return TYPE;
    }

    @Override
    public void handle(RightPixelPingHandler handler) {
        handler.onRightPixelPing(this);
    }
}
