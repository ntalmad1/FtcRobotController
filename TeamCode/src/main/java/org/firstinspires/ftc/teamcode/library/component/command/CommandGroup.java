package org.firstinspires.ftc.teamcode.library.component.command;

import java.util.ArrayList;
import java.util.List;

public class CommandGroup  extends AbstractCommand {

    /**
     */
    private List<Command> commands;

    /**
     *
     */
    public CommandGroup () {
        this.commands = new ArrayList<Command>();
    }

    /**
     *
     * @param command
     */
    public void add(Command command) {
        this.commands.add(command);
    }

    public Command getActiveCommand () {

        for (Command command : this.commands) {
            if (!command.isCompleted()) {
                return command;
            }
        }

        this.markAsCompleted();

        return null;
    }
}
