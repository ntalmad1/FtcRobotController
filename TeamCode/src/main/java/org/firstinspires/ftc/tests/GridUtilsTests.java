package org.firstinspires.ftc.tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.firstinspires.ftc.teamcode.library.utility.GridUtils;


import org.firstinspires.ftc.teamcode.library.utility.Point;

public class GridUtilsTests
{
    @Test
    public void TestRotatePointByDegreesWorks ()
    {
        // simple case rotate (0, 1) by 90 deg should equal (1, 0)
        Point point = GridUtils.rotatePointByDegrees(0, 1, 90);

        System.out.println("Point.getX() :: " + point.getX());
        System.out.println("Point.getY() :: " + point.getY());

        assertEquals(1, point.getX(),0 );
        assertEquals(0, point.getY(), 0);
    }
}
