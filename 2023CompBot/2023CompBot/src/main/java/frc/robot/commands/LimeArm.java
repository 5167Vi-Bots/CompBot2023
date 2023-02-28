package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CamMode;
import frc.robot.Constants.PipeType;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.util.XYOutputs;

public class LimeArm extends CommandBase {
    private LimelightSubsystem limelight;
    private ArmExtend armExtend;
    private ArmRotate armRotate;
    private ArmAngle armAngle;
    private boolean rotateFinished;
    private Timer timer;

    public LimeArm(LimelightSubsystem limelight, ArmExtend armExtend, ArmRotate armRotate, ArmAngle armAngle, PipeType pipe) {
        this.limelight = limelight;
        this.armExtend = armExtend;
        this.armRotate = armRotate;
        this.armAngle = armAngle;
        this.limelight.setPipe(pipe);
        timer = new Timer();

        addRequirements(limelight, armExtend, armRotate, armAngle);
    }

    @Override
    public void initialize() {
        limelight.setCamMode(CamMode.VISION);
        rotateFinished = false;
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        if (!rotateFinished) {
            XYOutputs xyOutputs = limelight.updateTracking(0, 0);
            armRotate.setSpeed(xyOutputs.x);
            if (xyOutputs.x < 0.05) rotateFinished = true;
        } else {
            armExtend.doMagic((int)limelight.calculateDistanceAsTicks(0,0,0,0));
        }
    }

    @Override
    public boolean isFinished() {
        return !armExtend.isMoving() && timer.get() > 0.15;
    }

    @Override
    public void end(boolean interrupted) {
        
    }


}
