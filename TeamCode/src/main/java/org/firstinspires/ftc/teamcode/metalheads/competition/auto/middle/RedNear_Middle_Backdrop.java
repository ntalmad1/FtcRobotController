package org.firstinspires.ftc.teamcode.metalheads.competition.auto.middle;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;

/**
 *
 */
@Autonomous(name="RedNear_Middle_Backdrop", group="MiddleBackdrop")
//@Disabled
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