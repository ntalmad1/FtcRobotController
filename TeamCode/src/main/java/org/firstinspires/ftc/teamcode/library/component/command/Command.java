package org.firstinspires.ftc.teamcode.library.component.command;

import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;

/**
 *
 */
public interface Command {

    /**
     *
     * @param callback
     * @return
     */
    HandlerRegistration addCallbackHandler (CommandCallback callback);

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
     */
    void init ();
}
