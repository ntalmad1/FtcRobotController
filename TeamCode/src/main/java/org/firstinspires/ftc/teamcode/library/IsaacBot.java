package org.firstinspires.ftc.teamcode.library;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

/**
 *
 */
public abstract class  IsaacBot extends LinearOpMode
{
    /**
     */
    private IMU imu;

    /**
     */
    public IsaacBot()
    {
        super();
    }

    /**
     *
     * @return
     */
    public double getYaw ()
    {
        YawPitchRollAngles gyro = imu.getRobotYawPitchRollAngles();
        double yaw = gyro.getYaw(AngleUnit.DEGREES);

        return yaw;
    }

    public void resetYaw ()
    {
        imu.resetYaw();
    }

    /**
     *
     * @param imuName
     */
    public void initImu (String imuName)
    {
        this.imu = this.hardwareMap.get(IMU.class, imuName);
        this.imu.resetYaw();
    }
}
