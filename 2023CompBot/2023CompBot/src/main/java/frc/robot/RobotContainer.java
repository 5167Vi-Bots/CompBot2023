// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.PipeType;
import frc.robot.Constants.Ports.ControllerPorts;
import frc.robot.commandgroups.ArmBackHigh;
import frc.robot.commandgroups.ArmBackLow;
import frc.robot.commandgroups.ArmBackMed;
import frc.robot.commandgroups.ArmFrontHigh;
import frc.robot.commandgroups.ArmFrontLow;
import frc.robot.commandgroups.ArmFrontMed;
import frc.robot.commandgroups.ArmHome;
import frc.robot.commandgroups.ArmRightHigh;
import frc.robot.commandgroups.ArmRightLow;
import frc.robot.commandgroups.ArmRightMed;
import frc.robot.commandgroups.ConeMove;
import frc.robot.commandgroups.ConeRampMove;
import frc.robot.commands.AngleHigh;
import frc.robot.commands.AngleHome;
import frc.robot.commands.AngleIntake;
import frc.robot.commands.AngleLow;
import frc.robot.commands.AngleMed;
import frc.robot.commands.Balance;
import frc.robot.commands.ClawMove;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.ExtendHigh;
import frc.robot.commands.ExtendHome;
import frc.robot.commands.ExtendIntake;
import frc.robot.commands.ExtendLow;
import frc.robot.commands.ExtendManualPosition;
import frc.robot.commands.ExtendMed;
import frc.robot.commands.LimeDrive;
import frc.robot.commands.RotateBack;
import frc.robot.commands.RotateHome;
import frc.robot.commands.RotateLeft;
import frc.robot.commands.RotateManualPosition;
import frc.robot.commands.RotateRight;
import frc.robot.commands.SetColor;
import frc.robot.subsystems.ArmAngle;
import frc.robot.subsystems.ArmExtend;
import frc.robot.subsystems.ArmRotate;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final LimelightSubsystem limelight = new LimelightSubsystem("limelight-jimmy", 0.027, 0.025, 0.5, .5, true, true);
  private final ArmExtend armExtend = new ArmExtend();
  private final ArmRotate armRotate = new ArmRotate();
  private final ArmAngle armAngle = new ArmAngle();
  private final Claw claw = new Claw();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController driverController = new CommandXboxController(ControllerPorts.kDriverControllerPort);
  private final CommandJoystick buttonBoard = new CommandJoystick(ControllerPorts.kOperatorControllerPort);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    driveSubsystem.setDefaultCommand(
      new DefaultDrive(driveSubsystem,
       () -> driverController.getLeftY(),
      () -> driverController.getRightX(),
      0.75)
    );
  }

  public void teleInit() {
    armAngle.resetEncoder();
    armExtend.resetEncoder();
    armRotate.resetEncoder();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // button 2 is front low
    // buttonBoard.button(2).whileTrue(new ArmFrontLow(armAngle, armExtend, armRotate));
    // Extend farthest 5
    // Extend half 3
    // Extend Home 1

    driverController.axisGreaterThan(2, 0.2).whileTrue(new ExtendManualPosition(armExtend, false));
    // driverController.axisGreaterThan(3, 0.2).whileTrue(new LimeDrive(limelight, driveSubsystem, PipeType.CONE));//.whileTrue(new ExtendManualPosition(armExtend, true));


    // On high extend home, angle home, extend position, angle position
    // buttonBoard.button(10).or(driverController.y()).toggleOnTrue(new ExtendHome(armExtend).andThen(new AngleHome(armAngle)).andThen(new ExtendHigh(armExtend).until( () -> (driverController.getRightTriggerAxis() > 0.2) || driverController.getLeftTriggerAxis() > 0.2).andThen(new AngleHigh(armAngle))));
    buttonBoard.button(10).toggleOnTrue(new ExtendHome(armExtend).andThen(new AngleHigh(armAngle)).andThen(new ExtendHigh(armExtend)).andThen(new WaitCommand(1)).andThen(new AngleMed(armAngle)));
    buttonBoard.button(9).or(driverController.b()).toggleOnTrue(new ExtendHome(armExtend).andThen(new AngleHigh(armAngle)).andThen(new ExtendMed(armExtend).until( () -> (driverController.getRightTriggerAxis() > 0.2) || driverController.getLeftTriggerAxis() > 0.2)).andThen(new AngleMed(armAngle)));
    buttonBoard.button(8).or(driverController.a()).toggleOnTrue(new ExtendHome(armExtend).andThen(new AngleLow(armAngle)).andThen(new ExtendLow(armExtend).until( () -> (driverController.getRightTriggerAxis() > 0.2) || driverController.getLeftTriggerAxis() > 0.2)));
    buttonBoard.button(7).or(driverController.x()).toggleOnTrue(new AngleHome(armAngle).andThen(new ExtendHome(armExtend)));//.andThen(new ExtendHome(armExtend)));
    // buttonBoard.button(10).whileTrue(new ExtendHome(armExtend).andThen(new AngleHome(armAngle)));
    // buttonBoard.button(9).whileTrue(new ExtendMed(armExtend));
    // buttonBoard.button(8).whileTrue(new ExtendLow(armExtend));
    // buttonBoard.button(7).whileTrue(new ExtendHome(armExtend));
    buttonBoard.button(2).or(driverController.povLeft()).toggleOnTrue(new RotateLeft(armRotate));
    buttonBoard.button(1).or(driverController.povDown()).toggleOnTrue(new RotateBack(armRotate));
    buttonBoard.button(3).or(driverController.povRight()).toggleOnTrue(new RotateRight(armRotate));
    buttonBoard.button(4).or(driverController.povUp()).toggleOnTrue(new RotateHome(armRotate));
    driverController.y().toggleOnTrue(new ExtendHome(armExtend).andThen(new AngleIntake(armAngle)).andThen(new ExtendIntake(armExtend)));
    //buttonBoard.button(5).whileTrue(new Balance(driveSubsystem));
    buttonBoard.button(5).whileTrue(new RotateManualPosition(armRotate, false));
    buttonBoard.button(6).whileTrue(new RotateManualPosition(armRotate, true));
    // buttonBoard.button(10).whileTrue(new ArmFrontHigh(armAngle, armExtend, armRotate));
    // buttonBoard.button(9).whileTrue(new ArmFrontMed(armAngle, armExtend, armRotate));
    // buttonBoard.button(8).whileTrue(new ArmFrontLow(armAngle, armExtend, armRotate));
    // buttonBoard.button(7).whileTrue(new ArmHome(armAngle, armExtend, armRotate));
    // buttonBoard.button(5).whileTrue(new ArmRightHigh(armAngle, armExtend, armRotate));
    // buttonBoard.button(3).whileTrue(new ArmRightMed(armAngle, armExtend, armRotate));
    // buttonBoard.button(1).whileTrue(new ArmRightLow(armAngle, armExtend, armRotate));
    
    // home is 10
    // high is 9
    // med is 8
    // low is 7
    // // button 4 is front med
    // buttonBoard.button(4).whileTrue(new ArmFrontMed(armAngle, armExtend, armRotate));
    // // button 6 is front high
    // buttonBoard.button(6).whileTrue(new ArmFrontHigh(armAngle, armExtend, armRotate));
    // // button 1 is right low
    // buttonBoard.button(1).whileTrue(new ArmRightLow(armAngle, armExtend, armRotate));
    // // button 3 is right med
    // buttonBoard.button(3).whileTrue(new ArmRightMed(armAngle, armExtend, armRotate));
    // // button 5 is right high
    // buttonBoard.button(5).whileTrue(new ArmRightHigh(armAngle, armExtend, armRotate));
    // // button 7 is home
    // buttonBoard.button(7).whileTrue(new ArmHome(armAngle, armExtend, armRotate));
    // // button 8 is back low
    // buttonBoard.button(8).whileTrue(new ArmBackLow(armAngle, armExtend, armRotate));
    // // button 9 is back med
    // buttonBoard.button(9).whileTrue(new ArmBackMed(armAngle, armExtend, armRotate));
    // // button 10 is back high
    // buttonBoard.button(10).whileTrue(new ArmBackHigh(armAngle, armExtend, armRotate));
    // // button 11 is open
    buttonBoard.button(11).whileTrue(new ClawMove(claw, true));
    // // button 12 is close
    buttonBoard.button(12).whileTrue(new ClawMove(claw, false));
    driverController.leftBumper().whileTrue(new DefaultDrive(driveSubsystem, () -> driverController.getLeftY(), () -> driverController.getRightX(), 0.5));
    driverController.rightBumper().whileTrue(new DefaultDrive(driveSubsystem, () -> driverController.getLeftY(), () -> driverController.getRightX(), 1));
    //buttonBoard.button(5).whileTrue(new SetColor(null, 0.91));
    //buttonBoard.button(6).whileTrue(new SetColor(null, 0.69));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
   //return new ConeRampMove(driveSubsystem, armExtend, armRotate, armAngle, claw); // Pssssst Put auton command hereeeee (Almost guaranteed to be a sequential command group)
   return new ConeMove(driveSubsystem, armExtend, armRotate, armAngle, claw); // Pssssst Put auton command hereeeee (Almost guaranteed to be a sequential command group)
  }
}
