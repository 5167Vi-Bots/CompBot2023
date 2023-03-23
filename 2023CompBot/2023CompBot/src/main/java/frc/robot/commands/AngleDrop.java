package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmAngle;

public class AngleDrop extends CommandBase {
    private Timer timer;
    private ArmAngle armAngle;
    

    public AngleDrop(ArmAngle armAngle) {
        this.armAngle = armAngle;
        timer = new Timer();

        addRequirements(armAngle);
    }

    @Override
    public void initialize() {
        armAngle.stop();
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        armAngle.drop();
    }

    @Override
    public boolean isFinished() {
        return !armAngle.isMoving()  && armAngle.targetPosition() == armAngle.drop; //&& timer.get() > 0.2; 
    }

    @Override
    public void end(boolean interrupted) {
        // armAngle.stop(); // Possibly?
    }
}