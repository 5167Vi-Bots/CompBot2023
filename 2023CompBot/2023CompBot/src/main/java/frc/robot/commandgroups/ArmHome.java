package frc.robot.commandgroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.AngleHome;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.RotateHome;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;

public class ArmHome extends ParallelCommandGroup{
    public ArmHome(ArmAngle armAngle, ArmExtend armExtend, ArmRotate armRotate) {
        addCommands(
            new AngleHome(armAngle),
            new ExtendHome(armExtend),
            new RotateHome(armRotate)
        );
    }
}
