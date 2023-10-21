package org.firstinspires.ftc.teamcode.library.arm;

import org.firstinspires.ftc.teamcode.library.boom.Boom;
import org.firstinspires.ftc.teamcode.library.boom.BoomMoveToDegreesCommand;
import org.firstinspires.ftc.teamcode.library.boom.BoomMoveToPositionCommand;
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
    public Arm moveClawToDegrees (double degrees) {
        return this.moveClawToDegrees(degrees, this.claw.getBase().getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm moveClawToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.claw.getBase(), degrees, power));
        return this;
    }

    /**
     *
     * @param position
     * @return
     */
    public Arm moveClawToPosition (double position) {
        return this.moveClawToPosition(position, this.claw.getBase().getMaxIncrement());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Arm moveClawToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.claw.getBase(), this.claw.getBase().getServoPosition(), position, power));
        return this;
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm rotateClawToDegrees (double degrees) {
        return this.rotateClawToDegrees(degrees, this.claw.getRotator().getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm rotateClawToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.claw.getRotator(), degrees, power));
        return this;
    }

    /**
     *
     * @param position
     * @return
     */
    public Arm rotateClawToPosition (double position) {
        return this.rotateClawToPosition(position, this.claw.getRotator().getMaxIncrement());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Arm rotateClawToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.claw.getRotator(), this.claw.getRotator().getServoPosition(), position, power));
        return this;
    }


    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveMiddleToDegrees (double degrees) {
        return this.moveMiddleToDegrees(degrees, this.midBoom.getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm moveMiddleToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.midBoom, degrees, power));
        return this;
    }

    /**
     *
     * @param position
     * @return
     */
    public Arm moveMiddleToPosition (double position) {
        return this.moveMiddleToPosition(position, this.midBoom.getMaxIncrement());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Arm moveMiddleToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.midBoom, this.midBoom.getServoPosition(), position, power));
        return this;
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveBottomToDegrees (double degrees) {
        return this.moveBottomToDegrees(degrees, this.bottomBoom.getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return
     */
    public Arm moveBottomToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.bottomBoom, degrees, power));
        return this;
    }

    /**
     *
     * @param position
     * @return
     */
    public Arm moveBottomToPosition (double position) {
        return this.moveBottomToPosition(position, this.bottomBoom.getMaxIncrement());
    }

    /**
     *
     * @param position
     * @param power
     * @return
     */
    public Arm moveBottomToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.bottomBoom, this.bottomBoom.getServoPosition(), position, power));
        return this;
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveBottomDegreesFromCurrentPosition (double degrees) {
        double targetDegrees = this.bottomBoom.getPositionDegrees() + degrees;
        return this.moveBottomToDegrees(targetDegrees);
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveMiddleDegreesFromCurrentPosition (double degrees) {
        double targetDegrees = this.midBoom.getPositionDegrees() + degrees;
        return this.moveMiddleToDegrees(targetDegrees);
    }

    /**
     *
     * @param degrees
     * @return
     */
    public Arm moveClawDegreesFromCurrentPosition (double degrees) {
        double targetDegrees = this.claw.getBase().getPositionDegrees() + degrees;
        return this.moveClawToDegrees(targetDegrees);
    }

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
