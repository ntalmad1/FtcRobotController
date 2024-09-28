package org.firstinspires.ftc.teamcode.metalheads.components;


import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponent;

/**
 *
 */
public class DoubleHooks extends Component {

    /**
     */
    private DoubleHooksConfig config;

    /**
     */
    private EncodedMotor linearActuator;

    /**
     */
    private ServoComponent doubleServos;

    /**
     */
    private boolean initialized = false;

    /**
     * Constructor
     * @param doubleHooksConfig
     */
    public DoubleHooks(DoubleHooksConfig doubleHooksConfig) {
        super(doubleHooksConfig.robot);
        this.config = doubleHooksConfig;
    }

    public void init() {
        this.linearActuator = new EncodedMotor(this.config.linearActuatorConfig);
        this.doubleServos = new ServoComponent(this.config.doubleServosConfig);

        this.linearActuator.init();
        this.doubleServos.init();

        this.initialized = true;
    }

    /**
     */
    @Override
    public void run() {
        if (this.initialized) {
            super.run();

            this.doubleServos.run();
            this.linearActuator.run();
        }
    }
}
