package org.firstinspires.ftc.teamcode.library.action;

import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.HandlerManager;
import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.action_callback.ActionAfterEvent;
import org.firstinspires.ftc.teamcode.library.event.action_callback.ActionCallbackEvent;
import org.firstinspires.ftc.teamcode.library.event.action_callback.ActionCallbackHandler;
import org.firstinspires.ftc.teamcode.library.event.action_callback.ActionFailureEvent;
import org.firstinspires.ftc.teamcode.library.event.action_callback.ActionSuccessEvent;

/**
 *
 */
public abstract class AbstractAction implements Action {

    protected final boolean STOP = false;

    protected final boolean CONTIUE = true;

    /**
     */
    protected boolean completed = false;

    /**
     */
    private boolean initialized = false;

    /**
     */
    private HandlerManager handlerManager = new HandlerManager();

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addCallbackHandler (ActionCallbackHandler handler) {
        return this.handlerManager.addHandler(ActionCallbackEvent.TYPE, handler);
    }

    /**
     *
     * @param event
     */
    public void fireEvent (Event event) {
        this.handlerManager.fireEvent(event);
    }

    /**
     *
     * @return
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     *
     */
    public void markAsCompleted () {
        this.markAsCompleted(true);
    }

    /**
     *
     */
    public void markAsCompleted (boolean fireEvents) {
        this.completed = true;
        this.fireEvent(new ActionSuccessEvent(this));
        this.fireEvent(new ActionAfterEvent(this));
    }

    /**
     *
     * @param e
     */
    public void markAsFailed (Exception e) {
        this.completed = true;
        this.fireEvent(new ActionFailureEvent(this, e));
        this.fireEvent(new ActionAfterEvent(this));
    }

    /**
     *
     * @return
     */
    public boolean isInitialized () {
        return this.initialized;
    }

    /**
     *
     * @param initialized
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    /**
     *
     */
    public void init () {
        this.setInitialized(true);
    };
}
