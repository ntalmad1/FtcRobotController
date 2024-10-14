package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOpBot", group = "Tests")
public class TeBot extends CompBot {

    /**
     * Constructor
     *
     */
    public TeBot() {
        super();

        this.setConfig(new TeBotConfig(this));
        this.configureBot();

    }
}
