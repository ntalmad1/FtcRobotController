package org.firstinspires.ftc.teamcode.metalheads.components;

import org.firstinspires.ftc.teamcode.library.component.Component;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotor;

/**
 *
 */
public class BigArm extends Component {

    /**
     */
    public BigArmConfig config;

    /**
     */
    public EncodedMotor mainBoom;

    /**
     */
    public PotentiometerMotor viperSlide;

    /**
     * Constructor
     *
     * @param bigArmConfig
     */
    public BigArm(BigArmConfig bigArmConfig) {
        super(bigArmConfig.robot);

        this.config = bigArmConfig;

        this.mainBoom = new EncodedMotor(this.config.mainBoomConfig);
        this.viperSlide = new PotentiometerMotor(this.config.viperSlideConfig);
    }

    /**
     *
     */
    @Override
    public void init() {
        super.init();

        this.mainBoom.init();
        this.viperSlide.init();
    }

    /**
     *
     */
    @Override
    public void run() {
        super.run();

        this.mainBoom.run();
        this.viperSlide.run();

        if (this.isDebug()) {
            telemetry.addData("Main Boom Position:", this.mainBoom.getCurrentPosition());
            telemetry.addData("Viper Slide Volts:", this.viperSlide.getVoltage());
        }
    }
}
