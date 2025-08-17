package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Claw extends SubsystemBase {
    private final ServoEx claw;

    public Claw(HardwareMap hardwareMap, String id) {
        claw = new SimpleServo(hardwareMap, id, 0, 300);
    }

    public void open() {
        claw.setPosition(0); // TODO - Edit
    }

    public void close() {
        claw.setPosition(1); // TODO - Edit
    }
}
