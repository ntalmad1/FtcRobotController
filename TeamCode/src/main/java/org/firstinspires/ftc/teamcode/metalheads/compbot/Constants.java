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
    public static final double VIPER_SLIDES_VOLTS_MIN = 0.456;
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
    public static final double CLAW_ROTATOR_0_DEG = 0.016;
    public static final double CLAW_ROTATOR_90_DEG = 0.016;
    public static final double CLAW_ROTATOR_180_DEG = 0.688;
    public static final double CLAW_ROTATOR_MAX_POS = 1;
    public static final double CLAW_ROTATOR_INIT_POS = 0.016;
    public static final double CLAW_ROTATOR_INCREMENT = 0.006;

    /**
     */
    public static final double CLAW_PINCHER_OPEN_POS = 0.294;
    public static final double CLAW_PINCHER_CLOSE_POS = 0.6;
    public static final double CLAW_PINCHER_INIT_POS = 0.52;

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_READY_MIN = new PositionsStruct(){
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_LEFT_READY_MIN = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_RIGHT_READY_MIN = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_READY_MAX = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_LEFT_READY_MAX = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PICK_RIGHT_READY_MAX = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_CARRY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_EXTEND_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PLACE_LOW_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SAMPLE_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    /**
     */
    public static final PositionsStruct SPECIMEN_PICK_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PICK = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_HIGH = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_LOW_READY = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     */
    public static final PositionsStruct SPECIMEN_PLACE_LOW = new PositionsStruct() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-518);
            vSlideVolts = 0.7;
            doubleServosPos = new ServoPos(0.5033);
            middleServoPos = new ServoPos(0.5044);
            clawRotatorPos = new ServoPos(0.0);
            clawPincherPos = new ServoPos(0.0);
        }
    };

    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
