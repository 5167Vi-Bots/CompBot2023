package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AngleHigh;
import frc.robot.commands.ExtendHigh;
import frc.robot.commands.RotateBack;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmBackHigh extends ParallelCommandGroup{
    public ArmBackHigh(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new AngleHigh(armAngle),
            new ExtendHigh(armExtend),
            new RotateBack(armRotate)
        );
    }
}
