package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.ArmConstants;

public class Claw extends SubsystemBase {
    private DoubleSolenoid clawSolenoid;

    public Claw() {
        clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.kClawForward, ArmConstants.kClawReverse);
    }
    
    @Override
    public void periodic() {
        String solValue;
        if (clawSolenoid.get() == Value.kForward) {
            solValue = "Forward";
        } else {
            solValue = "Reverse";
        }

        SmartDashboard.putString("Claw Value", solValue);
    }

    public void open(){
        clawSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close(){
        clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    }




}
