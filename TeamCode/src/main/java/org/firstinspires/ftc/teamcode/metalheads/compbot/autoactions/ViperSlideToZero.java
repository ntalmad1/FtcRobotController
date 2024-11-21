package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class ViperSlideToZero implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private EncodedMotor viperSlide;

    /**
     * Constructor
     * @param viperSlide
     */
    public ViperSlideToZero(EncodedMotor viperSlide) {
        this.viperSlide = viperSlide;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        int targetPosition = Constants.VIPER_SLIDES_MIN_TICS;

        // powers on motor, if it is not on
        if (!initialized) {
            viperSlide.setTargetPosition(targetPosition);
            viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viperSlide.setPower(1);
            initialized = true;
        }

        // checks lift's current position
        double pos = viperSlide.getCurrentPosition();
        packet.put("viperSlidePos", pos);
        if (pos > targetPosition - 30 || pos < targetPosition + 30) {
            // true causes the action to rerun
            return CONTINUE;
        } else {
            // false stops action rerun
            return STOP;
        }
        // overall, the action powers the lift until it surpasses
        // 3000 encoder ticks, then powers it off
    }
}
