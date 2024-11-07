package org.firstinspires.ftc.teamcode.metalheads.compbot;

import org.firstinspires.ftc.teamcode.library.dcmotor.MotorPos;
import org.firstinspires.ftc.teamcode.library.servo.ServoPos;

/**
 *
 */
public class Constants {

    /**
     */
    public static final double INTAKE_PINCHER_OPEN_POS = 0.373;
    public static final double INTAKE_PINCHER_CLOSE_POS = 0.544;

    /**
     */
    public static final double CLAW_PINCHER_OPEN_POS = 0.65;
    public static final double CLAW_PINCHER_CLOSE_POS = 0.484;

    /**
     */
    public static final double INTAKE_V_SERVO_INIT_POS = 0.364;

    /**
     */
    public static final double VIPER_SLIDE_VOLTS_MAX = 1.20;

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
