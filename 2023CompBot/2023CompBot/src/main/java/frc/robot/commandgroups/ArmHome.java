package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AngleHome;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.RotateHome;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmHome extends SequentialCommandGroup{
    public ArmHome(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new ExtendHome(armExtend),
            new AngleHome(armAngle).alongWith(new RotateHome(armRotate))
        );
    }
}
