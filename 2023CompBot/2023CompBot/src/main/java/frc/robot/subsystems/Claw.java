package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Ports.ArmConstants;
import com.revrobotics.*;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

public class Claw extends SubsystemBase {
    private DoubleSolenoid clawSolenoid;
    private Rev2mDistanceSensor distanceSensor;
    private boolean clawOpen;

    public Claw() {
        clawSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.kClawForward, ArmConstants.kClawReverse);
        distanceSensor = new Rev2mDistanceSensor(Port.kOnboard);
        distanceSensor.setAutomaticMode(true);
        distanceSensor.setEnabled(true);
        distanceSensor.setDistanceUnits(Unit.kMillimeters);
    }
    
    @Override
    public void periodic() {
        String solValue;
        if (clawSolenoid.get() == Value.kForward) {
            solValue = "Forward";
            clawOpen = true;
        } else {
            solValue = "Reverse";
            clawOpen = false;
        }
        SmartDashboard.putNumber("Distance!", distanceSensor.getRange());

        SmartDashboard.putString("Claw Value", solValue);
    }

    public boolean isOpen() {
        return clawOpen;
    }

    public double getDistance() {
        return distanceSensor.getRange();
    }

    public boolean inRange() {
        // .25 * 600
        if (distanceSensor.getRange() < (Constants.drivePower * 600) && distanceSensor.getRange() > 0) {
            SmartDashboard.putBoolean("In Range for Claw", true);
            return true;
        }
        SmartDashboard.putBoolean("In Range for Claw", true);
        return false;
    }

    public void open(){
        clawSolenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void close(){
        clawSolenoid.set(DoubleSolenoid.Value.kReverse);
    }




}
