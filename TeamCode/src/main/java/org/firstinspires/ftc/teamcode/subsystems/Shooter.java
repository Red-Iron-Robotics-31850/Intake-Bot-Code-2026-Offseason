package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.command.StartEndCommand;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter extends SubsystemBase {

    private final DcMotorEx motor;   /*
                                     provides enhanced motor functionality
                                     which is available with some hardware
                                     devices.
                                     */

    //Hardcoded Values, until we use AprilTags and LL3G
    private static final double TARGET_RPM = 3000;
    private static final double TOLERANCE = 75;
    public double TPS = 2520;

    public Shooter(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotorEx.class, "shooter");

        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT); //Coast Mode

        //TUNE\\
        motor.setVelocityPIDFCoefficients(
                50,
                0,
                0,
                12);
    }


    // Flywheel control

    public void setRPM(double rpm) {
        motor.setVelocity((TPS * 60) / 28); //TSP -> RPM
    }

    public void stop() {
        motor.setPower(0); //Stops the motor
    }

    public double getVelocity() {
        return motor.getVelocity(); //Used for tolerance things
    }

    public boolean isWithinTolerance(double target) { //Tests for whether the shooter is within the set RPM.
        return Math.abs(target - getVelocity()) < TOLERANCE;
    }

    // Commands (For buttons)

    // Hold to spin up
    public StartEndCommand shoot() {
        return new StartEndCommand(
                () -> setRPM(TARGET_RPM),
                this::stop
        );
    }


    // Spin up and wait until ready (useful for autos or logic)
//    public SequentialCommandGroup spinUpUntilReady() {
//        return new SequentialCommandGroup(
//                new InstantCommand(() -> setRPM(TARGET_RPM)),
//                new WaitUntilCommand(() -> isWithinTolerance(TARGET_RPM))
//        );
//    }
}