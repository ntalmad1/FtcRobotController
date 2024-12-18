package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class ViperSlideReachedTargetPosition implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;

    private int targetPosition;

    private EncodedMotor viperSlide;

    /**
     * Constructor
     * @param targetPosition
     */
    public ViperSlideReachedTargetPosition(EncodedMotor viperSlide, int targetPosition) {
        this.viperSlide = viperSlide;
        this.targetPosition = targetPosition;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        // checks lift's current position
        double pos = viperSlide.getCurrentPosition();
        packet.put("viperSlidePos", pos);
        if ((pos > targetPosition - 20) && (pos < targetPosition + 20)) {

            return STOP;
        } else {

            return CONTINUE;
        }
        // overall, the action powers the lift until it surpasses
        // 3000 encoder ticks, then powers it off
    }
}
