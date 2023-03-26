package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class ClawAuto extends CommandBase {
    private Claw claw;

    public ClawAuto(Claw claw) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        if (claw.inRange()) {
            claw.close();
        }
    }

    @Override
    public void end(boolean interrupted) {
        
    }
}
