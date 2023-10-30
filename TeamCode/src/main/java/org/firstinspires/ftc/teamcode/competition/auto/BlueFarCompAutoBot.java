package org.firstinspires.ftc.teamcode.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 *
 */
@TeleOp(name="BlueFarCompAutoBot", group="Competition")
//@Disabled
public class BlueFarCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public BlueFarCompAutoBot () {
        super();
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

        //this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
        //this.driveTrain.gyroTurnLeft(0.1, 0.5, 90);
        //this.driveTrain.forward(0.1, 0.5, 243, Units.Centimeters);
    }

    public void run () {
        super.run();
    }

}
