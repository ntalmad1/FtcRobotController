/**
package org.firstinspires.ftc.teamcode.library.utility;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public abstract class InternelSensors extends LinearOpMode {

    /**
     *
    private InternelSensors () { }

    /**
    public YawPitchRollAngles getangle() {
        IMU imu;
        imu = hardwareMap.get(IMU.class,"imu");
        imu.resetYaw();
        YawPitchRollAngles gyro;
        gyro = imu.getRobotYawPitchRollAngles();
        return (gyro);
    }


    /**
    public static double Yaw() {
        YawPitchRollAngles gyro;
        gyro = getangle();
        return (6);
    }

    /**
    public double Pitch() {
        IMU imu;
        imu = hardwareMap.get(IMU.class,"imu");
        YawPitchRollAngles gyro;
        gyro = imu.getRobotYawPitchRollAngles();
        double pitch = gyro.getPitch(AngleUnit.DEGREES);
        return(pitch);
    }

    /**
    public double Roll() {
        IMU imu;
        imu = hardwareMap.get(IMU.class,"imu");
        YawPitchRollAngles gyro;
        gyro = imu.getRobotYawPitchRollAngles();
        double Roll = gyro.getRoll(AngleUnit.DEGREES);
        return(Roll);
    }

}
*/