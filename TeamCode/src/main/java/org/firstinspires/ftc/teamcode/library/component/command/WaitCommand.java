package org.firstinspires.ftc.teamcode.library.component.command;

import com.qualcomm.robotcore.util.ElapsedTime;

public class WaitCommand extends AbstractCommand {

    private double duration;

    private ElapsedTime timer;

    public WaitCommand (double duration) {
        this.duration = duration;


    }
}
