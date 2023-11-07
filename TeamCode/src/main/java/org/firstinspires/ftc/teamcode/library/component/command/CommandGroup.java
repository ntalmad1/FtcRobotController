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

    /**
     *
     * @return
     */
    public List<Command> getCommands  () {
        return this.commands;
    }

    /**
     *
     */
    public void run () {
        if (this.isCompleted()) {
            return;
        }

        int completedCount = 0;

        for (Command command : commands) {
            if (command.isCompleted()) {
                completedCount++;
                continue;
            }

            if (!command.isInitialized()) {
                command.init();
            }
            command.run();

            if (!command.isCompleted() && command.isSynchronous()) {
                break;
            }
        }

        if (completedCount == this.commands.size()) {
            this.markAsCompleted();
        }
    }
}
