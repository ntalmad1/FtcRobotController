package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.RoadrunnerDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.BigArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.LittleArmConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public BigArmConfig bigArmConfig;

    /**
     */
    public LittleArmConfig littleArmConfig;

    /**
     */
    public RoadrunnerDriveTrainConfig driveTrainConfig;

    /**
     */
    public boolean useDriveTrain = true;

    /**
     */
    public boolean useBigArm = true;

    /**
     */
    public boolean useLittleArm = true;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugDriveTrain = false;

    /**
     */
    public boolean debugBigArm = false;

    /**
     */
    public boolean debugLittleArm = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        // driveTrain
        this.configureDriveTrain(robot);

        // big arm
        this.configureBigArm(robot);

        // little arm
        this.configureLittleArm(robot);
    }

    /**
     *
     * @param robot
     */
    private void configureBigArm(IsaacBot robot) {
        this.bigArmConfig = new BigArmConfig(robot);
        this.bigArmConfig.viperSlideConfig = new PotentiometerMotorConfig(robot);
        this.bigArmConfig.viperSlideConfig.motorName = "viperSlide";
        this.bigArmConfig.viperSlideConfig.minTics = 100;
        this.bigArmConfig.viperSlideConfig.maxTics = 2900;
        this.bigArmConfig.viperSlideConfig.minVolts = 0.586;
        this.bigArmConfig.viperSlideConfig.maxVolts = Constants.VIPER_SLIDE_VOLTS_MAX;
        this.bigArmConfig.viperSlideConfig.brakeOn = true;

        this.bigArmConfig.viperSlideConfig.potentiometerConfig = new PotentiometerConfig(robot);
        this.bigArmConfig.viperSlideConfig.potentiometerConfig.potentiometerName = "pot";

        this.bigArmConfig.mainBoomConfig = new EncodedMotorConfig(robot);
        this.bigArmConfig.mainBoomConfig.motorName = "arm";
        this.bigArmConfig.mainBoomConfig.minTics = -736;
        this.bigArmConfig.mainBoomConfig.maxTics = 5000;
    }

    /**
     *
     * @param robot
     */
    private void configureLittleArm(IsaacBot robot) {
        this.littleArmConfig = new LittleArmConfig(robot);

        this.littleArmConfig.doubleServosConfig = new RotatorConfig(robot);
        this.littleArmConfig.doubleServosConfig.isDualServo = true;
        this.littleArmConfig.doubleServosConfig.servoName = "leftActuatorArm";
        this.littleArmConfig.doubleServosConfig.homePosition = 0;
        this.littleArmConfig.doubleServosConfig.minPosition = 0;
        this.littleArmConfig.doubleServosConfig.maxPosition = 0.336;
        this.littleArmConfig.doubleServosConfig.zeroDegreePosition = 0;
        this.littleArmConfig.doubleServosConfig.secondaryServoName = "rightActuatorArm";

        this.littleArmConfig.middleServoConfig = new RotatorConfig(robot);
        this.littleArmConfig.middleServoConfig.servoName = "clawRotate";
        this.littleArmConfig.middleServoConfig.homePosition = 0;
        this.littleArmConfig.middleServoConfig.zeroDegreePosition = 0;
        this.littleArmConfig.middleServoConfig.minPosition = 0;
        this.littleArmConfig.middleServoConfig.maxPosition = 1;
        this.littleArmConfig.middleServoConfig.maxIncrement = 0.010;

        this.littleArmConfig.clawRotatorConfig = new RotatorConfig(robot);
        this.littleArmConfig.clawRotatorConfig.servoName = "clawRotate";
        this.littleArmConfig.clawRotatorConfig.homePosition = 0;
        this.littleArmConfig.clawRotatorConfig.zeroDegreePosition = 0;
        this.littleArmConfig.clawRotatorConfig.minPosition = 0;
        this.littleArmConfig.clawRotatorConfig.maxPosition = 1;
        this.littleArmConfig.clawRotatorConfig.maxIncrement = 0.010;

        this.littleArmConfig.clawPincherConfig = new ServoComponentConfig(robot);
        this.littleArmConfig.clawPincherConfig.servoName = "pincherServo";
        this.littleArmConfig.clawPincherConfig.homePosition = 0.484;
        this.littleArmConfig.clawPincherConfig.zeroDegreePosition = 0.5;
        this.littleArmConfig.clawPincherConfig.minPosition = 0.484;
        this.littleArmConfig.clawPincherConfig.maxPosition = 0.65;
    }

    /**
     *
     * @param robot
     */
    private void configureDriveTrain(IsaacBot robot) {
        this.driveTrainConfig = new RoadrunnerDriveTrainConfig(robot);
        this.driveTrainConfig.yawOffset = 0;
        this.driveTrainConfig.imuName = "imuExternal";
    }

}
