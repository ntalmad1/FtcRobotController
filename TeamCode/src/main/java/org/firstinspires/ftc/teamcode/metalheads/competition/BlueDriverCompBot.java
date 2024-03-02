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
        //if always forwards is of yawoffset = 90 else it's 0
        this.driveTrainConfig.yawOffset = 90;
        //this.armConfig.debug = true;
        this.armConfig.bottomBoomConfig.invertInput = false;
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
