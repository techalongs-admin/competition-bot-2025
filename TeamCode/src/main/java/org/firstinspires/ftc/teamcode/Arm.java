package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm extends SubsystemBase {
    private final MotorEx arm;

    public Arm(HardwareMap hardwareMap, String id) {
        arm = new MotorEx(hardwareMap, id, Motor.GoBILDA.RPM_312); // TODO: Change to match motor

        arm.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        arm.stopAndResetEncoder();

        arm.setRunMode(Motor.RunMode.VelocityControl);

        arm.setVeloCoefficients(0.05, 0.01, 0.31); // TODO: EDIT
        arm.setFeedforwardCoefficients(0.92, 0.47, 0.3); // TODO: EDIT
    }

    public void raise(double limiter) {
        arm.set(1);
    }

    public void lower(double limiter) {
        arm.set(-1);
    }

    public void stop() {
        arm.stopMotor();
    }

    public void runTo(int target) {
        arm.setTargetPosition(target);

        while (!arm.atTargetPosition()) {
            if (arm.getCurrentPosition() > target) arm.set(-1);
            else if (arm.getCurrentPosition() < target) arm.set(1);
        }

        arm.stopMotor();
    }
}
