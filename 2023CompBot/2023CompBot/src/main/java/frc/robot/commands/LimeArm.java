package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CamMode;
import frc.robot.Constants.PipeType;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;
import frc.robot.subsystems.LimelightSubsystem;

public class LimeArm extends CommandBase {
    private LimelightSubsystem limelight;
    private ArmExtend armExtend;
    private ArmRotate armRotate;
    private ArmAngle armAngle;

    public LimeArm(LimelightSubsystem limelight, ArmExtend armExtend, ArmRotate armRotate, ArmAngle armAngle, PipeType pipe) {
        this.limelight = limelight;
        this.armExtend = armExtend;
        this.armRotate = armRotate;
        this.armAngle = armAngle;
        this.limelight.setPipe(pipe);

        addRequirements(limelight, armExtend, armRotate, armAngle);
    }

    @Override
    public void initialize() {
        limelight.setCamMode(CamMode.VISION);
    }

    @Override
    public void execute() {
        
    }

    // @Override
    // public boolean isFinished() {
        
    // }

    @Override
    public void end(boolean interrupted) {
        
    }


}
