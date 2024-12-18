package org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions;

import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.CONTINUE;
import static org.firstinspires.ftc.teamcode.metalheads.compbot.autoactions.ActionsUtil.STOP;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.library.encodedmotor.EncodedMotor;
import org.firstinspires.ftc.teamcode.metalheads.compbot.Constants;

public class MainBoomToSpecimenPickReady implements Action {
    // checks if the lift motor has been powered on
    private boolean initialized = false;
    private EncodedMotor mainBoom;
    private int extraTicks;

    /**
     * Constructor
     * @param mainBoom
     */
    public MainBoomToSpecimenPickReady(EncodedMotor mainBoom) {
        this(mainBoom, 0);
    }

    public MainBoomToSpecimenPickReady(EncodedMotor mainBoom, int extraTicks) {
        this.mainBoom = mainBoom;
        this.extraTicks = extraTicks;
    }

    // actions are formatted via telemetry packets as below
    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        int targetPosition = (Constants.SPECIMEN_PICK_READY.mainBoomPos.getPos() + extraTicks);

        // powers on motor, if it is not on
        if (!initialized) {
            mainBoom.setTargetPosition(targetPosition);
            mainBoom.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            mainBoom.setPower(1);
            initialized = true;
        }

        // checks lift's current position
        double pos = mainBoom.getCurrentPosition();
        packet.put("mainBoomPos", pos);
        if ((pos > targetPosition - 20) && (pos < targetPosition + 20)) {

            return STOP;
        } else {

            return CONTINUE;
        }
        // overall, the action powers the lift until it surpasses
        // 3000 encoder ticks, then powers it off
    }
}
