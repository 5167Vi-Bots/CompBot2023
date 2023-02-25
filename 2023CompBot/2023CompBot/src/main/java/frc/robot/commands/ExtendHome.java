package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtend;

public class ExtendHome extends CommandBase{
    private ArmExtend armExtend;
    private Timer timer;

    public ExtendHome(ArmExtend armExtend) {
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
        armExtend.home();
    }

    // @Override
    // public boolean isFinished() {
    //     return !armExtend.isMoving() && timer.get() > 0.7;
    // }

    @Override
    public void end(boolean interrupted) {
        // armExtend.stop // Possibly?
    }
}
