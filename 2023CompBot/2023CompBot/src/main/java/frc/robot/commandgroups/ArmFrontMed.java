package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AngleMed;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.ExtendMed;
import frc.robot.commands.RotateFront;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmFrontMed extends SequentialCommandGroup{
    public ArmFrontMed(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new ExtendHome(armExtend),
            new AngleMed(armAngle).alongWith(new RotateFront(armRotate)),
            new ExtendMed(armExtend)
        );
    }
}
