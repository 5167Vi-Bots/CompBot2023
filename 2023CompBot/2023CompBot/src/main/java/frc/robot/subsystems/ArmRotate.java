package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Ports.ArmConstants;

import java.util.ResourceBundle.Control;

public class ArmRotate extends SubsystemBase{
    private WPI_TalonFX armRotate;

    public ArmRotate() {
        armRotate = new WPI_TalonFX(ArmConstants.kArmRotate);
        armRotate.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        armRotate.setNeutralMode(NeutralMode.Brake);
        armRotate.setInverted(false);
        armRotate.setSensorPhase(false);
        armRotate.configClosedLoopPeakOutput(0, 0.5);
        armRotate.selectProfileSlot(0, 0);
        armRotate.configMotionAcceleration(1500);
        armRotate.configMotionCruiseVelocity(3000);
        armRotate.config_kP(0, 0.1); // 0.5
        armRotate.configForwardSoftLimitEnable(false);
        armRotate.configReverseSoftLimitEnable(false);
    }

    public void resetEncoder() {
        armRotate.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic() {
        // setPosition(0);
        SmartDashboard.putNumber("Arm Rotate Position", getPosition());
        SmartDashboard.updateValues();
    }

    public void setSpeed(double speed){
        armRotate.set(ControlMode.PercentOutput, speed);
    }
    
    public void stop() {
        setSpeed(0);
    }

    public void setPosition(int position){
        // TODO check that the position is within bounds
        // if (position > 3000) { MAX BOUNDS GOES HERE
        //     position = 3000;
        // } else if (position < -3000){ MIN BOUNDS GOES HERE
        //     position = -3000;
        // }
        armRotate.set(ControlMode.Position, position);
    }
    
    public void doMagic(int position) {
        armRotate.set(ControlMode.MotionMagic, position);
    }

    public double getPosition() {
        return armRotate.getSelectedSensorPosition();
    }

    public boolean isMoving() {
        return Math.abs(armRotate.getActiveTrajectoryVelocity()) > 100;
    }

    public void home(){
        doMagic(0);
    }

    public void front() {
        doMagic(0);
    }

    public void right() {
        doMagic(-34500); // max positive 7500
    }

    public void left() {
        doMagic(34500); 
    }

    public void back() {
        doMagic(-68500); //max neg -13800
    }


}