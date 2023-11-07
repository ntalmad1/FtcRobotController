package org.firstinspires.ftc.teamcode.metalheads;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.arm.Arm;
import org.firstinspires.ftc.library.arm.ArmConfig;
import org.firstinspires.ftc.library.boom.BoomConfig;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;

@TeleOp(name="ArmWithDriveTrain", group="Linear OpMode")
@Disabled
public class ArmWithDriveTrain extends IsaacBot{

    /**
     */
    private Arm arm;

    /**
     */
    private MecanumDriveTrain driveTrain;

    public ArmWithDriveTrain () {
        super();

        BoomConfig topBoomConfig = new BoomConfig();
        topBoomConfig.robot = this;
        topBoomConfig.servoName = "topServo";
        topBoomConfig.direction = Servo.Direction.FORWARD;
        topBoomConfig.controllerInputMethod = Control.Gp2_RightStickY;
        topBoomConfig.invertInput = true;
        topBoomConfig.zeroDegreePosition = 0.586;
        topBoomConfig.maxIncrement = 0.005;

        BoomConfig midBoomConfig = new BoomConfig();
        midBoomConfig.robot = this;
        midBoomConfig.servoName = "middleServo";
        midBoomConfig.direction = Servo.Direction.REVERSE;
        midBoomConfig.controllerInputMethod = Control.Gp2_RightStickX;
        midBoomConfig.invertInput = false;
        midBoomConfig.maxIncrement = 0.002;
        midBoomConfig.zeroDegreePosition = 0.575;

        BoomConfig bottomBoomConfig = new BoomConfig();
        bottomBoomConfig.robot = this;
        bottomBoomConfig.servoName = "bottomLeftServo";
        bottomBoomConfig.isDualServo = true;
        bottomBoomConfig.secondaryServoName = "bottomRightServo";
        bottomBoomConfig.direction = Servo.Direction.REVERSE;
        bottomBoomConfig.controllerInputMethod = Control.Gp2_LeftStickX;
        bottomBoomConfig.invertInput = true;
        bottomBoomConfig.maxIncrement = 0.005;
        bottomBoomConfig.zeroDegreePosition = 0.575;

        ArmConfig armConfig = new ArmConfig();
        armConfig.robot = this;
        armConfig.midBoomConfig = midBoomConfig;
        armConfig.bottomBoomConfig = bottomBoomConfig;

        this.arm = new Arm(armConfig);

        MecanumDriveTrainConfig driveTrainConfig = new MecanumDriveTrainConfig();
        driveTrainConfig.robot = this;
        driveTrainConfig.leftFrontDeviceName   = "leftFrontDrive";
        driveTrainConfig.rightFrontDeviceName  = "rightFrontDrive";
        driveTrainConfig.rightRearDeviceName   = "rightRearDrive";
        driveTrainConfig.leftRearDeviceName    = "leftRearDrive";
        driveTrainConfig.accelerationIncrement = 0.02;
        driveTrainConfig.maxPower = 0.65;
        driveTrainConfig.maxPower = 0;
        driveTrainConfig.yawOffset = -90;
        driveTrainConfig.incrementalDeceleration = true;
        driveTrainConfig.leftFrontMotorDirection  = DcMotorSimple.Direction.REVERSE;
        driveTrainConfig.rightFrontMotorDirection = DcMotorSimple.Direction.FORWARD;
        driveTrainConfig.leftRearMotorDirection  =  DcMotorSimple.Direction.REVERSE;
        driveTrainConfig.rightRearMotorDirection  = DcMotorSimple.Direction.FORWARD;
        driveTrainConfig.imuName = "imu";
        driveTrainConfig.debug = true;

        this.driveTrain = new MecanumDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        this.driveTrain.init();
        this.arm.init();

        this.waitForStart();

        while (this.opModeIsActive()) {
            this.driveTrain.run();
            this.arm.run();
        }
    }
}
