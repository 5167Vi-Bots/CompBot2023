package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AngleMed;
import frc.robot.commands.ExtendMed;
import frc.robot.commands.RotateFront;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmFrontMed extends ParallelCommandGroup{
    public ArmFrontMed(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new AngleMed(armAngle),
            new ExtendMed(armExtend),
            new RotateFront(armRotate)
        );
    }
}
