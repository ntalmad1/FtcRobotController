package org.firstinspires.ftc.teamcode.library.component.command;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitCommand extends AbstractSynchronousCommand {

    private double duration;

    private ElapsedTime timer;

    public WaitCommand(double duration) {
        super();
        this.duration = duration;
    }

    public void init() {
        this.timer = new ElapsedTime();
        this.timer.reset();
        this.setInitialized(true);
    }

    public void run () {
        if (!this.isInitialized() || this.isCompleted()) {
            return;
        }

        if (this.timer.milliseconds() >= this.duration)
        {
            this.timer = null;
            this.markAsCompleted();
        }
    }
}
