package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.Component;

public class Arm extends Component {

    /**
     */
    private ArmConfiguration config;

    /**
     */
    private Boom topBoom;

    /**
     */
    private Boom midBoom;

    /**
     */
    private Boom bottomBoom;

    /**
     * @param configuration
     */
    public Arm (ArmConfiguration configuration) {
        super(configuration.robot);

        this.config = configuration;

        this.topBoom = new Boom(this.config.topBoomConfig);
        this.midBoom = new Boom(this.config.midBoomConfig);
        this.bottomBoom = new Boom(this.config.bottomBoomConfig);
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.bottomBoom.init();
        this.midBoom.init();
        this.topBoom.init();
    }

    /**
     *
     */
    public void run ()  {
        this.bottomBoom.run();
        this.midBoom.run();
        this.topBoom.run();
    }
}
