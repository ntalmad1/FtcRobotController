package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.boom.Boom;
import org.firstinspires.ftc.teamcode.library.boom.BoomMoveCommand;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;

public class Arm extends Component {

    /**
     */
    private ArmConfiguration config;

    /**
     */
    //private Boom topBoom;

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

        //this.topBoom = new Boom(this.config.topBoomConfig);
        this.midBoom = new Boom(this.config.midBoomConfig);
        this.midBoom.setInverted(true);

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
        //this.topBoom.init();
    }

    /**
     *
     */
    public void run ()  {
        super.run();

        this.bottomBoom.run();
        this.midBoom.run();
        //this.topBoom.run();

        if (this.config.debug) {

            this.telemetry.addData("Bottom degrees: ", "%2f", this.bottomBoom.getPositionDegrees());
            this.telemetry.addData("Bottom position: ", "%2f", this.bottomBoom.getServoPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Middle degrees: ", "%2f", this.midBoom.getPositionDegrees());
            this.telemetry.addData("Middle position: ", "%2f", this.midBoom.getServoPosition());
            this.telemetry.addLine();

           // this.telemetry.addData("Top degrees: ", "%2f", this.topBoom.getPositionDegrees());
           // this.telemetry.addData("Top position: ", "%2f", this.topBoom.getServoPosition());
            this.telemetry.addLine();

            this.telemetry.update();

        }
    }

    /**
     *
     * @param degrees
     * @return
     */
//    public Arm moveTop (double degrees) {
//        return this.moveTop(degrees, this.topBoom.getMaxIncrement());
//    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
//    public Arm moveTop (double degrees, double power) {
//        this.addCommand(new BoomMoveCommand(this.topBoom, degrees, power));
//        return this;
//    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveMiddle (double degrees) {
        return this.moveMiddle(degrees, this.midBoom.getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm moveMiddle (double degrees, double power) {
        this.addCommand(new BoomMoveCommand(this.midBoom, degrees, power));
        return this;
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveBottom (double degrees) {
        return this.moveBottom(degrees, this.bottomBoom.getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm moveBottom (double degrees, double power) {
        this.addCommand(new BoomMoveCommand(this.bottomBoom, degrees, power));
        return this;
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveBottomFromCurrentPosition (double degrees) {
        double targetDegrees = this.bottomBoom.getPositionDegrees() + degrees;
        return this.moveBottom(targetDegrees);
    }

    public Arm moveMiddleFromCurrentPosition (double degrees) {
        double targetDegrees = this.midBoom.getPositionDegrees() + degrees;
        return this.moveMiddle(targetDegrees);
    }

//    public Arm moveTopFromCurrentPosition (double degrees) {
//        double targetDegrees = this.topBoom.getPositionDegrees() + degrees;
//        return this.moveTop(targetDegrees);
//    }

    /**
     *
     * @param milliseconds
     * @return
     */
    public Arm wait (int milliseconds) {
        this.addCommand(new WaitCommand(milliseconds));
        return this;
    }
}
