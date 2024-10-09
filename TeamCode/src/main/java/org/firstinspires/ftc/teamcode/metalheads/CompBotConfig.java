package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.archive.competition.config.MecanumDriveCompConfig;
import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.continuousservo.ContinuousServoConfig;
import org.firstinspires.ftc.teamcode.library.drivetrain.MecanumDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotorConfig;
import org.firstinspires.ftc.teamcode.library.potentiometer.PotentiometerConfig;
import org.firstinspires.ftc.teamcode.library.potentiometermotor.PotentiometerMotorConfig;
import org.firstinspires.ftc.teamcode.library.rotator.RotatorConfig;
import org.firstinspires.ftc.teamcode.library.servo.ServoComponentConfig;
import org.firstinspires.ftc.teamcode.library.utility.Control;
import org.firstinspires.ftc.teamcode.metalheads.components.ArmConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.ClawConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.DoubleHooksConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.FlapperBarsConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.IntakeConfig;
import org.firstinspires.ftc.teamcode.metalheads.components.WinchConfig;

/**
 *
 */
public class CompBotConfig {

    /**
     */
    public IsaacBot robot;

    /**
     */
    public ArmConfig armConfig;

    /**
     */
    public ClawConfig clawConfig;

    /**
     */
    public DoubleHooksConfig doubleHooksConfig;

    /**
     */
    public FlapperBarsConfig flapperBarsConfig;

    /**
     */
    public IntakeConfig intakeConfig;

    /**
     */
    public MecanumDriveTrainConfig driveTrainConfig;

    /**
     */
    public WinchConfig winchConfig;

    /**
     */
    public boolean debugAll = false;

    /**
     */
    public boolean debugDriveTrain = false;

    /**
     */
    public boolean debugArm = false;

    /**
     */
    public boolean debugClaw = false;

    /**
     */
    public boolean debugDoubleHooks = false;

    /**
     */
    public boolean debugFlapperBars = false;

    /**
     */
    public boolean debugIntake = false;

    /**
     */
    public boolean debugWinch = false;

    /**
     * Constructor
     *
     * @param robot
     */
    public CompBotConfig(IsaacBot robot) {
        this.robot = robot;

        // driveTrain
        this.driveTrainConfig = new MecanumDriveTrainConfig(robot);
        this.driveTrainConfig.leftFrontDeviceName   = "leftFront";
        this.driveTrainConfig.rightFrontDeviceName  = "rightFront";
        this.driveTrainConfig.rightRearDeviceName   = "rightRear";
        this.driveTrainConfig.leftRearDeviceName    = "leftRear";
        this.driveTrainConfig.accelerationIncrement = 1;
        this.driveTrainConfig.maxPower = 1;
        this.driveTrainConfig.yawOffset = 0;
        this.driveTrainConfig.incrementalDeceleration = false;
        this.driveTrainConfig.leftFrontMotorDirection  = DcMotorSimple.Direction.REVERSE;
        this.driveTrainConfig.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        this.driveTrainConfig.leftRearMotorDirection  =  DcMotorSimple.Direction.REVERSE;
        this.driveTrainConfig.rightRearMotorDirection  = DcMotorSimple.Direction.FORWARD;
        this.driveTrainConfig.imuName = "imuExternal";

        // arm
        this.armConfig = new ArmConfig(robot);
        this.armConfig.viperSlideConfig = new PotentiometerMotorConfig(robot);
        this.armConfig.viperSlideConfig.motorName = "viperSlide";
        this.armConfig.viperSlideConfig.minTics = 100;
        this.armConfig.viperSlideConfig.maxTics = 2900;
        this.armConfig.viperSlideConfig.minVolts = 0.586;
        this.armConfig.viperSlideConfig.maxVolts = 1.129;
        this.armConfig.viperSlideConfig.brakeOn = true;
        this.armConfig.viperSlideConfig.control = Control.Gp2_RightStickX;

        this.armConfig.viperSlideConfig.potentiometerConfig = new PotentiometerConfig(robot);
        this.armConfig.viperSlideConfig.potentiometerConfig.potentiometerName = "pot";

        this.armConfig.mainBoomConfig = new EncodedMotorConfig(robot);
        this.armConfig.mainBoomConfig.motorName = "arm";
        this.armConfig.mainBoomConfig.minTics = -736;
        this.armConfig.mainBoomConfig.maxTics = 5000;
        this.armConfig.mainBoomConfig.control = Control.Gp2_LeftStickY;



        // claw
        this.clawConfig = new ClawConfig(robot);
        this.clawConfig.pincherConfig = new ServoComponentConfig(robot);
        this.clawConfig.pincherConfig.servoName = "pincherServo";
        this.clawConfig.pincherConfig.homePosition = 0.5;
        this.clawConfig.pincherConfig.zeroDegreePosition = 0.5;
        this.clawConfig.pincherConfig.minPosition = 0.484;
        this.clawConfig.pincherConfig.maxPosition = 0.65;
        //this.clawConfig.pincherConfig.controllerInputMethod = Control.Gp1_RightStickX;

        this.clawConfig.clawRotatorConfig = new RotatorConfig(robot);
        this.clawConfig.clawRotatorConfig.servoName = "clawRotate";
        this.clawConfig.clawRotatorConfig.homePosition = 0;
        this.clawConfig.clawRotatorConfig.zeroDegreePosition = 0;
        this.clawConfig.clawRotatorConfig.minPosition = 0;
        this.clawConfig.clawRotatorConfig.maxPosition = 1;
        //this.clawConfig.clawRotatorConfig.controllerInputMethod = Control.Gp1_RightStickY;

        // double hooks
        this.doubleHooksConfig = new DoubleHooksConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig = new EncodedMotorConfig(robot);
        this.doubleHooksConfig.linearActuatorConfig.motorName = "linearActuator";
        this.doubleHooksConfig.linearActuatorConfig.minTics = 0;
        this.doubleHooksConfig.linearActuatorConfig.maxTics = 8574;
        this.doubleHooksConfig.linearActuatorConfig.control = Control.Gp2_Dpad_UpDown;

        this.doubleHooksConfig.doubleServosConfig = new ServoComponentConfig(robot);
        this.doubleHooksConfig.doubleServosConfig.isDualServo = true;
        this.doubleHooksConfig.doubleServosConfig.servoName = "leftActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.homePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.minPosition = 0;
        this.doubleHooksConfig.doubleServosConfig.maxPosition = 0.336;
        this.doubleHooksConfig.doubleServosConfig.zeroDegreePosition = 0;
        this.doubleHooksConfig.doubleServosConfig.secondaryServoName = "rightActuatorArm";
        this.doubleHooksConfig.doubleServosConfig.secondaryServoOffset = 1 - 0.979;
        this.doubleHooksConfig.doubleServosConfig.controllerInputMethod = Control.Gp2_Dpad_Left;
        this.doubleHooksConfig.doubleServosConfig.controllerInputMethod2 = Control.Gp2_Dpad_Right;

        // flapper bars
        this.flapperBarsConfig = new FlapperBarsConfig(robot);
        this.flapperBarsConfig.isDualServo = true;
        this.flapperBarsConfig.servoName = "leftFlapper";
        this.flapperBarsConfig.zeroDegreePosition = 0;
        this.flapperBarsConfig.homePosition = 0;
        this.flapperBarsConfig.minPosition = 0;
        this.flapperBarsConfig.maxPosition = 1;
        this.flapperBarsConfig.secondaryServoName = "rightFlapper";
        this.flapperBarsConfig.maxIncrement = 0.001;

        // intake
        this.configureIntake();

        // winch
        this.winchConfig = new WinchConfig(robot);
        this.winchConfig.motorName = "winch";
        this.winchConfig.minTics = -90000;
        this.winchConfig.maxTics = 90000;
    }

    /**
     *
     */
    private void configureIntake() {

        this.intakeConfig = new IntakeConfig(robot);
        this.intakeConfig.pincherConfig = new ServoComponentConfig(robot);
        this.intakeConfig.pincherConfig.servoName = "intake";
        this.intakeConfig.pincherConfig.homePosition = 0.5;
        this.intakeConfig.pincherConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.pincherConfig.minPosition = Constants.INTAKE_PINCHER_OPEN_POS;
        this.intakeConfig.pincherConfig.maxPosition = Constants.INTAKE_PINCHER_CLOSE_POS;

        this.intakeConfig.hServoConfig = new RotatorConfig(robot);
        this.intakeConfig.hServoConfig.servoName = "intakeHorizontal";
        this.intakeConfig.hServoConfig.controllerInputMethod = Control.Gp1_LeftStickX;
        this.intakeConfig.hServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.hServoConfig.homePosition = 0.5011;
        this.intakeConfig.hServoConfig.minPosition = 0;
        this.intakeConfig.hServoConfig.maxPosition = 1;

        this.intakeConfig.vServoConfig = new RotatorConfig(robot);
        this.intakeConfig.vServoConfig.servoName = "intakeVertical";
        this.intakeConfig.vServoConfig.controllerInputMethod = Control.Gp1_LeftStickY;
        this.intakeConfig.vServoConfig.zeroDegreePosition = 0.5;
        this.intakeConfig.vServoConfig.homePosition = Constants.INTAKE_V_SERVO_INIT_POS;
        this.intakeConfig.vServoConfig.minPosition = 0.233;
        this.intakeConfig.vServoConfig.maxPosition = 0.674;


    }
}
