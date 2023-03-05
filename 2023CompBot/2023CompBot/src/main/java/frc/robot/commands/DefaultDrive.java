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
        driveSubsystem.drive(x.getAsDouble()*-speedLimit, SquareInputs(z.getAsDouble() *speedLimit)*-1);
    }
    
    private double SquareInputs(double Input)
    {
        double SquaredInput = Input*Input;
        boolean isNegative = Input < 0;

        if (isNegative)
        SquaredInput = SquaredInput * -1;

        return SquaredInput;
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.drive(0, 0);
    }
}
