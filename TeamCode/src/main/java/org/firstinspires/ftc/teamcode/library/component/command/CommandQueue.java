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
            if (command instanceof CommandGroup) {
                CommandGroup group = (CommandGroup)command;

                Command activeCommand = group.getActiveCommand();
                if (activeCommand == null) {
                    group.markAsCompleted();
                }
                this.component.runCommand(activeCommand);

            }
            else
            {
                this.component.runCommand(command);
            }

            if (command.isCompleted()) {
                completedCommands.add(command);
            }
        }

        for (Command command : completedCommands) {
            this.queue.remove(command);
        }
    }
}
