package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class ViperSlideToSpecimenHighReady implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private EncodedMotor viperSlide;
    private int extraTicks;

    /**
     * Constructor
     * @param viperSlide
     */
    public ViperSlideToSpecimenHighReady(EncodedMotor viperSlide, int extraTicks) {
        this.viperSlide = viperSlide;
        this.extraTicks = extraTicks;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        int targetPosition = (Constants.SPECIMEN_PLACE_HIGH_READY.vSlidePos.getPos() + extraTicks);

        // powers on motor, if it is not on
        if (!initialized) {
            viperSlide.setTargetPosition(targetPosition);
            viperSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            viperSlide.setPower(1);
            initialized = true;
        }

        // checks Slides current position
        double pos = viperSlide.getCurrentPosition();
        packet.put("viperSlidePos", pos);

        if (pos > targetPosition - 20 || pos < targetPosition + 20) {
            // true causes the action to rerun
            return CONTINUE;

        } else {
            // false stops action rerun
            return STOP;
        }
    }
}
