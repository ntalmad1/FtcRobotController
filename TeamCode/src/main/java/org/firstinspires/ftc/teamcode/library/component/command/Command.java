package org.firstinspires.ftc.teamcode.library.component.command;

import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackHandler;

/**
 *
 */
public interface Command {

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
    boolean isCompleted();

    /**
     *
     * @return
     */
    boolean isInitialized();

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
}
