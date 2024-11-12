package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public class Constants {

    /**
     */
    public static final int MAIN_BOOM_MIN_TICS = 0;
    public static final int MAIN_BOOM_MAX_TICS = 1155;
    public static final int MAIN_BOOM_INIT_TICS = 525;
    public static final int MAIN_BOOM_SCALE = 200;

    /**
     */
    public static final double VIPER_SLIDES_VOLTS_MIN = 0.48;
    public static final double VIPER_SLIDES_VOLTS_MAX = 1.284;
    public static final int VIPER_SLIDES_SCALE = 200;
    /**
     */
    public static final double DOUBLE_SERVOS_MIN_POS = 0;
    public static final double DOUBLE_SERVOS_MAX_POS = 0.9;
    public static final double DOUBLE_SERVOS_INIT_POS = 0.9;
    public static final double DOUBLE_SERVOS_INCREMENT = 0.006;

    /**
     */
    public static final double MIDDLE_SERVO_MIN_POS = 0;
    public static final double MIDDLE_SERVO_MAX_POS = 0.783;
    public static final double MIDDLE_SERVO_INIT_POS = 0.783;
    public static final double MIDDLE_SERVO_INCREMENT = 0.006;

    /**
     */
    public static final double CLAW_ROTATOR_MIN_POS = 0;
    public static final double CLAW_ROTATOR_0_DEG = 0;
    public static final double CLAW_ROTATOR_90_DEG = 0.3;
    public static final double CLAW_ROTATOR_180_DEG = 0.64;
    public static final double CLAW_ROTATOR_MAX_POS = 1;
    public static final double CLAW_ROTATOR_INIT_POS = 0;
    public static final double CLAW_ROTATOR_INCREMENT = 0.006;

    /**
     */
    public static final double CLAW_PINCHER_OPEN_POS = 0.294;
    public static final double CLAW_PINCHER_CLOSE_POS = 0.556;
    public static final double CLAW_PINCHER_INIT_POS = 0.556;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_READY = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            vSlideVolts = 0.488;
            doubleServosPos = new ServoPos(0.617);
            middleServoPos = new ServoPos(0.783);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_180_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_DOWN = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            doubleServosPos = new ServoPos(0.543);
            middleServoPos = new ServoPos(0.733);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_UP = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            doubleServosPos = new ServoPos(0.728);
            middleServoPos = new ServoPos(0.78);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MAX_TICS);
            vSlideVolts = Constants.VIPER_SLIDES_VOLTS_MAX;
            doubleServosPos = new ServoPos(0.493);
            middleServoPos = new ServoPos(0.023);
            clawRotatorPos = new ServoPos(0.68);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SPECIMEN_PICK_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MAX_TICS);
            vSlideVolts = Constants.VIPER_SLIDES_VOLTS_MIN;
            doubleServosPos = new ServoPos(0.9);
            middleServoPos = new ServoPos(0.416);
            clawRotatorPos = new ServoPos(0.689);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(526);
            vSlideVolts = 0.787;
            doubleServosPos = new ServoPos(0.32);
            middleServoPos = new ServoPos(0.0);
            clawRotatorPos = new ServoPos(0.016);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };

    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
