package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CamMode;
import frc.robot.Constants.LedMode;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimeDrive extends CommandBase {

    private LimelightSubsystem limelight;
    private DriveSubsystem driveSubsystem;

    public LimeDrive(LimelightSubsystem limelight, DriveSubsystem driveSubsystem, int pipe) {
        this.limelight = limelight;
        this.limelight.setPipe(pipe);
        this.driveSubsystem = driveSubsystem;

        addRequirements(limelight, driveSubsystem);
    }


    @Override
    public void initialize() {
        limelight.setCamMode(CamMode.VISION);
        limelight.setLedMode(LedMode.PIPE_SETTING);
        driveSubsystem.stop();
    }

    @Override
    public void execute() {
        limelight.updateTracking(0, 0, driveSubsystem);
    }

    @Override
    public boolean isFinished() {
        return limelight.doneTargeting();
    }
}