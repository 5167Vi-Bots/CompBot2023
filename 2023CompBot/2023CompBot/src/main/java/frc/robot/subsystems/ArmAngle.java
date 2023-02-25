package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Ports.ArmConstants;

public class ArmAngle extends SubsystemBase{
    private WPI_TalonFX armAngle;

    public ArmAngle() {
        armAngle = new WPI_TalonFX(ArmConstants.kArmAngle);
        armAngle.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        armAngle.setNeutralMode(NeutralMode.Brake);
        armAngle.setInverted(true);
        armAngle.setSensorPhase(true);
        armAngle.configClosedLoopPeakOutput(0, 0.25);
        armAngle.config_kP(0, 0.1);
    }

    public void resetEncoder() {
        armAngle.setSelectedSensorPosition(0);
    }
    
    @Override
    public void periodic() {
        //setPosition(0);
        SmartDashboard.putNumber("Arm Angle Position", getPosition());
        SmartDashboard.updateValues();
    }

    public void setSpeed(double speed){
        armAngle.set(ControlMode.PercentOutput, speed);
    }

    public void setPosition(int position){
        armAngle.set(ControlMode.Position, position);
    }

    public double getPosition() {
        return armAngle.getSelectedSensorPosition();
    }

    public boolean isMoving() {
        return (armAngle.getClosedLoopError(0)) > 1250;
    }

    public void stop() {
        setSpeed(0);
    }

    public void home(){
        setPosition(0);
    }

    public void low(){
        setPosition(-75400);
    }

    public void med(){
        setPosition(-48700);
    }

    public void high(){
        setPosition(-36500);
    }


}