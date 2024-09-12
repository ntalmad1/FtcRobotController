package org.firstinspires.ftc.teamcode.library.event.ping;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.EventType;

/**
 *
 */
public class PingEvent extends Event<PingHandler> {

    /**
     */
    public final static EventType<PingHandler> TYPE = new EventType<PingHandler>();

    private double degrees;

    private double distance;

    private DistanceUnit units;


    /**
     *
     * @param degrees
     * @param distance
     * @param units
     */
    public PingEvent(double degrees, double distance, DistanceUnit units) {

        this.degrees = degrees;
        this.distance = distance;
        this.units = units;

    }

    /**
     *
     * @return
     */
    @Override
    public EventType<PingHandler> getType() {
        return TYPE;
    }

    /**
     *
     * @return
     */
    public double getDegrees () {
        return this.degrees;
    }

    /**
     *
     * @return
     */
    public double getDistance () {
       return this.distance;
    }

    /**
     *
     * @return
     */
    public DistanceUnit getUnits () {
        return this.units;
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle(PingHandler handler) {
        handler.onPing(this);
    }
}
