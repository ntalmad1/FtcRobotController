package org.firstinspires.ftc.library.component.command;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.component.event.Event;
import org.firstinspires.ftc.library.component.event.HandlerManager;
import org.firstinspires.ftc.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.library.component.event.command_callback.CommandAfterEvent;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackEvent;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;

/**
 *
 */
public abstract class AbstractCommand implements ICommand {

    /**
     */
    protected boolean completed;

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
        this.completed = true;
        this.fireEvent(new CommandSuccessEvent(this));
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
