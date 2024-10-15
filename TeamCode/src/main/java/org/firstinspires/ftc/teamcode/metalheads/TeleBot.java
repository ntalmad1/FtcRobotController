package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleBot", group = "Tests")
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

    /**
     *
     */
    @Override
    protected void configureBot() {
        super.configureBot();
    }
}
