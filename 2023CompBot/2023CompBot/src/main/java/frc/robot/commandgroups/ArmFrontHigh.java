package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AngleHigh;
import frc.robot.commands.ExtendHigh;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.RotateFront;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmFrontHigh extends SequentialCommandGroup{
    public ArmFrontHigh(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new ExtendHome(armExtend),
            new AngleHigh(armAngle).alongWith(new RotateFront(armRotate)),
            new ExtendHigh(armExtend)
        );
    }
}
