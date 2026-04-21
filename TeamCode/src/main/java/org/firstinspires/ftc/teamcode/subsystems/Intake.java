package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake extends SubsystemBase {
    public enum Speed {
        INTAKE,
        OUTTAKE,
        STOP
    }

    DcMotor motor = hardwareMap.get(DcMotor.class, "intake");

    public void set(Speed speed) {
        switch (speed) {
            case INTAKE:
                motor.setPower(0.8);
                break;
            case OUTTAKE:
                motor.setPower(-0.8);
                break;
            case STOP:
                motor.setPower(0.0);
                break;
        }
    }

    public StartEndCommand intakeCommand() {
        return new StartEndCommand(
                () -> {
                    set(Speed.INTAKE);
                },
                () -> {
                    set(Speed.STOP);
                },
                this
        );
    }

    public StartEndCommand outtakeCommand() {
        return new StartEndCommand(
                () -> {
                    set(Speed.OUTTAKE);
                },
                () -> {
                    set(Speed.STOP);
                },
                this
        );
    }
}