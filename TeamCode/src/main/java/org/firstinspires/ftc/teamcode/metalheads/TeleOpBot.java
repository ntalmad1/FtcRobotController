package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOpBot", group = "Tests")
public class TeleOpBot extends CompBot {

    /**
     * Constructor
     *
     */
    public TeleOpBot() {
        super();

        this.setConfig(new TeleOpBotConfig(this));
        this.configureBot();

    }
}
