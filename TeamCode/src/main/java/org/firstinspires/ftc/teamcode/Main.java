package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Intake;

@TeleOp(name = "Main")
public class Main extends CommandOpMode {

    private GamepadEx driver;
    private GamepadEx operator;

    @Override
    public void initialize() {

        driver = new GamepadEx(gamepad1);
        operator = new GamepadEx(gamepad2);

        Intake intake = new Intake(hardwareMap);

        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1).toggleWhenActive(intake.intakeCommand());
        //Change the .toggleWhenActive to .whenActive to see a difference
        new Trigger(() -> driver.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1).toggleWhenActive(intake.outtakeCommand());
    }

}