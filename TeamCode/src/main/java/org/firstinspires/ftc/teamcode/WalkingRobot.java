package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Deprecated
@TeleOp (name = "WalkingRobot")
public class WalkingRobot extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private ColorSensor sensor;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        sensor = hardwareMap.get(ColorSensor.class, "sensor");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            float FLPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x) + gamepad1.left_stick_x;
            float FRPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x) - gamepad1.left_stick_x;
            float BLPower = (-gamepad1.left_stick_y + gamepad1.right_stick_x) - gamepad1.left_stick_x;
            float BRPower = (-gamepad1.left_stick_y - gamepad1.right_stick_x) + gamepad1.left_stick_x;

            double limiter = 1;
            frontLeft.setPower(FLPower * limiter);
            frontRight.setPower(FRPower * limiter);
            backLeft.setPower(BLPower * limiter);
            backRight.setPower(BRPower * limiter);

            updateTelemetry();
        }
    }

    private void updateTelemetry() {
        telemetry.addData("Front Right", frontRight.getPower());
        telemetry.addData("Front Left", frontLeft.getPower());
        telemetry.addData("Back Right", backRight.getPower());
        telemetry.addData("Back Left", backLeft.getPower());
        telemetry.addData("Red", sensor.red());
        telemetry.addData("Green", sensor.green());
        telemetry.addData("Blue", sensor.blue());
        telemetry.addData("Alpha", sensor.alpha());
        telemetry.update();
    }
}
