package org.firstinspires.ftc.library.component.command;

import org.firstinspires.ftc.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;

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

    void run ();

    /**
     *
     */
    void markAsCompleted();
}
