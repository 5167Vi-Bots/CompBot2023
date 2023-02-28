package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtend;

public class ExtendManualPosition extends CommandBase {
    private ArmExtend armExtend;
    private double position;
    private boolean out;

    public ExtendManualPosition(ArmExtend armExtend, boolean out) {
        this.armExtend = armExtend;
        this.position = armExtend.getPosition();
        this.out = out;
        addRequirements(armExtend);
    }

    @Override
    public void initialize() {
        armExtend.stop();
        this.position = armExtend.getPosition();
    }

    @Override
    public void execute() {
        position = armExtend.getPosition();
        if (out) { armExtend.setSpeed(0.1); }
        else {
            armExtend.setSpeed(-0.1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        armExtend.doMagic((int) position);
    }
}
