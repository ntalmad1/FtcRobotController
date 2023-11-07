package org.firstinspires.ftc.library.component.command;

import org.firstinspires.ftc.library.component.Component;

import java.util.ArrayList;
import java.util.List;

public class CommandQueue {

    private Component component;

    private List<ICommand> queue;

    private boolean debug = false;


    public CommandQueue (Component component) {
        this.component = component;

        this.queue = new ArrayList<ICommand>();
    }

    /**
     *
     * @param command
     */
    public void add (ICommand command) {
        this.queue.add(command);
    }

    /**
     *
     */
    public void clear () {
        this.queue.clear();
    }

    /**
     *
     */
    public void run ()
    {
        List<ICommand> completedCommands = new ArrayList<ICommand>();
        int runningCommandCount = 0;

        if (debug) this.component.telemetry.addData("Command queue size: ", "%2d", this.queue.size());

        for (ICommand command : this.queue)
        {
            if (debug) this.component.telemetry.addLine("Running command: " + command.getClass().toString());

            if (command.isBlocker() && runningCommandCount > 0) {
                if (debug) this.component.telemetry.addLine("Breaking for loop on blocking command");
                break;
            }

            if (!command.isInitialized()) {
                command.init();
            }
            this.component.runCommand(command);

            if (command.isCompleted()) {
                completedCommands.add(command);
            }
            else {
                runningCommandCount++;
            }

            if (!command.isCompleted() && command.isSynchronous()) {
                if (debug) this.component.telemetry.addLine("Breaking for loop on synchronous command");
                break;
            }
        }

        for (ICommand command : completedCommands) {
            this.queue.remove(command);
        }

        if (debug) this.component.telemetry.update();
    }

    /**
     *
     * @param debug
     */
    public void setDebug (boolean debug) {
        this.debug = debug;
    }

    /**
     *
     * @return
     */
    public int size () {
        return this.queue.size();
    }
}
