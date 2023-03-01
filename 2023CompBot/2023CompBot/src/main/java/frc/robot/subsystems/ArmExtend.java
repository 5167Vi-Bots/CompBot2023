package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Ports.ArmConstants;

public class ArmExtend extends SubsystemBase{
    private WPI_TalonFX armExtend;

    public ArmExtend() {
        armExtend = new WPI_TalonFX(ArmConstants.kArmExtend);
        armExtend.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        armExtend.setNeutralMode(NeutralMode.Coast);
        armExtend.setInverted(false);
        armExtend.setSensorPhase(false);
        armExtend.configClosedLoopPeakOutput(0, 0.6);
        armExtend.config_kP(0, 0.1);
        armExtend.selectProfileSlot(0, 0);
        armExtend.configMotionAcceleration(8000);
        armExtend.configMotionCruiseVelocity(8000);
    }

    public void resetEncoder() {
        armExtend.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Extend Position", getPosition());
        SmartDashboard.updateValues();
    }

    public void doMagic(int position) {
        armExtend.set(ControlMode.MotionMagic, position);
    }

    public void setSpeed(double speed){
        armExtend.set(ControlMode.PercentOutput, speed);
    }

    public void setPosition(int position){
        armExtend.set(ControlMode.Position, position);
    }

    public double getPosition() {
        return armExtend.getSelectedSensorPosition();
    }

    public void stop() {
        setSpeed(0);
    }

    public boolean isMoving() {
        return Math.abs(armExtend.getActiveTrajectoryVelocity()) > 100;
    }

    public void home(){
        doMagic(0);
    }

    public void low(){
        doMagic(-0);
    }

    public void med(){
        doMagic(-76000);
    }

    public void high(){
        doMagic(-170400); //max 175000 ? :140000
    }

    public void intake() {
        doMagic(-60000);
    }


}
