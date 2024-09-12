package org.firstinspires.ftc.teamcode.library.claw;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.boom.BoomConfig;
import org.firstinspires.ftc.library.rotator.RotatorConfig;


public class ClawConfig {

    public IsaacBot robot;

    public BoomConfig clawBoomConfig;

    public double rightClawMaxPosition;
    public double rightClawMinPosition;
    public double leftClawMaxPosition;
    public double leftClawMinPosition;
    public double rightClawInitPosition = 0.0;
    public double leftClawInitPosition = 0.0;

    public String leftClawName;
    public String rightClawName;
}
