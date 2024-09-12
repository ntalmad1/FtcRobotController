package org.firstinspires.ftc.teamcode.library.event;

/**
 *
 * @param <H>
 */
public abstract class Event<H extends EventHandler> {

    /**
     *
     * @return
     */
    public abstract EventType<H> getType ();

    /**
     *
     * @param handler
     */
    public abstract void handle (H handler);
}
