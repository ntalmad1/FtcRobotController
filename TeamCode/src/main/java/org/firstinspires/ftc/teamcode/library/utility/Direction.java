package org.firstinspires.ftc.teamcode.library.utility;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 *
 */
public enum Direction {

    /**
     *
     */
    FORWARD,

    /**
     *
     */
    REVERSE,

    /**
     *
     */
    LEFT,

    /**
     *
     */
    RIGHT;

    /**
     *
     * @return
     */
    public Direction invert () {

        switch (this) {
            case FORWARD:
                return REVERSE;
            case REVERSE:
                return FORWARD;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }

        return null;
    }

    /**
     *
     * @param direction
     * @return
     */
    public static DcMotor.Direction invert (DcMotor.Direction direction) {
        switch (direction) {
            case FORWARD:
                return DcMotor.Direction.REVERSE;
            case REVERSE:
                return DcMotor.Direction.FORWARD;

        }

        return null;
    }
}
