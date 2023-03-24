package frc.robot.commands;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
//import frc.robot.Constants.Lights.*;
import frc.robot.subsystems.*;


public class SetColorTimed extends CommandBase
{
    Lights m_Lights;
    int Level;
    public SetColorTimed(Lights lights) {
        super();
        m_Lights = lights;
        this.Level = 0;
        addRequirements(m_Lights);
    }

    @Override
    public void initialize()
    {
 
    }

    @Override 
    public void execute()
    {
        double TimeRemaining; 
        TimeRemaining = DriverStation.getMatchTime();

        //Set blinking intensity
        if (TimeRemaining > 30)
            Level = 0;
        else if (TimeRemaining > 15)
            Level = 1;
        else if (TimeRemaining > 10)
            Level = 2;
        else if (TimeRemaining > 5)
            Level = 3;
        
        //Determine which color and blinking intensity then set it
        if (DriverStation.getAlliance() == DriverStation.Alliance.Red)
        {
            switch (Level)
            {
            case 0: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.DarkRed);
            break;
            case 1: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.BreathingRed);
            break;
            case 2: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.HeartbeatRed);
            break;
            case 3:
            m_Lights.setColor(Constants.LightConstants.LEDColors.StrobeRed);
            break;
            }
        }
        if (DriverStation.getAlliance() == DriverStation.Alliance.Blue)
        {
            switch (Level)
            {
            case 0: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.DarkBlue);
            break;
            case 1: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.BreathingBlue);
            break;
            case 2: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.HeartbeatBlue);
            break;
            case 3:
            m_Lights.setColor(Constants.LightConstants.LEDColors.StrobeBlue);
            break;
            }
        }
        if (DriverStation.getAlliance() == DriverStation.Alliance.Invalid)
        {
            switch (Level)
            {
            case 0: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.White);
            break;
            case 1: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.BreathingWhite);
            break;
            case 2: 
            m_Lights.setColor(Constants.LightConstants.LEDColors.HeartbeatWhite);
            break;
            case 3:
            m_Lights.setColor(Constants.LightConstants.LEDColors.StrobeWhite);
            break;
            }
        }
        

    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

}