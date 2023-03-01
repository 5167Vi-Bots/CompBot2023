package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonImuJNI;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Ports.DriveConstants;

public class DriveSubsystem extends SubsystemBase{
    private MotorControllerGroup leftGroup, rightGroup;
    private DifferentialDrive driveTrain;
    private WPI_TalonFX frontLeft, frontRight, backLeft, backRight;
    //private final double ticksPerInch = 2048; //1365 208
    private final double driveFeedForward = 0.225;// 0.235 does move 0.225 doesn't
    private final double steerFeedForward = 0.16; // 0.24, 0.16
    private final double drive_kP = 0.03;
    private final double steer_kP = 0.03;
    private final int ticksPerInch = 1050;
    public PigeonIMU pigeon;
    //private Pigeon2 pigeon; Compbot has a pigeon 2

    public DriveSubsystem(){
        frontLeft = new WPI_TalonFX(DriveConstants.kFrontLeftPort);
        backLeft = new WPI_TalonFX(DriveConstants.kBackLeftPort);
        frontRight = new WPI_TalonFX(DriveConstants.kFrontRightPort);
        backRight = new WPI_TalonFX(DriveConstants.kBackRightPort);

        frontLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        frontRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        backLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);
        backRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 15);

        frontRight.setInverted(true);
        backRight.setInverted(true);

        leftGroup = new MotorControllerGroup(frontLeft, backLeft);
        rightGroup = new MotorControllerGroup(frontRight, backRight);

        driveTrain = new DifferentialDrive(leftGroup, rightGroup);
        
        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        // frontLeft.configClosedLoopPeakOutput(0, .3);
        // backLeft.configClosedLoopPeakOutput(0, .3);
        // frontRight.configClosedLoopPeakOutput(0, .3);
        // backRight.configClosedLoopPeakOutput(0, .3);

        pigeon = new PigeonIMU(0);
        //pigeon = new Pigeon2(0);
    }

    public void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }

    public void setPosition(int position) {
        frontLeft.set(ControlMode.Position, position);
        frontRight.set(ControlMode.Position, position);
        backLeft.set(ControlMode.Position, position);
        backRight.set(ControlMode.Position, position);
    }

    public double getPosition() {
        return (frontLeft.getSelectedSensorPosition() + frontRight.getSelectedSensorPosition()) / 2;
    }

    public void drive(double x, double z){
        driveTrain.arcadeDrive(x, z);
    }

    public double getDriveFF(){
        return driveFeedForward;
    }

    public double getSteerFF(){
        return steerFeedForward;
    }

    public double getYaw() {
        return pigeon.getYaw(); //replace with AHRS
    }

    public double getRoll() {
        return pigeon.getRoll(); //replace with AHRS
    }

    public double getPitch() {
        return pigeon.getPitch(); //replace with AHRS
    }

    @Override
    public void periodic()
    {
        SmartDashboard.putNumber("Yaw", getYaw());
        SmartDashboard.putNumber("Roll", getRoll());
        SmartDashboard.putNumber("Pitch", getPitch());

        SmartDashboard.putNumber("LF Ticks", frontLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("LB Ticks", backLeft.getSelectedSensorPosition());
        SmartDashboard.putNumber("RF Ticks", frontRight.getSelectedSensorPosition());
        SmartDashboard.putNumber("RB Ticks", backRight.getSelectedSensorPosition());

        SmartDashboard.putNumber("DriveSubsystem Position", getPosition());

    }

    public void holdAngle(double drive, int angle){
        double error = -(getYaw() - angle);
        double rotate = error * steer_kP;

        drive(drive, rotate);
    }

    public void driveForward(int inches) {
        int position = inches * ticksPerInch;
        setPosition(position);
    }

}
