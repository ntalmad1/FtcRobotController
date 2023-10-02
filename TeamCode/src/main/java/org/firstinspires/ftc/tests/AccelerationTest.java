package org.firstinspires.ftc.tests;

import com.qualcomm.robotcore.hardware.DcMotor;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AccelerationTest
{
    @Test
    public void testAccelerationWorks()
    {
        double power = this.accelerate(-1, -0.02);

        assertEquals(-0.04, power, 2);
    }

    public double accelerate (double targetPower, double currentPower)
    {
        if (currentPower < targetPower)
        {
            double power = currentPower + 0.02;
            if (power > targetPower)
            {
                power = targetPower;
            }

            return power;
        }
        else if (currentPower > targetPower)
        {
            double power = currentPower - 0.02;
            if (power < targetPower)
            {
                power = targetPower;
            }

            return power;
        }
        else
        {
            return targetPower;
        }

    }
}
