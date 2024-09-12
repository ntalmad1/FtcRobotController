package org.firstinspires.ftc.teamcode.library.command;

import org.firstinspires.ftc.teamcode.library.event.Event;
import org.firstinspires.ftc.teamcode.library.event.HandlerManager;
import org.firstinspires.ftc.teamcode.library.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackEvent;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandFailureEvent;
import org.firstinspires.ftc.teamcode.library.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public abstract class AbstractCommand implements ICommand {

    /**
     */
    protected boolean completed = false;

    /**
     */
    protected boolean blocker = false;

    /**
     */
    private boolean initialized = false;

    /**
     */
    private boolean synchronous = false;

    /**
     */
    private boolean repeating = false;

    /**
     */
    private HandlerManager handlerManager = new HandlerManager();

    /**
     *
     * @param handler
     * @return
     */
    public HandlerRegistration addCallbackHandler (CommandCallbackHandler handler) {
        return this.handlerManager.addHandler(CommandCallbackEvent.TYPE, handler);
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
    public boolean isBlocker () {
        return this.blocker;
    }

    /**
     *
     * @return
     */
    @Override
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
        this.fireEvent(new CommandSuccessEvent(this));
        this.fireEvent(new CommandAfterEvent(this));
    }

    /**
     *
     * @param e
     */
    public void markAsFailed (Exception e) {
        this.completed = true;
        this.fireEvent(new CommandFailureEvent(this, e));
        this.fireEvent(new CommandAfterEvent(this));
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isInitialized () {
        return this.initialized;
    }

    /**
     *
     * @return
     */
    public boolean isRepeating () {
        return this.repeating;
    }

    /**
     *
     * @return
     */
    public boolean isSynchronous () {
        return this.synchronous;
    }

    /**
     *
     * @param isBlocker
     */
    public void setBlocking (boolean isBlocker) {
        this.blocker = isBlocker;
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
     * @param repeating
     */
    public void setRepeating (boolean repeating) {
        this.repeating = repeating;
    }

    /**
     *
     * @param synchronous
     */
    public void setSynchronous (boolean synchronous) {
       this.synchronous = synchronous;
    }

    /**
     *
     */
    public void init () {
        this.setInitialized(true);
    };

    /**
     *
     */
    public abstract void run ();
}
