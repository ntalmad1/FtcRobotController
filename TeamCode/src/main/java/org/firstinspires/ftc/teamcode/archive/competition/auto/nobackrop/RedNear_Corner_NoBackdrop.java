package org.firstinspires.ftc.teamcode.archive.competition.auto.nobackrop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.archive.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.archive.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
@Autonomous(name="RedNear_Corner_NoBackdrop", group="NoBackdrop")
@Disabled
public class RedNear_Corner_NoBackdrop extends CompAutoBot {

    /**
     * Constructor
     */
    public RedNear_Corner_NoBackdrop() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.LEFT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.NEAR;
        this.robotAutoConfig.backdropDirection = Direction.RIGHT;
        this.robotAutoConfig.useBackdrop = false;
        this.robotAutoConfig.parkPosition = RobotAutoConfig.ParkPosition.CORNER;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("RedNear_Corner_NoBackdrop Initialized...");
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
