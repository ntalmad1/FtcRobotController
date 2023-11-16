package org.firstinspires.ftc.teamcode.metalheads.competition;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompDriverBot;

@TeleOp(name="RedDriverCompBot", group="Competition OpMode")
@Disabled
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
        this.armConfig.debug = true;
        this.armConfig.bottomBoomConfig.invertInput = false;
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
