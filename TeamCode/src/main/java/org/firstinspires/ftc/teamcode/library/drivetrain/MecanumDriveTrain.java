package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.library.utility.GridUtils;
import org.firstinspires.ftc.teamcode.library.utility.Point;

/**
 *
 */
public class MecanumDriveTrain extends AbstractDriveTrain
{
    /**
     */
    private double maxPower;

    private boolean leftBumperFlag;
    private boolean rightBumperFlag;

    private ElapsedTime leftBumperRuntime = new ElapsedTime();
    private ElapsedTime rightBumperRuntime = new ElapsedTime();
    private int bumperTimeout = 250;

    /**
     *
     */

    /**
     * Constructor
     *
     * @param config
     */
    public MecanumDriveTrain(MecanumDriveTrainConfiguration config)
    {
        super(config);

        this.maxPower = config.maxPower;
    }

    /**
     *
     */
    public void init ()
    {
        super.init();

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftFrontMotor.setDirection(this.getConfig().leftFrontMotorDirection);
        rightFrontMotor.setDirection(this.getConfig().rightFrontMotorDirection);
        rightRearMotor.setDirection(this.getConfig().rightRearMotorDirection);
        leftRearMotor.setDirection(this.getConfig().leftRearMotorDirection);
    }

    public void run ()
    {
        double yaw = -(this.robot.getYaw() + getConfig().yawOffset);

        float leftY = -this.robot.gamepad1.left_stick_y;
        float leftX = this.robot.gamepad1.left_stick_x;
        float rx = this.robot.gamepad1.right_stick_x;

        Point newPoint = GridUtils.rotatePointByDegrees(leftX,leftY,yaw);
        double x = newPoint.getX();
        double y = newPoint.getY();

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower   = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower  = (y + x - rx) / denominator;

//        double r = Math.hypot(x,y);
//        double robotAngle = Math.atan2(y,x) - Math.PI / 4;
//        double rightX = rx;
//        final double v1 = r * Math.cos(robotAngle) + rightX;
//        final double v2 = r * Math.sin(robotAngle) - rightX;
//        final double v3 = r * Math.sin(robotAngle) + rightX;
//        final double v4 = r * Math.cos(robotAngle) - rightX;

//        leftFront.setPower(v1);
//        rightFront.setPower(v2);
//        leftRear.setPower(v3)
//        rightRear.setPower(v4);

        // button controls
        if (robot.gamepad1.b) {
            //resetIMU
            this.robot.initImu(this.getConfig().imuName);
        }

        if (!robot.gamepad1.left_bumper || this.leftBumperRuntime.milliseconds() > this.bumperTimeout)
        {
            this.leftBumperFlag = false;
        }

        if (robot.gamepad1.left_bumper)
        {
            if (!this.leftBumperFlag) {
                leftBumperRuntime.reset();

                double power = this.maxPower - 0.1;
                if (power < 0.1) {
                    power = 0.1;
                }
                this.maxPower = power;
            }
            this.leftBumperFlag = true;
        }

        if (!robot.gamepad1.right_bumper || rightBumperRuntime.milliseconds() > this.bumperTimeout)
        {
            this.rightBumperFlag = false;
        }

        if (robot.gamepad1.right_bumper)
        {
            if (!this.rightBumperFlag) {
                rightBumperRuntime.reset();

                double power = this.maxPower + 0.1;
                if (power > 1) {
                    power = 1;
                }
                this.maxPower = power;
            }
            this.rightBumperFlag = true;
        }

        frontLeftPower  = accelerate(frontLeftPower, this.leftFrontMotor);
        frontRightPower = accelerate(frontRightPower, this.rightFrontMotor);
        backRightPower  = accelerate(backRightPower, this.rightRearMotor);
        backLeftPower   = accelerate(backLeftPower, this.leftRearMotor);

        this.leftFrontMotor.setPower(frontLeftPower);
        this.rightFrontMotor.setPower(frontRightPower);
        this.rightRearMotor.setPower(backRightPower);
        this.leftRearMotor.setPower(backLeftPower);

        if (this.getConfig().isDebug())
        {
            this.robot.telemetry.addData("Rotated Y: ", "%.2f", y);
            this.robot.telemetry.addData("Rotated X: ", "%.2f", x);
            this.robot.telemetry.addData("Right X: ", "%.2f", rx);
            this.robot.telemetry.addData("Yaw: ", "%.2f", yaw);
            this.robot.telemetry.addData("frontLeftPower: ", "%.2f", frontLeftPower);
            this.robot.telemetry.addData("backLeftPower: ", "%.2f", backLeftPower);
            this.robot.telemetry.addData("frontRightPower: ", "%.2f", frontRightPower);
            this.robot.telemetry.addData("backRightPower: ", "%.2f", backRightPower);
            this.robot.telemetry.addData("Max Power: ", "%.2f", this.maxPower);
            this.robot.telemetry.addData("Left Bumper Timer: ", "%.2f", this.leftBumperRuntime.milliseconds());
            this.robot.telemetry.update();
        }
    }


    /**
     *
     * @return
     */
    protected MecanumDriveTrainConfiguration getConfig() {
        return (MecanumDriveTrainConfiguration)super.getConfig();
    }

    /**
     *
     * @param targetPower
     * @param motor
     * @return
     */
    private double accelerate (double targetPower, DcMotor motor)
    {
        if (!this.getConfig().incrementalDeceleration && targetPower == 0)
        {
            return 0;
        }
        
        double currentPower = motor.getPower();
        double newpower = targetPower;
        if (currentPower < targetPower)
        {
            double power = currentPower + this.getConfig().accelerationIncrement;
            if (power > targetPower)
            {
                power = targetPower;
            }

            newpower = power;
        }
        else if (currentPower > targetPower)
        {
            double power = currentPower - this.getConfig().accelerationIncrement;
            if (power < targetPower)
            {
                power = targetPower;
            }

            newpower = power;
        }
        if (Math.abs(newpower) > this.maxPower)
        {
            if (newpower < 0)
            {
                newpower = this.maxPower * -1;
            }
            else
            {
                newpower = this.maxPower;
            }
        }
        return newpower;

    }
}
