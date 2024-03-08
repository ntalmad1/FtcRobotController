package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.metalheads.competition.config.RobotAutoConfig;

/**
 *
 */
@TeleOp(name="BlueFar_Corner_Backdrop", group="CornerBackdrop")
//@Disabled
public class Auto_BlueFar_Corner_Backdrop extends CompAutoBot {

    /**
     * Constructor
     */
    public Auto_BlueFar_Corner_Backdrop() {
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
