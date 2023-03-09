package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private DoubleSupplier x, z;

    private double speedLimit;
    public DefaultDrive(DriveSubsystem driveSubsystem, DoubleSupplier x, DoubleSupplier z, double speedLimit) {
        this.driveSubsystem = driveSubsystem;
        this.x = x;
        this.z = z;
        this.speedLimit = speedLimit;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.drive(0, 0);
    }

    @Override
    public void execute() {
        // driveSubsystem.drive(x.getAsDouble()*-speedLimit, SquareInputs(z.getAsDouble() *speedLimit)*-1);
        driveSubsystem.drive(x.getAsDouble()*-speedLimit, z.getAsDouble()*-speedLimit, true);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0, 0);
    }
}
