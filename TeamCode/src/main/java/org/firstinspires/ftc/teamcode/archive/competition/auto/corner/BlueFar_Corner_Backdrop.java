package org.firstinspires.ftc.teamcode.archive.competition.auto.corner;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.archive.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.archive.competition.config.RobotAutoConfig;
import org.firstinspires.ftc.teamcode.library.utility.Direction;

/**
 *
 */
@Autonomous(name="BlueFar_Corner_Backdrop", group="CornerBackdrop")
@Disabled
public class BlueFar_Corner_Backdrop extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueFar_Corner_Backdrop() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.LEFT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.FAR;
        this.robotAutoConfig.backdropDirection = Direction.LEFT;
        this.robotAutoConfig.useBackdrop = true;
        this.robotAutoConfig.parkPosition = RobotAutoConfig.ParkPosition.CORNER;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("BlueFar_Corner_Backdrop Initialized...");
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