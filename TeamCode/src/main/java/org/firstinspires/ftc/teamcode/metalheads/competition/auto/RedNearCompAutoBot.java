package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.library.utility.Units;

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
        this.robotAutoConfig.routine = Routine.NEAR;

        this.robotAutoConfig.placeYellowPixelDistance_near = 60;
        this.robotAutoConfig.placeYellowPixelDistance_middle = 79;
        this.robotAutoConfig.placeYellowPixelDistance_far = 100;

        this.robotAutoConfig.placePurplePixel_forwards_sideDistance = 15;
        this.robotAutoConfig.placePurplePixel_oppositeTruss_sideDistance = 34;

        this.robotAutoConfig.rotateTrussSide = 43;

        this.robotAutoConfig.autoCorrectAtEnd = false;
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
