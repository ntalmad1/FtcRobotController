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
    public static final int MAIN_BOOM_SPECIMEN_TEST = 680;

    /**
     */
    public static final double VIPER_SLIDES_VOLTS_INIT = 0.43;
    public static final int VIPER_SLIDES_MIN_TICS = 20;
    public static final int VIPER_SLIDES_MAX_TICS = 3020;
    //public static final double VIPER_SLIDES_VOLTS_MIN = 0.43;
    //public static final double VIPER_SLIDES_VOLTS_MAX = 1.184;
    //public static final double VIPER_SLIDES_VOLTS_MAX = 1.209;
    public static final int VIPER_SLIDES_SPECIMEN_TEST = 680;
    public static final int VIPER_SLIDES_SCALE = 200;
    /**
     */
    public static final double DOUBLE_SERVOS_MIN_POS = 0;
    public static final double DOUBLE_SERVOS_MAX_POS = 1;
    public static final double DOUBLE_SERVOS_INIT_POS = 0.98;
    public static final double DOUBLE_SERVOS_SPECIMEN_TEST = 0.006;
    public static final double DOUBLE_SERVOS_INCREMENT = 0.006;

    /**
     */
    public static final double MIDDLE_SERVO_MIN_POS = 0;
    public static final double MIDDLE_SERVO_MAX_POS = 0.783;
    public static final double MIDDLE_SERVO_INIT_POS = 0.783;
    public static final double MIDDLE_SERVO_INCREMENT = 0.006;
    public static final double MIDDLE_SERVO_SPECIMEN_PLACED = 0.173;
    public static final double MIDDLE_SERVO_SPECIMEN_TEST = 0;

    /**
     */
    public static final double CLAW_ROTATOR_MIN_POS = 0;
    public static final double CLAW_ROTATOR_0_DEG = 0;
    public static final double CLAW_ROTATOR_90_DEG = 0.3;
    public static final double CLAW_ROTATOR_180_DEG = 0.64;
    public static final double CLAW_ROTATOR_MAX_POS = 1;
    public static final double CLAW_ROTATOR_INIT_POS = 0.64;
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
            vSlidePos = new MotorPos(230);
            doubleServosPos = new ServoPos(0.637);
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
            doubleServosPos = new ServoPos(0.553);
            middleServoPos = new ServoPos(0.753);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_UP = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MIN_TICS);
            doubleServosPos = new ServoPos(0.728);
            middleServoPos = new ServoPos(0.783);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(Constants.MAIN_BOOM_MAX_TICS);
            vSlidePos = new MotorPos(Constants.VIPER_SLIDES_MAX_TICS);
            doubleServosPos = new ServoPos(0.44);
            middleServoPos = new ServoPos(0.023);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_180_DEG);
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
            vSlidePos = new MotorPos(0);
            doubleServosPos = new ServoPos(0.963);
            middleServoPos = new ServoPos(0.408);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_180_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            // was 526
            mainBoomPos = new MotorPos(536);
            vSlidePos = new MotorPos(1500);
            doubleServosPos = new ServoPos(0.260);
            middleServoPos = new ServoPos(0.0);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_0_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_CLOSE_POS);
        }
    };

    /**
     */
    public static final PositionsStruct HANG_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(669);
            vSlidePos = new MotorPos(1920);
            doubleServosPos = new ServoPos(0.9);
            middleServoPos = new ServoPos(0.783);
            clawRotatorPos = new ServoPos(Constants.CLAW_ROTATOR_0_DEG);
            clawPincherPos = new ServoPos(Constants.CLAW_PINCHER_OPEN_POS);
        }
    };

    /**
     */
    public static final PositionsStruct HANG = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(863);
            vSlidePos = new MotorPos(30);
        }
    };



    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
