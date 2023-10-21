package org.firstinspires.ftc.teamcode.library.component.command;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.Component;

import java.util.ArrayList;
import java.util.List;

public class CommandQueue {

    private Component component;

    private List<Command> queue;


    public CommandQueue (Component component) {
        this.component = component;

        this.queue = new ArrayList<Command>();
    }

    /**
     *
     * @param command
     */
    public void add (Command command) {
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
        List<Command> completedCommands = new ArrayList<Command>();
        int runningCommandCount = 0;

        for (Command command : this.queue)
        {
            if (command.isBlocker() && runningCommandCount > 0) {
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
                break;
            }
        }

        for (Command command : completedCommands) {
            this.queue.remove(command);
        }
    }

    /**
     *
     * @return
     */
    public int size () {
        return this.queue.size();
    }
}
