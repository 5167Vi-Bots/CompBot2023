package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveDistance extends CommandBase {
    
    private DriveSubsystem driveSubsystem;
    private int inches;

    public DriveDistance(DriveSubsystem driveSubsystem, int inches){
        this.driveSubsystem = driveSubsystem;
        this.inches = inches;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.resetEncoders();
    }

    @Override
    public void execute() {
        driveSubsystem.driveForward(inches);
    }

    


}
