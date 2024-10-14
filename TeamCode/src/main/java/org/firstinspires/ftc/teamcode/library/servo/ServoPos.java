package org.firstinspires.ftc.teamcode.library.servo;

/**
 *
 */
public class ServoPos {

    /**
     */
    private Double pos;

    /**
     */
    private double increment;

    /**
     * Constructor - increment defautls to 1
     * @param pos
     */
    public ServoPos(Double pos) {
        this(pos, 1);
    }


    /**
     * Constructor
     *
     * @param pos
     * @param increment
     */
    public ServoPos(Double pos, double increment) {
        this.setPos(pos);
        this.setIncrement(increment);
    }

    /**
     *
     * @return
     */
    public double getIncrement() {
        return this.increment;
    }

    /**
     *
     * @return
     */
    public Double getPos() {
        return this.pos;
    }

    /**
     *
     * @param increment
     */
    public void setIncrement(double increment) {
       this.increment = increment;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Double pos) {
        this.pos = pos;
    }
}
