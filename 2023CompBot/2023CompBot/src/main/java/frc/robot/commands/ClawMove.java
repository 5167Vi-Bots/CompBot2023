package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class ClawMove extends CommandBase {
    private Claw claw;
    private boolean clawOpen;

    public ClawMove(Claw claw, boolean clawOpen) {
        this.claw = claw;
        this.clawOpen = clawOpen;
        addRequirements(claw);
    }

    @Override
    public void execute() {
        if (clawOpen) {
            claw.open();
        } else{
            claw.close();
        }
    }
}
