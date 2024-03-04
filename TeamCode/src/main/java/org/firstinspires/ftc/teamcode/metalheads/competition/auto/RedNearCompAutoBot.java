package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;

/**
 *
 */
@Autonomous(name="RedNearCompAutoBot", group="Red")
// @Disabled
public class RedNearCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public RedNearCompAutoBot() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.LEFT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.NEAR;
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

        super.go();

    }

    /**
     *
     */
    public void run () {
        super.run();
    }

}
