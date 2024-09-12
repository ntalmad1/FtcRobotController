package org.firstinspires.ftc.teamcode.library.command;

import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackHandler;

/**
 *
 */
public interface ICommand {

    /**
     *
     * @param callback
     * @return
     */
    HandlerRegistration addCallbackHandler (CommandCallbackHandler callback);

    /**
     *
     * @return
     */
    boolean isBlocker ();

    /**
     *
     * @return
     */
    boolean isCompleted ();

    /**
     *
     * @return
     */
    boolean isInitialized ();

    /**
     *
     * @return
     */
    boolean isRepeating ();

    /**
     *
     * @return
     */
    boolean isSynchronous ();

    /**
     *
     */
    void init ();

    /**
     *
     */
    void run ();

    /**
     *
     */
    void markAsCompleted();

    /**
     *
     * @param fireEvents
     */
    void markAsCompleted(boolean fireEvents);
}
