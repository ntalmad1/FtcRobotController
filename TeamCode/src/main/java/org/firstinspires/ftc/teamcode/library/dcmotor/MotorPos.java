package org.firstinspires.ftc.teamcode.library.dcmotor;

/**
 *
 */
public class MotorPos {

    /**
     */
    private Integer pos;

    /**
     */
    private double power;

    /**
     * Constructor - increment defautls to 1
     * @param pos
     */
    public MotorPos(Integer pos) {
        this(pos, 1);
    }


    /**
     * Constructor
     *
     * @param pos
     * @param power
     */
    public MotorPos(Integer pos, double power) {
        this.setPos(pos);
        this.setPower(power);
    }

    /**
     *
     * @return
     */
    public double getPower() {
        return this.power;
    }

    /**
     *
     * @return
     */
    public Integer getPos() {
        return this.pos;
    }

    /**
     *
     * @param power
     */
    public void setPower(double power) {
       this.power = power;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Integer pos) {
        this.pos = pos;
    }
}
