package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends CommandBase {
    private DriveSubsystem driveSubsystem;
    private DoubleSupplier x, z;

    public DefaultDrive(DriveSubsystem driveSubsystem, DoubleSupplier x, DoubleSupplier z) {
        this.driveSubsystem = driveSubsystem;
        this.x = x;
        this.z = z;
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {
        driveSubsystem.drive(0, 0);
    }

    @Override
    public void execute() {
        driveSubsystem.drive(x.getAsDouble(), -z.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0, 0);
    }
}
