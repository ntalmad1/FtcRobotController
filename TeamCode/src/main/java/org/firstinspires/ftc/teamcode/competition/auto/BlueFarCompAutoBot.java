package org.firstinspires.ftc.teamcode.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.competition.base.CompAutoBot;
import org.firstinspires.ftc.teamcode.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.teamcode.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.teamcode.library.component.event.ping.PingHandler;
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

        super.go();

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce() {
                BlueFarCompAutoBot.this.ping(0, 0, new PingHandler() {
                    public void onPing(PingEvent event) {
                        BlueFarCompAutoBot.this.telemetry.addData("Degrees: ", "%2f", event.getDegrees());
                        BlueFarCompAutoBot.this.telemetry.addData("Distance: ", "%2f", + event.getDistance());
                        BlueFarCompAutoBot.this.telemetry.update();
                    }
                });
            }
        });
    }

    public void run () {
        super.run();
    }

}
