package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.WaitUntilCommand;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter extends SubsystemBase {

    DcMotorEx shooter = hardwareMap.get(DcMotorEx.class, "shooter");
    //Hardcoded values, until we use AprilTags and LL3G
    private static final double TARGET_RPM = 3000;
    private static final double TOLERANCE_RPM = 75;

    public Shooter() {
        shooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        /*
        P: Proportional
        I: Integral
        D: Derivative
        F: Feed-forward Gain
        
        YOU MUST TUNE THESE!!
         */
        shooter.setVelocityPIDFCoefficients(
                50,
                0,
                0,
                12);
    }

    //Shooter Control\\

    public void setVelocity(double rpm) {
        shooter.setVelocity(rpm);
    }

    public void stop() {
        shooter.setPower(0);
    }

    public double getVelocity() {
        return shooter.getVelocity();
    }

    public boolean isWithinTolerance(double target) {
        return Math.abs(getVelocity() - target) < TOLERANCE_RPM;
    }

    // Commands (For buttons)
    public StartEndCommand manualShoot() {
        return new StartEndCommand(
                () -> setVelocity(TARGET_RPM),
                //:: = Method Reference
                this::stop
        );
    }

}