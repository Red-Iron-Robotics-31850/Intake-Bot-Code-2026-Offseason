package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake extends SubsystemBase {
    public enum Speed {
        INTAKE(0.8),
        OUTTAKE(-0.8),
        STOP(0);

        private final double percentOutput;

        private Speed(double percentOutput) {
            this.percentOutput = percentOutput;
        }
    }
    private final DcMotor motor;

    public Intake(HardwareMap hwmap) {
        motor = hwmap.get(DcMotor.class, "intake");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void set(Speed speed) {
        motor.setPower(speed.percentOutput);
    }

    public StartEndCommand intakeCommand() {
        return new StartEndCommand(
                () -> set(Speed.INTAKE),
                () -> set(Speed.STOP),
                this
        );
    }

    public StartEndCommand outtakeCommand() {
        return new StartEndCommand(
                () -> set(Speed.OUTTAKE),
                () -> set(Speed.STOP),
                this
        );
    }
}