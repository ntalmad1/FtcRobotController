package org.firstinspires.ftc.library.utility;

/**
 * Utility class with static functions for converting units
 */
public class Converter
{
    /**
     * Private constructor so class can not be instantiated or extended
     */
    private Converter () { }

    /**
     *
     * @param length The length to convert to centimeters
     * @param convertFromUnits The units to convert from
     * @return The number of centimeters converted from the input. BEWARE: Int is used instead of double
     *         because centimeter is the smallest unit of operation currently in the drive train. May
     *         need to revisit
     */
    public static int convertToCm (double length, Units convertFromUnits)
    {
        switch (convertFromUnits)
        {
            case Centimeters:
                return (int)Math.round(length);

            case Meters:
                length = length * 100;
                return (int)Math.round(length);

            case Feet:
                length = length * 30.48;
                return (int)Math.round(length);

            case Inches:
                length = length / 2.54;
                return (int)Math.round(length);
        }

        return 0;
    }
}
