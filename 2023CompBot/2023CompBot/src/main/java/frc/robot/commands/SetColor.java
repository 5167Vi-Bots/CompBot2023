package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
//import frc.robot.Constants.Lights.*;
import frc.robot.subsystems.*;


public class SetColor extends CommandBase
{
    Lights m_Lights;
    Constants.LightConstants.LEDColors Color;
    public SetColor(Lights lights, Constants.LightConstants.LEDColors Color) {
        super();
        m_Lights = lights;
        this.Color = Color;
        addRequirements(m_Lights);
    }

    @Override
    public void initialize()
    {
    }

    @Override 
    public void execute()
    {
        m_Lights.setColor(Color);

    }

    @Override
    public boolean isFinished()
    {
        return false;
    }

}