package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class Balance extends CommandBase {
    
        private DriveSubsystem driveSubsystem;
        private final double kP = 0.1;
        private double error;
        private double output;

        public Balance(DriveSubsystem driveSubsystem) {
            this.driveSubsystem = driveSubsystem;
            addRequirements(driveSubsystem);
        }
    
        @Override
        public void initialize() {
            driveSubsystem.drive(0, 0);
            error = 0;
            output = 0;
        }
    
        @Override
        public void execute() {
        

            error = 0 - driveSubsystem.getPitch();
            output = error * kP;
            if(output >= 0.30){//0.45
                output = 0.30;//0.45
            }
            else if(output <= -0.30){//0.45
                output = -0.30;//0.45
            }
            driveSubsystem.drive(-output, 0);

          
        }

        @Override
        public boolean isFinished()
        {
            // boolean isBalanced = false;
            // double currentPitch =  driveSubsystem.getPitch();
            // if (currentPitch > 0 && currentPitch < 1)
            //     isBalanced = true;
            // if (currentPitch < 0 && currentPitch > -1)
            //     isBalanced = true;

            // if (!isBalanced)
            //     i=0;
            // if (isBalanced && i < 100)
            // {
            //     driveSubsystem.drive(0,0);
            //     i++;
            //     return false;
            // }
            // //Return true when pidegon reports balanced
            // return isBalanced;
            return false;
        }

        @Override
        public void end(boolean interrupted) {
            driveSubsystem.drive(0, 0);
        }
        
}
