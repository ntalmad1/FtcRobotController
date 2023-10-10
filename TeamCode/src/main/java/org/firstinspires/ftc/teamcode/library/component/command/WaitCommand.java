package org.firstinspires.ftc.teamcode.library.component.command;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitCommand extends AbstractCommand {

    private double duration;

    private ElapsedTime timer;

    public WaitCommand(double duration) {
        this.duration = duration;
    }

    public void init() {
        this.timer = new ElapsedTime();
        this.timer.reset();
        this.setInitialized(true);
    }

    @Override
    public boolean isCompleted() {
        if (!this.isInitialized()) {
            return false;
        }
        
        if (this.completed) {
            return true;
        }

        if (this.timer.milliseconds() >= this.duration)
        {
            this.timer = null;
            this.markAsCompleted();
        }

        return this.completed;
    }
}
