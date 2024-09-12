package org.firstinspires.ftc.teamcode.archive.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;

/**
 *
 */
@Autonomous(name="RedFarCompAutoBot", group="Red")
@Disabled
public class RedFarCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public RedFarCompAutoBot() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.RIGHT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.FAR;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Red Far Auto Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        super.go();

    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
