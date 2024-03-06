package org.firstinspires.ftc.teamcode.metalheads.competition.config;

import org.firstinspires.ftc.library.utility.Direction;

/**
 *
 */
public class RobotAutoConfig {

    /**
     */
    public enum ParkPosition {

        /**
         */
        MIDDLE,

        /**
         */
        CORNER,
    }


    /**
     *
     */
    public enum StartPosition {
        NEAR,
        FAR


    }

    /**
     * Distance in CM
     */
    public interface ParkDistFromAprilTags_Blue {

        int fromId1_toCorner  = 52;
        int fromId1_toMiddle  = 68;

        int fromId2_toCorner  = 68;
        int fromId2_toMiddle  = 52;

        int fromId3_toCorner  = 83;
        int fromId3_toMiddle  = 37;
    }

    /**
     * Distance in CM
     */
    public interface ParkDistFromAprilTags_Red {

        int fromId4_toCorner  = 83;
        int fromId4_toMiddle  = 37;

        int fromId5_toCorner  = 68;
        int fromId5_toMiddle  = 52;

        int fromId6_toCorner  = 52;
        int fromId6_toMiddle  = 68;
    }

    /**
     */
    public Direction backdropDirection;

    /**
     */
    public double backboardPlaceTarget = 6.5;

    /**
     */
    public ParkPosition parkPosition;


    /**
     */
    public double placePurplePixelOppositeTrussRotationDegrees = 25;

    /**
     */
    public double placePurplePixelTrussSideRotationDegrees = 35;

    /**
     */
    public int placePurplePixelForwardsDistance = 27;

    /**
     */
    public int placePurplePixelOppositeTrussDistance = 27;

    /**
     */
    public int placePurplePixelTrussSideDistance = 27;

    /**
     *  NEAR / FAR
     */
    public StartPosition startPosition;

    /**
     * LEFT / RIGHT
     */
    public Direction startingTrussDirection;

    /**
     */
    public boolean useBackdrop;

    /**
     */
    public String webCamName = "Webcam 1";

    /**
     */
    public int gotoCornerDistance_Far = 203;

    /**
     */
    public int gotoCornerDistance_NEAR = 203;

    /**
     */
    public int parkPositionForwardsDistance_afterPlacingYellowPixelOnBackdrop = 0;

}
