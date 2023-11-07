package org.firstinspires.ftc.teamcode.library.utility;

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
    Right;

    public Direction invert () {

        switch (this) {
            case FORWARD:
                return REVERSE;
            case REVERSE:
                return FORWARD;
            case LEFT:
                return Right;
            case Right:
                return LEFT;
        }

        return null;
    }
}
