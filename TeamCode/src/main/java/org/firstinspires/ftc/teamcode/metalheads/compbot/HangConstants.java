package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public class HangConstants {

    /**
     *
     */
    public static final CompBot.HangPositionContants HANG_READY = new CompBot.HangPositionContants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(2266, 1);
            vSlideVolts = 0.887;
            clawRotatorPos = new ServoPos(0.0, 1);
            hServoPos = new ServoPos(0.5, 1);
            vServoPos = new ServoPos(0.3639, 1);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS, 1);
            intakePincherPos = new ServoPos(Constants.INTAKE_PINCHER_CLOSE_POS, 1);
            linearActuatorPos = new MotorPos(8578);
            dHookServos = new ServoPos(0.1233, 1);
        }
    };

    /**
     *
     */
    public static final CompBot.HangPositionContants HANG_READY_1 = new CompBot.HangPositionContants() {
        @Override
        public void setValues() {
            vSlideVolts = 0.900;
            mainBoomPos = new MotorPos(1274, 1);
        }
    };

    /**
     *
     */
    public static final CompBot.HangPositionContants HANG_READY_2 = new CompBot.HangPositionContants() {
        @Override
        public void setValues() {
             vSlideVolts = 0.700;
             dHookServos = new ServoPos(0.140);
        }
    };

    public static final CompBot.HangPositionContants HANG_READY_3 = new CompBot.HangPositionContants() {
        @Override
        public void setValues() {
            linearActuatorPos = new MotorPos(1000, 1);
            dHookServos = new ServoPos(0.11);
        }
    };

    /**
     *
     */
    public static final CompBot.HangPositionContants HANG_READY_4 = new CompBot.HangPositionContants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(2106, 1);
            vSlideVolts = 0.638;
            dHookServos = new ServoPos(0.0233);
            linearActuatorPos = new MotorPos(92, 1);
        }
    };



    /**
     * Hidden Contructor
     *
     */
    private HangConstants() {
    }
}
