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
    public interface ParkDistFromAprilTags {

        int fromId1_toCorner  = 52;
        int fromId1_toMiddle  = 68;

        int fromId2_toCenter  = 68;
        int fromId2_toMiddle  = 52;

        int fromId3_toCenter  = 83;
        int fromId3_toMiddle  = 37;
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

}
