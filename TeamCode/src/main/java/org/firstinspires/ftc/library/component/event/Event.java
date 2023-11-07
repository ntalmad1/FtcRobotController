package org.firstinspires.ftc.library.component.event;

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
