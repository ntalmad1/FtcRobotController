package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class MainBoomToZero implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private EncodedMotor mainBoom;

    /**
     * Constructor
     * @param mainBoom
     */
    public MainBoomToZero(EncodedMotor mainBoom) {
        this.mainBoom = mainBoom;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {
        // powers on motor, if it is not on
        if (!initialized) {
            mainBoom.setTargetPosition(Constants.MAIN_BOOM_MIN_TICS);
            mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mainBoom.setPower(1);
            initialized = true;
        }

        // checks lift's current position
        double pos = mainBoom.getCurrentPosition();
        packet.put("mainBoomPos", pos);
        if (pos > 20) {
            // true causes the action to rerun
            return CONTINUE;
        } else {
            // false stops action rerun
            mainBoom.setPower(0);
            return STOP;
        }
        // overall, the action powers the lift until it surpasses
        // 3000 encoder ticks, then powers it off
    }
}
