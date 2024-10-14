package org.firstinspires.ftc.teamcode.metalheads;

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
    public static final CompBot.PositionConstants SAMPLE_PICK_READY = new CompBot.PositionConstants(){
        @Override
        public void setValues() {
            hServoPos = new ServoPos(0.5033);
            vServoPos = new ServoPos(0.5044);
            vSlideVolts = 0.7;
            mainBoomPos = new MotorPos(-703);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SAMPLE_PICK_LEFT_READY = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            mainBoomPos = new MotorPos(-736, 0.5);
            vSlideVolts = 0.694;
            hServoPos = new ServoPos(0.8633);
            vServoPos = new ServoPos(0.4606);
            intakePincherPos = new ServoPos(INTAKE_PINCHER_OPEN_POS, 1);
        }
    };

    /**
     */
    public static final CompBot.PositionConstants SPECIMEN_PLACE_READY_HIGH = new CompBot.PositionConstants() {
        @Override
        public void setValues() {
            hServoPos = new ServoPos(0.425);
            vServoPos = new ServoPos(0.0967);
            vSlideVolts = 0.747;
            mainBoomPos = new MotorPos(1566, 0.75);
            clawRotatorPos = new ServoPos(0.445);
        }
    };




    /**
     * Hidden constructor - make class essentially static
     */
    private Constants() {
    }
}
