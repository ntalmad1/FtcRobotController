package org.firstinspires.ftc.teamcode.archive.competition.auto.middle;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.archive.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.archive.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
@Autonomous(name="RedNear_Middle_Backdrop", group="MiddleBackdrop")
@Disabled
public class RedNear_Middle_Backdrop extends CompAutoBot {

    /**
     * Constructor
     */
    public RedNear_Middle_Backdrop() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.LEFT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.NEAR;
        this.robotAutoConfig.backdropDirection = Direction.RIGHT;
        this.robotAutoConfig.useBackdrop = true;
        this.robotAutoConfig.parkPosition = RobotAutoConfig.ParkPosition.MIDDLE;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("RedNear_Middle_Backdrop Initialized...");
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
