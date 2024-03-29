package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;

import javax.management.timer.Timer;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveOffOfRamp extends CommandBase {
    
        private DriveSubsystem driveSubsystem;
        private final double kP = 0.03;
        private double error;
        private double output;

        public DriveOffOfRamp(DriveSubsystem driveSubsystem) {
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
            //-17 is initial ramp, -11/10 is on top but not balanced
             double currentPitch = driveSubsystem.getPitch();
             
            //  if (currentPitch > 2 || currentPitch < -2)
            //  {
            //     driveSubsystem.drive(0,0);
            // }
            // else
            // {
                driveSubsystem.drive(.5,0);
            // }
             

            // error = -(currentPitch- -1);
            
            // driveSubsystem.drive(error * kP, 0);

            // while (!isFinished())
            // {
            //     double currentPitch = driveSubsystem.getPitch();
            //     if (currentPitch> 1 && currentPitch < 15)
            //         driveSubsystem.drive(.35, 0);
            //     if(currentPitch < -1 && currentPitch > -15)
            //         driveSubsystem.drive(-.35, 0);
            //     if(currentPitch < 1 && currentPitch > -1)
            //         driveSubsystem.drive(0,0);
            //     if (currentPitch < -15)
            //         driveSubsystem.drive(-.5, 0);
            //     if (currentPitch > 15)
            //         driveSubsystem.drive(.5,0);
                
            // }
            // Timer t = new Timer();

            // try {
            //     t.wait(100, 0);
            // } catch (InterruptedException e) { }
        }
    
        @Override
        public void end(boolean interrupted) {
            driveSubsystem.drive(0, 0);
        }

        int i = 0;
        @Override
        public boolean isFinished()
        {

            boolean IsDone = false;
            double currentPitch =  Math.abs(driveSubsystem.getPitch());
            
            IsDone = (currentPitch < 3);
            if (i < 20 && IsDone)
            {
                i++;
                return false;
            }
            if (!IsDone)
            {
                i = 0;
            }


            return IsDone;


            // boolean isBalanced = false;
            // double currentPitch =  driveSubsystem.getPitch();




            // return (currentPitch < 2 || currentPitch > -2);


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
        }
        
}
