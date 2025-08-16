package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.RevIMU;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AngularVelocity;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Quaternion;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class Robot {
    private final MecanumDrive drivetrain;

    public enum DriveState {
        ROBOT_CENTRIC,
        FIELD_CENTRIC
    }

    public Robot(HardwareMap hardwareMap) {
        MotorEx frontLeft = new MotorEx(hardwareMap, "frontLeft", Motor.GoBILDA.RPM_312);
        MotorEx frontRight = new MotorEx(hardwareMap, "frontRight", Motor.GoBILDA.RPM_312);
        MotorEx backLeft = new MotorEx(hardwareMap, "backLeft", Motor.GoBILDA.RPM_312);
        MotorEx backRight = new MotorEx(hardwareMap, "backRight", Motor.GoBILDA.RPM_312);

        frontLeft.setInverted(true);
        frontRight.setInverted(false);
        backLeft.setInverted(true);
        backRight.setInverted(false);

        frontLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);

        frontLeft.stopAndResetEncoder();
        frontRight.stopAndResetEncoder();
        backLeft.stopAndResetEncoder();
        backRight.stopAndResetEncoder();

        drivetrain = new MecanumDrive(false, frontLeft, frontRight, backLeft, backRight);
    }

    public void drive(DriveState state, GamepadEx gamepad, double limiter) {
        if (state == DriveState.ROBOT_CENTRIC) driveRobotCentric(gamepad, limiter);
    }

    private void driveRobotCentric(GamepadEx gamepad, double limiter) {
        double strafeSpeed = gamepad.getLeftX() * limiter;
        double forwardSpeed = gamepad.getLeftY() * limiter;
        double turnSpeed = gamepad.getRightX() * limiter;

        drivetrain.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }
}
