package org.firstinspires.ftc.teamcode.metalheads.components;


import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.TouchSensor;

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

        RevTouchSensor touchSensor = this.robot.hardwareMap.get(RevTouchSensor.class, this.config.viperSlidesTouchSensorName);

        this.viperSlide.setTouchSensor(touchSensor);

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
            //telemetry.addData("Viper Slide Volts:", this.viperSlide.getVoltage());
            telemetry.addData("Viper Slide Position:", this.viperSlide.getCurrentPosition());
            telemetry.addData("Touch Sensor: ", this.viperSlide.getTouchSensor().isPressed());
        }
    }
}
