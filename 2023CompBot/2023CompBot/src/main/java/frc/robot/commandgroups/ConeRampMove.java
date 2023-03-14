package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AngleHigh;
import frc.robot.commands.AngleHome;
import frc.robot.commands.AngleMed;
import frc.robot.commands.Balance;
import frc.robot.commands.ClawMove;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveOffOfRamp;
import frc.robot.commands.DriveOntoRamp;
import frc.robot.commands.ExtendHigh;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.ExtendMed;
import frc.robot.commands.RotateHome;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveSubsystem;

public class ConeRampMove extends SequentialCommandGroup {
    public ConeRampMove(DriveSubsystem driveSubsystem, ArmExtend armExtend, ArmRotate armRotate, ArmAngle armAngle,
            Claw claw) {
        addCommands(

                new AngleHome(armAngle),
                new ExtendHome(armExtend),
                new RotateHome(armRotate),
                // Send to High
                //new ExtendMed(armExtend),
                new AngleHigh(armAngle),
                new ExtendHigh(armExtend),
                new AngleMed(armAngle),
                new WaitCommand(1),
                // Drop piece
                new ClawMove(claw, false).withTimeout(1),
                new ExtendHome(armExtend),
                new AngleHome(armAngle),
                new WaitCommand(0.5),
                // Movement ah
                new DriveDistance(driveSubsystem, -86),
              
                new Balance(driveSubsystem)
                

        // // new DriveOntoRamp(driveSubsystem, true),
        // new WaitCommand(2),
        // new DriveDistance(driveSubsystem, 0),
        // // new DriveOffOfRamp(driveSubsystem),
        // new WaitCommand(0.25),

        // new DriveOntoRamp(driveSubsystem, true),
        // new WaitCommand(2),

        // new Balance(driveSubsystem)
        // new DriveDistance(driveSubsystem, -110).alongWith(new
        // RotateBack(armRotate)).withTimeout(4)
        );
    }
}
