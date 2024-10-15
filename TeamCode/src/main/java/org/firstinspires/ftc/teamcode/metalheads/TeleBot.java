package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOpBot", group = "Tests")
public class TeleBot extends CompBot {

    /**
     * Constructor
     *
     */
    public TeleBot() {
        super();

        this.setConfig(new TeleBotConfig(this));
        this.configureBot();

    }
}
