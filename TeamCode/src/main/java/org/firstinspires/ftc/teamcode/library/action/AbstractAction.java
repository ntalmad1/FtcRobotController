package org.firstinspires.ftc.teamcode.library.action;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
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

    /**
     *
     */
    public static final boolean STOP = false;

    /**
     *
     */
    public static final boolean CONTIUE = true;

    /**
     */
    protected boolean blocking = false;

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
     */
    private TelemetryPacket telemetryPacket;

    /**
     */
    private boolean useEvents = false;

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
    public TelemetryPacket getTelemetryPacket() {
        return this.telemetryPacket;
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
        this.markAsCompleted(this.useEvents);
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

    public boolean isBlocking () {
        return this.blocking;
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

    /**
     *
     * @param tp
     * @return
     */
    public boolean run(TelemetryPacket tp) {
        this.telemetryPacket = tp;

        if (!this.initialized) {
            this.init();
            this.setInitialized(true);
        }

        boolean result = this.run();

        if (result == STOP) {
            this.markAsCompleted();
        }

        return result;
    }

    /**
     *
     * @return
     */
    public abstract boolean run();

    /**
     *
     * @param blocking
     */
    protected void setBlocking(boolean blocking) {
        this.blocking = blocking;
    }
}
