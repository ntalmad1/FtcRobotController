package org.firstinspires.ftc.teamcode.metalheads.competition;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompDriverBot;

@TeleOp(name="RedDriverCompBot", group="Red")
//@Disabled
public class RedDriverCompBot extends CompDriverBot {

    /**
     * Constructor
     *
     */
    public RedDriverCompBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {
        this.driveTrainConfig.yawOffset = -90;
        this.armConfig.debug = false;
        this.armConfig.bottomBoomConfig.invertInput = true;
        this.armConfig.midBoomConfig.invertInput = true;

        super.initBot();

        this.telemetry.addLine("Red Driver Comp Bot Initialized...");
        this.telemetry.addLine("READY!");
        this.telemetry.update();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
