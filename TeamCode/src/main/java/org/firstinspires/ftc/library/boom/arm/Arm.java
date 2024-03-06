package org.firstinspires.ftc.library.boom.arm;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.boom.Boom;
import org.firstinspires.ftc.library.boom.BoomMoveToDegreesCommand;
import org.firstinspires.ftc.library.boom.BoomMoveToPositionCommand;
import org.firstinspires.ftc.library.claw.Claw;
import org.firstinspires.ftc.library.claw.ClawCloseCommand;
import org.firstinspires.ftc.library.claw.ClawOpenCommand;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.command.Command;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.WaitCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.motor.EncodedMotor;

/**
 *
 */
public class Arm extends Component {

    /**
     */
    private final Boom bottomBoom;

    /**
     */
    private final Claw claw;

    /**
     */
    private final ArmConfig config;

    /**
     */
    private final EncodedMotor linAct;

    /**
     */
    private Servo leftRelay;

    /**
     */
    private Servo rightRelay;

    /**
     */
    private double bottomBoomIncrement;

    /**
     * Constructor
     *
     * @param configuration The configuration values for the Arm
     */
    public Arm (ArmConfig configuration) {
        super(configuration.robot);

        this.config = configuration;

        this.claw = new Claw(this.config.clawConfig);
        this.linAct = new EncodedMotor(this.config.linActConfig);

        this.bottomBoom = new Boom(this.config.bottomBoomConfig);
        this.bottomBoomIncrement = this.config.bottomBoomConfig.maxIncrement;

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

        //this.getCommandQueue().setDebug(true);

        this.rightRelay = this.robot.hardwareMap.get(Servo.class, "rightBoomRelay");
        this.leftRelay = this.robot.hardwareMap.get(Servo.class, "leftBoomRelay");

        rightRelay.setPosition(1);
        leftRelay.setPosition(1);

        this.claw.init();

        this.bottomBoom.init();

        this.linAct.init();
    }

    /**
     *
     */
    public void run ()  {
        super.run();

        this.bottomBoom.run();
        this.linAct.run();
        this.claw.run();

        if (this.config.debug) {
            this.telemetry.addData("Bottom boom increment: ", "%2f", this.bottomBoomIncrement);
            this.telemetry.addData("Bottom boom degrees: ", "%2f", this.bottomBoom.getPositionDegrees());
            this.telemetry.addData("Bottom boom position: ", "%2f", this.bottomBoom.getPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Linear actuator position: ", "%7d", this.linAct.getCurrentPosition());
            this.telemetry.addLine();

            this.telemetry.addData("Claw boom degrees: ", "%2f", this.claw.getBase().getPositionDegrees());
            this.telemetry.addData("Claw boom position: ", "%2f", this.claw.getBase().getPosition());
            this.telemetry.addLine();

            this.telemetry.update();
        }
    }

    /**
     *
     * @param command
     */
    public Arm addCommand (ICommand command) {
        return (Arm) super.addCommand(command);
    }

    public Arm closeLeftClaw () {
        this.addCommand(new ClawCloseCommand(this.claw, Claw.Side.LEFT));

        return this;
    }

    public Arm closeRightClaw () {
        this.addCommand(new ClawCloseCommand(this.claw, Claw.Side.RIGHT));
        return this;
    }

    /**
     *
     * @return
     */
    public double getBottomBoomIncrement () {
        return this.bottomBoomIncrement;
    }

    /**
     *
     * @return Claw
     */
    public Claw getClaw () {
        return this.claw;
    }

    public Boom getBottomBoom () {
        return this.bottomBoom;
    }

    /**
     *
     * @return EncodedMotor
     */
    public EncodedMotor getLinearActuator () {
        return this.linAct;
    }


    /**
     *
     * @return Arm
     */
    public Arm openRightClaw () {
        this.addCommand(new ClawOpenCommand(this.claw, Claw.Side.RIGHT));
        return this;
    }

    /**
     *
     * @param degrees Number of degrees to move the claw base by
     * @return "this" for a fluid interface
     */
    public Arm moveClawDegreesFromCurrentPosition (double degrees) {
        double targetDegrees = this.claw.getBase().getPositionDegrees() + degrees;
        return this.moveClawToDegrees(targetDegrees);
    }

    /**
     *
     * @param degrees Move the claw base to the given degrees position
     * @return "this" for a fluid interface
     */
    public Arm moveClawToDegrees (double degrees) {
        return this.moveClawToDegrees(degrees, this.claw.getBase().getMaxIncrement());
    }

    /**
     *
     * @param degrees Moves the claw base to the given degrees position
     * @param power The increment to move by for each cycle
     * @return "this" for a fluid interface
     */
    public Arm moveClawToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.claw.getBase(), degrees, power));
        return this;
    }

    /**
     *
     * @param position Move the claw base to the given position ( 0 - 1 )
     * @return "this" for a fluid interface
     */
    public Arm moveClawToPosition (double position) {
        return this.moveClawToPosition(position, this.claw.getBase().getMaxIncrement());
    }

    /**
     *
     * @param position Move the claw to the given position ( 0 - 1 )
     * @param power The amount to increment position by each cycle ( 0 - 1 )
     * @return "this" for a fluid interface
     */
    public Arm moveClawToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.claw.getBase(), position, power));
        return this;
    }

    /**
     *
     * @param position
     * @return Arm
     */
    public Arm moveLinearActuatorToPosition (int position) {
        return this.moveLinearActuatorToPosition(position, 1, new CommandCallbackAdapter());
    }

    /**
     *
     * @param position
     * @param handler
     * @return
     */
    public Arm moveLinearActuatorToPosition (int position, CommandCallbackHandler handler) {
        return this.moveLinearActuatorToPosition(position, 1, handler);
    }

    /**
     *
     * @param position
     * @param power
     * @return Arm
     */
    public Arm moveLinearActuatorToPosition (int position, double power) {
        return this.moveLinearActuatorToPosition(position, 1, new CommandCallbackAdapter());
    }

    public Arm moveLinearActuatorToPosition (int position, double power, CommandCallbackHandler handler) {
        LinearActuatorMoveToPositionCommand command = new LinearActuatorMoveToPositionCommand(this.getLinearActuator(), position, power);
        command.addCallbackHandler(handler);
        this.addCommand(command);
        return this;
    }

    /**
     *
     * @param degrees
     * @return "this" for a fluid interface
     */
    public Arm moveBottomToDegrees (double degrees) {
        return this.moveBottomToDegrees(degrees, this.bottomBoom.getMaxIncrement());
    }

    /**
     *
     * @param degrees
     * @param power
     * @return "this" for a fluid interface
     */
    public Arm moveBottomToDegrees (double degrees, double power) {
        this.addCommand(new BoomMoveToDegreesCommand(this.bottomBoom, degrees, power));
        return this;
    }

    /**
     *
     * @param position
     * @return "this" for a fluid interface
     */
    public Arm moveBottomToPosition (double position) {
        return this.moveBottomToPosition(position, this.bottomBoom.getMaxIncrement());
    }

    /**
     *
     * @param position
     * @param power
     * @return "this" for a fluid interface
     */
    public Arm moveBottomToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.bottomBoom, position, power));
        return this;
    }

    /**
     *
     * @param degrees Number of degrees to move the bottom boom two
     * @return "this" for a fluid interface
     */
    public Arm moveBottomDegreesFromCurrentPosition (double degrees) {
        double targetDegrees = this.bottomBoom.getPositionDegrees() + degrees;
        return this.moveBottomToDegrees(targetDegrees);
    }

    /**
     *
     */
    public void setBottomBoomOff() {
        this.rightRelay.setPosition(0);
        this.leftRelay.setPosition(0);
    }

    /**
     *
     */
    public void setBottomBoomOn() {
        this.rightRelay.setPosition(1);
        this.leftRelay.setPosition(1);
    }

    public void setBottomBoomIncrement (double increment) {
        this.bottomBoomIncrement = increment;
        this.bottomBoom.setMaxIncrement(increment);
    }

    /**
     *
     * @return Arm
     */
    public Arm openLeftClaw () {
        this.addCommand(new ClawOpenCommand(this.claw, Claw.Side.LEFT));
        return this;
    }

    /**
     *
     * @param command
     */
    public void endCommand (ICommand command) {
        this.endCommand(0, command);
    }

    /**
     *
     * @param milliseconds
     * @param command
     */
    public void endCommand (int milliseconds, ICommand command) {

        this.wait(milliseconds, new CommandCallbackAdapter() {
            @Override
            public void onSuccess(CommandSuccessEvent successEvent) {
                command.markAsCompleted();
            }
        });
    }

    /**
     *
     * @param milliseconds Number of milliseconds to wait for
     * @return "this" for a fluid interface
     */
    public Arm wait (int milliseconds) {
        return (Arm) super.wait(milliseconds);
    }

    /**
     *
     * @param milliseconds
     * @param handler
     * @return
     */
    public Arm wait (int milliseconds, CommandCallbackHandler handler) {
        ICommand command = new WaitCommand(milliseconds);
        command.addCallbackHandler(handler);
        this.addCommand(command);
        return this;
    }
}
