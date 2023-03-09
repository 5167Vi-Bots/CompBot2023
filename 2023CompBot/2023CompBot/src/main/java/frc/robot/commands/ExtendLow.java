package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtend;

public class ExtendLow extends CommandBase{
    private ArmExtend armExtend;
    private Timer timer;

    public ExtendLow(ArmExtend armExtend) {
        this.armExtend = armExtend;
        timer = new Timer();
        addRequirements(armExtend);
    }

    @Override
    public void initialize() {
        armExtend.stop();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        armExtend.low();
    }

    @Override
    public boolean isFinished() {
        return !armExtend.isMoving() && armExtend.targetPosition() == armExtend.low;
    }

    @Override
    public void end(boolean interrupted) {
        // armExtend.stop // Possibly?
    }
}
