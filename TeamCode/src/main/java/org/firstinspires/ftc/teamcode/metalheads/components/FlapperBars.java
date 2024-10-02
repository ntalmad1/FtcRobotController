package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;

/**
 *
 */
public class FlapperBars extends ServoComponent {
    /**
     * Constructor
     *
     * @param config The configuration values of the boom
     */
    public FlapperBars(FlapperBarsConfig config) {
        super(config);
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        if (this.isDebug()) {
            telemetry.addData("Flappers Servo Pos", this.getPosition());
        }
    }
}
