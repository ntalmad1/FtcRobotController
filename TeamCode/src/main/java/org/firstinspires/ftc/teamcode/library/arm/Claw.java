package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.arm.boom.Boom;
import org.firstinspires.ftc.teamcode.library.component.Component;

public class Claw extends Component {

    private Boom clawBoom;

    private ClawConfig config;

    /**
     *
     */
    public Claw(ClawConfig config) {
        super(config.robot);
        this.config = config;

        this.clawBoom = new Boom(config.clawBoomConfig);
    }

    public void init () {
        super.init();

        this.clawBoom.init();
    }

    public void run () {
        this.clawBoom.run();
    }



}
