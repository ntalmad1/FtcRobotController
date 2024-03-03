package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.library.utility.Direction;

/**
 *
 */
@TeleOp(name="BlueFarCompAutoBot", group="Blue")
//@Disabled
public class BlueFarCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueFarCompAutoBot () {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.LEFT;
        this.robotAutoConfig.routine = Routine.FAR;

        this.robotAutoConfig.distanceUnderTruss_oppositeTruss = 120;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Blue Far Auto Initialized...");
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
