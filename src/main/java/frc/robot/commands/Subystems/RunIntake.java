package frc.robot.commands.Subystems;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class RunIntake extends Command {
    private final Intake intakeSubsystem;

    //Initialize Command Subsytems
    public RunIntake(Intake iSubsystem) {
        intakeSubsystem = iSubsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize() {
        System.out.println("Intake Running");
        intakeSubsystem.runIntake(intakeSubsystem.intakeSpeed);
    }

    @Override
    public void end(boolean interrupted){
        intakeSubsystem.runIntake(0);
        System.out.println("Intake Finished");
    }
}