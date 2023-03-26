package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Claw;

public class ClawMove extends CommandBase {
    private Claw claw;

    public ClawMove(Claw claw){ //boolean clawOpen) {
        this.claw = claw;
        addRequirements(claw);
    }

    @Override
    public void initialize() {
        if (claw.isOpen()) {
            claw.close();
        } else{
            claw.open();
        }
    }

    @Override
    public boolean isFinished() {
        // TODO Auto-generated method stub
        return true;
    }
}
