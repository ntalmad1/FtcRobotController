package org.firstinspires.ftc.library.utility;

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
}
