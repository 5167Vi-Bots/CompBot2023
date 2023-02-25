package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AngleLow;
import frc.robot.commands.ExtendLow;
import frc.robot.commands.RotateRight;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmRightLow extends ParallelCommandGroup{
    public ArmRightLow(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new AngleLow(armAngle),
            new ExtendLow(armExtend),
            new RotateRight(armRotate)
        );
    }
}
