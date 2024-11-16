package org.firstinspires.ftc.teamcode.metalheads.compbot;

/**
 * Private class
 *
 */
public class TrajectoryFactory {

    /**
     */
    private AutoBot autoBot;

    /**
     */
    public TrajectoryFactory(AutoBot autoBot) {
        this.autoBot = autoBot;
    }

    /**
     *
     * @return
     */
    public AutoBot getAutoBot () {
        return this.autoBot;
    }
}
