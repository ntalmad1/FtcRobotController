package org.firstinspires.ftc.teamcode.library.event.action_callback;

import com.acmerobotics.roadrunner.Action;

/**
 *
 */
public class ActionAfterEvent extends ActionCallbackEvent {

    /**
     *
     * @param action
     */
    public ActionAfterEvent(Action action) {
        super(Type.AFTER, action);
    }

    /**
     *
     * @param handler
     */
    @Override
    public void handle( ActionCallbackHandler handler) {
        handler.onAfter(this);
    }
}
