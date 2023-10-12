package org.firstinspires.ftc.teamcode.library.component.command;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitCommand extends AbstractSynchronousCommand {

    private double duration;

    private ElapsedTime timer;

    /**
     * Empty Constructor
     */
    public WaitCommand () {
        super();
        this.setBlocking(true);
    }

    /**
     * Constructor
     *
     * @param duration
     */
    public WaitCommand(double duration) {
        this();
        this.duration = duration;
    }

    /**
     *
     */
    public void init() {
        if (this.duration > 0) {
            this.timer = new ElapsedTime();
            this.timer.reset();
        }
        this.setInitialized(true);
    }

    /**
     *
     */
    public void run () {
        if (this.isCompleted()) {
            return;
        }

        if (this.duration == 0) {
            this.markAsCompleted();
            return;
        }

        if (this.timer.milliseconds() >= this.duration)
        {
            this.timer = null;
            this.markAsCompleted();
        }
    }

    public String toString () {
        return this.getClass().toString() + ": " + this.duration;
    }
}
