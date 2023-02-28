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
        armAngle.setNeutralMode(NeutralMode.Coast);
        armAngle.setInverted(true);
        armAngle.setSensorPhase(true);
        armAngle.configClosedLoopPeakOutput(0, 0.6);
        armAngle.selectProfileSlot(0, 0);
        armAngle.configMotionAcceleration(5000);
        armAngle.configMotionCruiseVelocity(6000);
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

    public void doMagic(int position) {
        armAngle.set(ControlMode.MotionMagic, position);
    }

    public void setPosition(int position){
        armAngle.set(ControlMode.Position, position);
    }

    public double getPosition() {
        return armAngle.getSelectedSensorPosition();
    }

    public boolean isMoving() {
        return Math.abs(armAngle.getActiveTrajectoryVelocity()) > 100;
    }

    public void stop() {
        setSpeed(0);
    }

    public void home(){
        doMagic(0);
    }

    public void low(){
        doMagic(-75400);
    }

    public void med(){
        doMagic(-38000);
    }

    public void high(){
        doMagic(-34500);
    }


}