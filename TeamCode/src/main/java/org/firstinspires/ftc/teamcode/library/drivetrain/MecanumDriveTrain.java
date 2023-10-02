package org.firstinspires.ftc.teamcode.library.drivetrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.library.utility.GridUtils;
import org.firstinspires.ftc.teamcode.library.utility.Point;
import org.opencv.core.Mat;

/**
 *
 */
public class MecanumDriveTrain extends AbstractDriveTrain
{
    /**
     * Constructor
     *
     * @param config
     */
    public MecanumDriveTrain(MecanumDriveTrainConfiguration config) {
        super(config);
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
        double yaw = this.robot.getYaw();

        float leftY = this.robot.gamepad1.left_stick_y * (float) -1;
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
            this.robot.telemetry.addData("Left X: ", "%.2f", leftX);
            this.robot.telemetry.addData("Left Y: ", "%.2f", leftY);
            this.robot.telemetry.addData("Right X: ", "%.2f", rx);
            this.robot.telemetry.addData("Rotated X: ", "%.2f", x);
            this.robot.telemetry.addData("Rotated Y: ", "%.2f", y);
            this.robot.telemetry.addData("frontLeftPower: ", "%.2f", frontLeftPower);
            this.robot.telemetry.addData("backLeftPower: ", "%.2f", backLeftPower);
            this.robot.telemetry.addData("frontRightPower: ", "%.2f", frontRightPower);
            this.robot.telemetry.addData("backRightPower: ", "%.2f", backRightPower);
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
        if (Math.abs(newpower) > getConfig().maxpower)
        {
            if (newpower < 0)
            {
                newpower = getConfig().maxpower * -1;
            }
            else
            {
                newpower = getConfig().maxpower;
            }
        }
        return newpower;

    }
}
