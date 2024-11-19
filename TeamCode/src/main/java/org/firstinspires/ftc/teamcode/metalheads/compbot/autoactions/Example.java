package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Example implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private DcMotor lift;

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        // powers on motor, if it is not on
        if (!initialized) {
            lift.setPower(0.8);
            initialized = true;
        }

        // checks lift's current position
        double pos = lift.getCurrentPosition();
        packet.put("liftPos", pos);
        if (pos < 3000.0) {
            // true causes the action to rerun
            return CONTINUE;
        } else {
            // false stops action rerun
            lift.setPower(0);
            return STOP;
        }
        // overall, the action powers the lift until it surpasses
        // 3000 encoder ticks, then powers it off
    }
}
