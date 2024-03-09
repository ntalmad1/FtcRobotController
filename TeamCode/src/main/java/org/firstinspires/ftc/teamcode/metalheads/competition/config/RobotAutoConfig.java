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
        int fromId1_toMiddle  = 88;

        int fromId2_toCorner  = 68;
        int fromId2_toMiddle  = 72;

        int fromId3_toCorner  = 83;
        int fromId3_toMiddle  = 57;
    }

    /**
     * Distance in CM
     */
    public interface ParkDistFromAprilTags_Red {

        int fromId4_toCorner  = 83;
        int fromId4_toMiddle  = 57;

        int fromId5_toCorner  = 68;
        int fromId5_toMiddle  = 72;

        int fromId6_toCorner  = 52;
        int fromId6_toMiddle  = 88;
    }

    /**
     */
    public Direction backdropDirection;

    /**
     */
    public ParkPosition parkPosition;

    /**
     */
    public int leftTokenScanDistance = 10;

    /**
     */
    public int rightTokenScanDistance = 10;

    /**
     */
    public int frontTokenScanDistance = 35;

    /**
     */
    public int bumpForwardsDistance = 18;

    /**
     * For the distance sensor detection
     */
    public double backboardPlaceTarget = 4.5;

    /**
     */
    public int placePurplePixelForwardsDistance = 42;

    /**
     */
    public int placePurplePixelOppositeTrussDistance = 21;

    /**
     */
    public int placePurplePixelTrussSideDistance = 15;

    /**
     *
     */
    public int placeYellowPixelBackstageDistance = 20;

    /**
     *
     */
    public int corner_backDistance_afterPlacingPurplePixelForwards = 10;
    public int corner_backDistance_afterPlacingPurplePixelOppositeTruss = 50;
    public int corner_backDistance_afterPlacingPurplePixelTrussSide = 20;

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
    public int gotoCornerDistance_Far_forwards = 190;
    public int gotoCornerDistance_Far_oppositeTruss = 190;
    public int gotoCornerDistance_Far_trussSide = 190;

    /**
     */
    public int gotoCornerDistance_Near_forwards = 27;
    public int gotoCornerDistance_Near_oppositeTruss = 35;
    public int gotoCornerDistance_Near_trussSide = 23;

    /**
     */
    public int gotoMiddleDistance_Far_forwards = 209;
    public int gotoMiddleDistance_Far_oppositeTruss = 179;
    public int gotoMiddleDistance_Far_trussSide = 193;

    /**
     */
    public int gotoMiddleDistance_Near_forwards = 30;
    public int gotoMiddleDistance_Near_oppositeTruss = 60;
    public int gotoMiddleDistance_Near_trussSide = 35;

    /**
     */
    public int parkPositionForwardsDistance_afterPlacingYellowPixelOnBackdrop = 15;

}
