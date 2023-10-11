package org.firstinspires.ftc.teamcode.library.component.command;

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

    public void run ()
    {
        List<Command> completedCommands = new ArrayList<Command>();

        for (Command command : this.queue)
        {
            if (!command.isInitialized()) {
                command.init();
            }
            this.component.runCommand(command);

            if (command.isCompleted()) {
                completedCommands.add(command);
            }

            if (!command.isCompleted() && command.isSynchronous()) {
                break;
            }
        }

        for (Command command : completedCommands) {
            this.queue.remove(command);
        }
    }
}
