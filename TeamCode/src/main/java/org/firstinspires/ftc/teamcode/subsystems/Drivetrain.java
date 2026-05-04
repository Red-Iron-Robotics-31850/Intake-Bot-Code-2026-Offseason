package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain extends SubsystemBase {

    private final DcMotor leftMotor, rightMotor;

    public Drivetrain(HardwareMap hwmap) {
        leftMotor = hwmap.get(DcMotor.class, "left_drive");
        rightMotor = hwmap.get(DcMotor.class, "right_drive");

        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);
    }

    public void arcadeDrive(double drive, double turn) {
        double leftPower = drive + turn;
        double rightPower = drive - turn;

        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));
        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;
        }

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public void stop() {
        leftMotor.setPower(0);
        rightMotor.setPower(0);
    }

}