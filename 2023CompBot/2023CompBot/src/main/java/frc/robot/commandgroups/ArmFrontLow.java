package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AngleLow;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.ExtendLow;
import frc.robot.commands.RotateFront;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmFrontLow extends SequentialCommandGroup{
    public ArmFrontLow(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new ExtendHome(armExtend),
            new AngleLow(armAngle).alongWith(new RotateFront(armRotate)),
            new ExtendLow(armExtend)
        );
    }
}
