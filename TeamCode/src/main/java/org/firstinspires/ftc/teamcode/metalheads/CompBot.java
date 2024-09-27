package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.metalheads.base.CompBotConfig;

@TeleOp(name="CompBot", group="Base")
//@Disabled
public class CompBot extends IsaacBot {

    /**
     */
    protected CompBotConfig config;

    /**
     * Constructor
     *
     */
    public CompBot(){
        super();

        CompBotConfig compBotConfig = new CompBotConfig() {};

        this.config = compBotConfig;
    }


    /**
     * Constructor
     *
     * @param compBotConfig
     */
    public CompBot(CompBotConfig compBotConfig) {
        super();

        this.config = compBotConfig;
    }

    /**
     *
     */
    @Override
    public void initBot(){

    }

    /**
     *
     */
    @Override
    public void go (){

    }

    /**
     *
     */
    @Override
    public void run() {

    }

    /**
     *
     */
    @Override
    public void onStop() {

    }
}