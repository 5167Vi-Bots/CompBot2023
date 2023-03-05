package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmRotate;

public class RotateManualPosition extends CommandBase {
    private ArmRotate ArmRotate;
    private double position;
    private boolean out;

    public RotateManualPosition(ArmRotate ArmRotate, boolean out) {
        this.ArmRotate = ArmRotate;
        this.position = ArmRotate.getPosition();
        this.out = out;
        addRequirements(ArmRotate);
    }

    @Override
    public void initialize() {
        ArmRotate.stop();
        this.position = ArmRotate.getPosition();
    }

    @Override
    public void execute() {
        position = ArmRotate.getPosition();
        if (out) { ArmRotate.setSpeed(0.15); }
        else {
            ArmRotate.setSpeed(-0.15);
        }
    }

    @Override
    public void end(boolean interrupted) {
        ArmRotate.doMagic((int) position);
    }
}
