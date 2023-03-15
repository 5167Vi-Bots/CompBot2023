package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmAngle;

public class AngleManualPosition extends CommandBase {
    private ArmAngle armAngle;
    private double position;
    private boolean up;

    public AngleManualPosition(ArmAngle armAngle, boolean up) {
        this.armAngle = armAngle;
        this.position = armAngle.getPosition();
        this.up = up;
        addRequirements(armAngle);
    }

    @Override
    public void initialize() {
        armAngle.stop();
        this.position = armAngle.getPosition();
    }

    @Override
    public void execute() {
        position = armAngle.getPosition();
        if (up) { armAngle.setSpeed(0.1); }
        else {
            armAngle.setSpeed(-0.1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        armAngle.doMagic((int) position);
    }
}
