package org.firstinspires.ftc.teamcode.library.action;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitAction extends AbstractAction {

    private double duration;

    private ElapsedTime timer;

    /**
     * Empty Constructor
     */
    public WaitAction () {
        super();
    }

    /**
     * Constructor
     *
     * @param duration in milli seconds
     */
    public WaitAction(double duration) {
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
    }

    /**
     *
     */
    public boolean run() {

        if (!this.isInitialized()) {
            this.init();
        }

        if (this.duration == 0) {
            this.markAsCompleted();
            return STOP;
        }
        else if (this.timer.milliseconds() >= this.duration)        {
            this.timer = null;
            this.markAsCompleted();
            return STOP;
        }
        else {
            return CONTIUE;
        }
    }

    /**
     *
     * @return
     */
    public String toString() {
        return this.getClass() + ": " + this.duration;
    }

}
