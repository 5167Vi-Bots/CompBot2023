package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DriveDistance extends CommandBase {
    
    private DriveSubsystem driveSubsystem;
    private int inches;
    private int ticks; 
    private double kP = 0.01;

    public DriveDistance(DriveSubsystem driveSubsystem, int inches){
        this.driveSubsystem = driveSubsystem;
        this.inches = inches;
    //private final int ticksPerInch = 1050;
    this.ticks = inches * 1130;
        addRequirements(driveSubsystem);

    }

    @Override
    public void initialize() {
        driveSubsystem.resetEncoders();
    }

    boolean IsFinished = false;
    @Override
    public void execute() {
        double error = ticks - driveSubsystem.getPosition();
        double power = error * kP;
        if (power > 0.55) {
            power = 0.55;
        } else if (power < -0.55) {
            power = -0.55;
        }

        if (Math.abs(error) < 600) {
            power = 0;
        }
        if (power ==0)
        IsFinished = true;
        driveSubsystem.drive(power, 0);
        // if (driveSubsystem.getPosition() < ticks)
        //     driveSubsystem.drive(.5, 0);
        // else 
        //     driveSubsystem.drive(0,0);
    }

    @Override
    public boolean isFinished()
    {
        return IsFinished;
    }

    


}
