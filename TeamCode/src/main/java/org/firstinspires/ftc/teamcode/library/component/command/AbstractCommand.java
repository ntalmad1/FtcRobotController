package org.firstinspires.ftc.teamcode.library.component.command;

public class AbstractCommand implements Command {

    private boolean completed;

    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    public void markAsCompleted () {
        this.completed = true;
    }
}
