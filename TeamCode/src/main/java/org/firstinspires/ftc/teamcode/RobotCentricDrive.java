package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class RobotCentricDrive extends OpMode {
    private GamepadEx driver1;
    private GamepadEx driver2;
    private Robot robot;

    @Override
    public void init() {
        driver1 = new GamepadEx(gamepad1);
        driver2 = new GamepadEx(gamepad2);
        robot = new Robot(hardwareMap);
    }

    @Override
    public void loop() {
        robot.drive(Robot.DriveState.ROBOT_CENTRIC, driver1, 0.5);
    }
}
