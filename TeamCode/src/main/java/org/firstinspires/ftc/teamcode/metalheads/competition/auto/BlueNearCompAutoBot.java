package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.library.utility.Units;

/**
 *
 */
@Autonomous(name="BlueNearCompAutoBot", group="Blue")
//@Disabled
public class BlueNearCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueNearCompAutoBot() {
        super();

        this.robotAutoConfig.startingTrussDirection = Direction.RIGHT;
        this.robotAutoConfig.routine = Routine.NEAR;

        this.robotAutoConfig.placeYellowPixelDistance_near = 63;
        this.robotAutoConfig.placeYellowPixelDistance_middle = 79;
        this.robotAutoConfig.placeYellowPixelDistance_far = 100;
        this.robotAutoConfig.placePurplePixel_forwards_sideDistance = 18;
        this.robotAutoConfig.placePurplePixel_oppositeTruss_sideDistance = 39;


        this.robotAutoConfig.rotateTrussSide = 42;

        this.robotAutoConfig.autoCorrectAtEnd = false;
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Blue Near Auto Initialized...");
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
