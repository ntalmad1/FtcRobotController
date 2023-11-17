package org.firstinspires.ftc.teamcode.metalheads.competition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompDriverBot;

@TeleOp(name="BlueDriverCompBot", group="Competition OpMode")
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
        this.armConfig.debug = true;
        this.driveTrainConfig.yawOffset = 90;
        this.armConfig.bottomBoomConfig.invertInput = true;
        this.armConfig.midBoomConfig.invertInput = true;

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
