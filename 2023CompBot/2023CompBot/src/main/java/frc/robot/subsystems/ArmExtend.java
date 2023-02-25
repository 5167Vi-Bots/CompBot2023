package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Ports.ArmConstants;

public class ArmExtend extends SubsystemBase{
    private WPI_TalonSRX armExtend;

    public ArmExtend() {
        armExtend = new WPI_TalonSRX(ArmConstants.kArmExtend);
        armExtend.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 15);
        armExtend.setNeutralMode(NeutralMode.Brake);
        armExtend.setInverted(false);
        armExtend.setSensorPhase(false);
        armExtend.configClosedLoopPeakOutput(0, 0.6);
        armExtend.config_kP(0, 0.05);
    }

    public void resetEncoder() {
        armExtend.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Arm Extend Position", getPosition());
        SmartDashboard.updateValues();
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
        return (armExtend.getClosedLoopError(0)) > 1250;
    }

    public void home(){
        setPosition(0);
    }

    public void low(){
        setPosition(1000);
    }

    public void med(){
        setPosition(70000);
    }

    public void high(){
        setPosition(140000); //max 175000 ? :140000
    }


}
