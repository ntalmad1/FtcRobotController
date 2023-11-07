package org.firstinspires.ftc.teamcode.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 *
 */
@Autonomous(name="RedNearCompAutoBot", group="Competition")
@Disabled
public class RedNearCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public RedNearCompAutoBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Red Near Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
        this.driveTrain.gyroTurnRight(0.1, 0.5, 90);
        this.driveTrain.forward(0.1, 0.5, 100, Units.Centimeters);
    }

}
