package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;

public class Winch extends EncodedMotor {

    /**
     * Constuctor
     *
     * @param config
     */
    public Winch(WinchConfig config) {
        super(config);
    }

    /**
     *
     * @return
     */
    public WinchConfig getConfig() {
        return (WinchConfig) super.getConfig();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        if (this.isDebug()) {
            telemetry.addData("Winch Pos:", this.getCurrentPosition());
        }
    }
}
