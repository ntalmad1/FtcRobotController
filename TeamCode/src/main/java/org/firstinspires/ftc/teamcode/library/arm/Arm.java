package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.boom.Boom;
import org.firstinspires.ftc.teamcode.library.boom.BoomMoveCommand;
import org.firstinspires.ftc.teamcode.library.claw.Claw;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.component.command.WaitCommand;

public class Arm extends Component {

    /**
     */
    private ArmConfig config;

    /**
     */
    private Claw claw;

    /**
     */
    private Boom midBoom;

    /**
     */
    private Boom bottomBoom;

    /**
     * @param configuration
     */
    public Arm (ArmConfig configuration) {
        super(configuration.robot);

        this.config = configuration;

        this.claw = new Claw(this.config.clawConfig);
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
        this.claw.init();
    }

    /**
     *
     */
    public void run ()  {
        super.run();

        this.bottomBoom.run();
        this.midBoom.run();
        this.claw.run();

        if (this.config.debug) {

            this.telemetry.addData("Bottom boom degrees: ", "%2f", this.bottomBoom.getPositionDegrees());
            this.telemetry.addData("Bottom boom position: ", "%2f", this.bottomBoom.getServoPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Middle boom degrees: ", "%2f", this.midBoom.getPositionDegrees());
            this.telemetry.addData("Middle boom position: ", "%2f", this.midBoom.getServoPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Claw boom degrees: ", "%2f", this.claw.getBase().getPositionDegrees());
            this.telemetry.addData("Claw boom position: ", "%2f", this.claw.getBase().getServoPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Claw rotator degrees: ", "%2f", this.claw.getRotator().getPositionDegrees());
            this.telemetry.addData("Claw rotator position: ", "%2f", this.claw.getRotator().getServoPosition());
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
