package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.arm.boom.Boom;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.Command;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;

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

        this.addGp2_LeftStickXHandler(event -> Arm.this.cancelAllCommands());
        this.addGp2_LeftStickYHandler(event -> Arm.this.cancelAllCommands());
        this.addGp2_RightStickXHandler(event -> Arm.this.cancelAllCommands());
        this.addGp2_RightStickYHandler(event -> Arm.this.cancelAllCommands());
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
        super.run();

        this.bottomBoom.run();
        this.midBoom.run();
        this.topBoom.run();

        this.telemetry.addData("Bottom degrees: ", "%2f", this.bottomBoom.getPositionDegrees());
        this.telemetry.addData("Middle degrees: ", "%2f", this.midBoom.getPositionDegrees());
        this.telemetry.addData("Top degrees: ", "%2f", this.topBoom.getPositionDegrees());
        this.telemetry.update();
    }


    public Arm moveTop (double degrees) {
        this.addCommand(new BoomMoveCommand(this.topBoom, degrees));
        return this;
    }

    public Arm moveMiddle (double degrees) {
        this.addCommand(new BoomMoveCommand(this.midBoom, degrees));
        return this;
    }

    public Arm moveBottom (double degrees) {
        this.addCommand(new BoomMoveCommand(this.bottomBoom, degrees));
        return this;
    }

    public Arm wait (int milliseconds) {
        this.addCommand(new WaitCommand(milliseconds));
        return this;
    }
}
