package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter extends SubsystemBase {

    private final DcMotorEx flywheel;

    //Hardcoded Values, until we use AprilTags and LL3G
    private static final double TARGET_RPM = 3000;
    private static final double TOLERANCE = 75;

    public Shooter(HardwareMap hardwareMap) {
        flywheel = hardwareMap.get(DcMotorEx.class, "shooter");

        flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //TUNE\\
        flywheel.setVelocityPIDFCoefficients(
                50,
                0,
                0,
                12);
    }


    // Flywheel control

    public void setVelocity(double rpm) {
        flywheel.setVelocity(rpm);
    }

    public void stop() {
        flywheel.setPower(0);
    }

    public double getVelocity() {
        return flywheel.getVelocity();
    }

    public boolean isWithinTolerance(double target) {
        return Math.abs(getVelocity() - target) < TOLERANCE;
    }

    // Commands (For buttons)

    // Hold to spin up
    public StartEndCommand shoot() {
        return new StartEndCommand(
                () -> setVelocity(TARGET_RPM),
                this::stop
        );
    }
}