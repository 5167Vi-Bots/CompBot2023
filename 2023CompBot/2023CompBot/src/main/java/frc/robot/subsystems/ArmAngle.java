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
    public double home = 0;
    public double low = -82500;
    public double med = -43500;
    public double high = -39500;
    public double intake = -33800;

    public ArmAngle() {
        armAngle = new WPI_TalonFX(ArmConstants.kArmAngle);
        armAngle.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        armAngle.setNeutralMode(NeutralMode.Brake);
        armAngle.setInverted(true);
        armAngle.setSensorPhase(true);
        armAngle.configClosedLoopPeakOutput(0, 0.75); //0.6
        armAngle.selectProfileSlot(0, 0);
        armAngle.configMotionAcceleration(12000); //5000 
        armAngle.configMotionCruiseVelocity(14000); //6000
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
        return Math.abs(Math.abs(armAngle.getClosedLoopTarget()) - Math.abs(armAngle.getSelectedSensorPosition(0))) > 5000;
    }

    public double targetPosition() {
        return armAngle.getClosedLoopTarget(0);
    }

    public void stop() {
        setSpeed(0);
    }

    public void home(){
        doMagic(0);
    }

    public void low(){
        // Dear programmers: T'is Austin. Change this value by increasing ticks in the negative direction Ex. (-75400 to -78000)
        doMagic(-82500);
    }

    public void med(){
        doMagic(-43500); // 39
    }

    public void high(){
        doMagic(-39500); // 40500
    }

    public void intake() {
        doMagic(-33800);
    }


}