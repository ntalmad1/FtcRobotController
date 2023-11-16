package org.firstinspires.ftc.library.dronelauncher;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.boom.BoomMoveToPositionCommand;
import org.firstinspires.ftc.library.component.Component;
import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.WaitCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackHandler;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.rotator.Rotator;


/**
 *
 */
public class DroneLauncher extends Component {

    /**
     *
     */
    protected DroneLauncherConfig config;

    /**
     *
     */
    protected Rotator rotator;

    /**
     *
     */
    protected Servo trigger;

    /**
     * Constructor
     *
     * @param config The configuration values for the drone launcher
     */
    public DroneLauncher (DroneLauncherConfig config) {
        super(config.robot);

        this.config = config;
    }

    /**
     *
     */
    public void init () {
        super.init();

        this.trigger = this.robot.hardwareMap.get(Servo.class, this.config.triggerServoName);
        this.trigger.resetDeviceConfigurationForOpMode();
        this.trigger.setPosition(this.config.triggerServoDownPos);

        this.rotator = new Rotator(this.config.rotatorConfig);
        this.rotator.init();
    }

    /**
     *
     */
    public void run () {
        super.run();

        this.rotator.run();
    }

    /**
     *
     */
    public void launchDrone () {

        this.rotateToPosition(this.config.launchPosition, 0.005);
        this.wait(500, new CommandCallbackAdapter(){
            public void onSuccess(CommandSuccessEvent successEvent) {
                DroneLauncher.this.trigger.setPosition(DroneLauncher.this.config.triggerServoUpPos);
            }
        });


    }

    /**
     *
     * @param position The position to rotate the launcher to
     * @return DroneLauncher - fluid interface
     */
    public DroneLauncher rotateToPosition (double position, double power) {
        this.addCommand(new BoomMoveToPositionCommand(this.rotator, position, power));
        return this;
    }

    /**
     *
     * @param milliseconds
     * @return
     */
    public DroneLauncher wait (int milliseconds) {
        this.addCommand(new WaitCommand(milliseconds));
        return this;
    }

    /**
     *
     * @param milliseconds
     * @param handler
     * @return
     */
    public DroneLauncher wait (int milliseconds, CommandCallbackHandler handler) {
        ICommand command = new WaitCommand(milliseconds);
        command.addCallbackHandler(handler);
        this.addCommand(command);
        return this;
    }
}
