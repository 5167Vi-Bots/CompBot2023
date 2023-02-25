package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmRotate;

public class RotateRight extends CommandBase {
    private ArmRotate armRotate;
    private Timer timer;
    
    public RotateRight(ArmRotate armRotate) {
        this.armRotate = armRotate;
        timer = new Timer();
        addRequirements(armRotate);
    }

    @Override
    public void initialize() {
        armRotate.stop();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        armRotate.right();
    }

    @Override
    public boolean isFinished() {
        return !armRotate.isMoving() && timer.get() > 0.7;
    }

    @Override
    public void end(boolean interrupted) {
        // armRotate.stop() // Possibly?
    }
}
