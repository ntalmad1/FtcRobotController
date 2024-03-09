package org.firstinspires.ftc.teamcode.metalheads.competition.auto.nobackrop;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;

/**
 *
 */
@Autonomous(name="BlueNear_Corner_NoBackdrop", group="NoBackdrop")
//@Disabled
public class BlueNear_Corner_NoBackdrop extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueNear_Corner_NoBackdrop() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.RIGHT;
        this.robotAutoConfig.startPosition = RobotAutoConfig.StartPosition.NEAR;
        this.robotAutoConfig.backdropDirection = Direction.LEFT;
        this.robotAutoConfig.useBackdrop = false;
        this.robotAutoConfig.parkPosition = RobotAutoConfig.ParkPosition.CORNER;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("BlueNear_Corner_NoBackdrop Initialized...");
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
