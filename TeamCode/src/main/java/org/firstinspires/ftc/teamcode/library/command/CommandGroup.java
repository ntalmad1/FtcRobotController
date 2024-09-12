package org.firstinspires.ftc.teamcode.library.command;

import java.util.ArrayList;
import java.util.List;

public class CommandGroup  extends AbstractCommand {

    /**
     */
    private List<ICommand> commands;

    /**
     *
     */
    public CommandGroup () {
        this.commands = new ArrayList<ICommand>();
    }

    /**
     *
     * @param command
     */
    public void add(ICommand command) {
        this.commands.add(command);
    }

    /**
     *
     * @return
     */
    public List<ICommand> getCommands  () {
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

        for (ICommand command : commands) {
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
