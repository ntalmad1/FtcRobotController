package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.metalheads.base.CompBotConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooks;

@TeleOp(name="CompBot", group="Base")
//@Disabled
public class CompBot extends IsaacBot {

    /**
     */
    protected CompBotConfig config;

    /**
     */
    private DoubleHooks doubleHooks;

    /**
     * Constructor
     *
     */
    public CompBot(){
        this(null);
    }


    /**
     * Constructor
     *
     * @param compBotConfig
     */
    public CompBot(CompBotConfig compBotConfig) {
        super();

        if (compBotConfig == null) {
            compBotConfig = new CompBotConfig(this);
        }

        this.config = compBotConfig;

        this.doubleHooks = new DoubleHooks(compBotConfig.doubleHooksConfig);
    }

    /**
     *
     */
    @Override
    public void initBot(){
        this.doubleHooks.init();
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
        super.run();

        this.doubleHooks.run(this.config.debug);

        if (this.config.debug) {
            telemetry.update();
        }
    }

    /**
     *
     */
    @Override
    public void onStop() {

    }
}