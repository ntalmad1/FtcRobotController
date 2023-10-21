package org.firstinspires.ftc.teamcode.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 *
 */
@Autonomous(name="BlueNearCompAutoBot", group="Competition")
//@Disabled
public class BlueNearCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueNearCompAutoBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Blue Near Auto Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
        this.driveTrain.gyroTurnLeft(0.1, 0.5, 90);
        this.driveTrain.forward(0.1, 0.5, 100, Units.Centimeters);
    }

}
