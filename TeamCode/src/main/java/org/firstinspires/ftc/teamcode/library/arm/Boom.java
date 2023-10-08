package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXEvent;
import org.firstinspires.ftc.teamcode.library.component.event.gp2_left_stick_x.Gp2_LeftStickXHandler;

/**
 *
 */
public class Boom extends Component implements Gp2_LeftStickXHandler
{

    /**
     * @param robot
     */
    public Boom(IsaacBot robot) {
        super(robot);

        this.addGp2_LeftStickXHandler(this);
    }

    public void init ()
    {
        super.init();

    }

    /**
     *
     */
    public void run ()
    {
        super.run();


    }

    @Override
    public void onGp2_LeftStickX(Gp2_LeftStickXEvent event) {
        this.telemetry.addData("Class handler Left stick x:", "%2f", event.getPosition());

    }
}
