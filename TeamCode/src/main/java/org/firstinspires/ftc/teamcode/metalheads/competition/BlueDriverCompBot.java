package org.firstinspires.ftc.teamcode.metalheads.competition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompDriverBot;

@TeleOp(name="BlueDriverCompBot", group="Blue")
//@Disabled
public class BlueDriverCompBot extends CompDriverBot {

    /**
     * Constructor
     *
     */
    public BlueDriverCompBot () {
        super();
    }

    /**
     *
     */
    public void initBot () {
        this.driveTrainConfig.yawOffset = 90;
        this.armConfig.debug = false;
        this.armConfig.bottomBoomConfig.invertInput = false;
        this.armConfig.midBoomConfig.invertInput = true;
        this.armConfig.clawConfig.clawBoomConfig.invertInput = false;

        super.initBot();

        this.telemetry.addLine("Blue Driver Comp Bot Initialized...");
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
