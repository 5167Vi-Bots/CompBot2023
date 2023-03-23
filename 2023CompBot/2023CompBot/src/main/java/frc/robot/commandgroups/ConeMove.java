package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AngleHigh;
import frc.robot.commands.AngleHome;
import frc.robot.commands.AngleMed;
import frc.robot.commands.ClawMove;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExtendHigh;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.RotateBack;
import frc.robot.commands.RotateHome;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveSubsystem;

public class ConeMove extends SequentialCommandGroup {
    public ConeMove(DriveSubsystem driveSubsystem, ArmExtend armExtend, ArmRotate armRotate, ArmAngle armAngle, Claw claw) {
        addCommands(
            // Send Arm Home
            new InstantCommand(() -> claw.close()).withTimeout(0.5),
            new AngleHome(armAngle),
            new ExtendHome(armExtend),
            new RotateHome(armRotate),
            // Send to High
            new AngleHigh(armAngle),
            new ExtendHigh(armExtend),
            new AngleMed(armAngle),
            new WaitCommand(2),
            // Drop piece
            new InstantCommand(() -> claw.open()).withTimeout(1),
            new ExtendHome(armExtend),
            new AngleHome(armAngle),
            new WaitCommand(2),
            new DriveDistance(driveSubsystem, -150)
            // new DriveDistance(driveSubsystem, -110).alongWith(new RotateBack(armRotate)).withTimeout(4)
        );
    }
}
