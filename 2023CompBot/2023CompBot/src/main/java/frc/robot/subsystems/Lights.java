package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Constants.LightConstants;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.PWM;

public class Lights extends SubsystemBase {
    

    private MotorController LEDController;   

    public Lights(int Channel) {
        LEDController = new PWMSparkMax(Channel);
        
    }
    

    public void setColor(Constants.LightConstants.LEDColors Color)
    {    
        System.out.println("Returning Color " + Color);
        LEDController.set(Color.PWMValue);
    }

}
