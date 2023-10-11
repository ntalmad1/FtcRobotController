package org.firstinspires.ftc.teamcode.library.component.command;

import org.firstinspires.ftc.teamcode.library.component.event.Event;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerManager;
import org.firstinspires.ftc.teamcode.library.component.event.HandlerRegistration;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackEvent;
import org.firstinspires.ftc.teamcode.library.component.event.command_callback.CommandCallbackHandler;

/**
 *
 */
public class AbstractCommand implements Command {

    /**
     */
    protected boolean completed;

    /**
     */
    private boolean initialized = false;

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
    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     *
     */
    public void markAsCompleted () {
        this.completed = true;
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
     * @param initialized
     */
    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    /**
     *
     */
    public void init () {};
}
