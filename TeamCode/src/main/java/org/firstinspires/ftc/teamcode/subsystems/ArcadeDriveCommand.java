package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;

public class ArcadeDriveCommand extends CommandBase {

    private final Drivetrain drivetrain;
    private final DoubleSupplier drive;
    private final DoubleSupplier turn;

    public ArcadeDriveCommand(Drivetrain drivetrain,
                              DoubleSupplier drive,
                              DoubleSupplier turn) {
        this.drivetrain = drivetrain;
        this.drive = drive;
        this.turn = turn;

        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        drivetrain.arcadeDrive(drive.getAsDouble(), turn.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.stop();
    }
}