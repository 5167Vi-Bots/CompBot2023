package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase{
    private NetworkTableEntry tv, tx, ty, ta, camMode, ledMode, pipeline;
    private NetworkTable limelightTable;
    private double limelightDriveCommand, limelightSteerCommand, k_drive, k_steer, k_minError, k_maxDrive;
    private boolean invertRot, invertFwd;

    public Limelight(String name, double k_drive, double k_steer, double k_minError, double k_maxDrive, boolean invertFwd, boolean invertRot) {
        limelightTable = NetworkTableInstance.getDefault().getTable(name);
        this.k_drive = k_drive;
        this.k_steer = k_steer ;
        this.k_minError = k_minError;
        this.k_maxDrive = k_maxDrive;
        this.invertRot = invertRot;
        this.invertFwd = invertFwd;

        tv = limelightTable.getEntry("tv");
        tx = limelightTable.getEntry("tx");
        ty = limelightTable.getEntry("ty");
        ta = limelightTable.getEntry("ta");
        camMode = limelightTable.getEntry("camMode");
        ledMode = limelightTable.getEntry("ledMode");
        pipeline = limelightTable.getEntry("pipeline");
        
        limelightDriveCommand = 0.0;
        limelightSteerCommand = 0.0;
    }

    /* Visible Values (tv)
        0: No Targets Visible
        1: Target(s) Visible
    */
    public double getV() {
        return tv.getDouble(0);
    }

    // Limelight Target/Crosshair X Axis Error
    public double getX() {
        return tx.getDouble(0);
    }

    // Limelight Target/Crosshair Y Axis Error
    public double getY() {
        return ty.getDouble(0);
    }

    // Limelight Target Area
    public double getA() {
        return ta.getDouble(0);
    }

    /* Pipeline Values
        : 0-9 Pipeline currently being used by Limelight
    */
    public double getPipe() {
        return pipeline.getDouble(0);
    }

    public void setPipe(int pipe) {
        if (pipe >= 0 && pipe <= 9) {
            pipeline.setDouble(pipe);
        } else {
            System.out.println("!!! Error with limelight.setPipe INVALID PIPELINE !!!");
        }
    }

    public void setAlliancePipe(Alliance ac) {
        if (ac == Alliance.Red) {
            setPipe(1);
        } else {
            setPipe(0);
        }
    }

    /* LEDMode Values
        0: Use the ledMode set in the current pipeline
        1: Force Off
        2: Force Blink
        3: Force On
    */
    public double getLedMode() {
        return ledMode.getDouble(0);
    }

    public void setLedMode(int ledSetting) {
        if (ledSetting >= 0 && ledSetting <= 3) {
            ledMode.setDouble(ledSetting);
        } else {
            System.out.println("!!! Error with limelight.setLedMode INVALID LEDMODE !!!");
        }
    }

    /* Camera Mode Values
        0: Camera using Vision Processing
        1: Driver Camera (Increases exposure, disables vision processing)
    */
    public double getCamMode() {
        return camMode.getDouble(0);
    }

    public void setCamMode(int cameraMode) {
        if (cameraMode == 0 || cameraMode == 1) {
            camMode.setDouble(cameraMode);
        } else {
            System.out.println("!!! Error with limelight.setCamMode INVALID CAMERA MODE !!!");
        }
    }

    // Drive power being sent based on limelight target
    public double getDriveCommand() {
        return limelightDriveCommand;
    }

    // Steer power being sent based on limelight target
    public double getSteerCommand() {
        return limelightSteerCommand;
    }

    // Return boolean of whether or not limelight has valid target
    public boolean hasTarget() {
        if (getV() == 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean doneTargeting() {
        if (Math.abs(getX()) > k_minError || Math.abs(getY()) > k_minError) {
            return false;
        }
        return true;
    }

    public void updateTracking(double fwd, double strafe, DriveSubsystem driveTrain) {
        
        // Check if we have target before trying to follow a target
        if (!this.hasTarget()) {
          limelightDriveCommand = 0.0;
          limelightSteerCommand = 0.35;
          driveTrain.drive(limelightDriveCommand, limelightSteerCommand); // Safely rotate until we see a target while trying to target
          return; // return allows us to exit the function at this point without unnecessarily executing code below
        }

        driveTrain.drive(0, 0);

        // Find our commands (This is really our error)
        double steer_cmd = getX() * k_steer; 
        double drive_cmd = getY() * k_drive;

        /* Drive FeedForward Algorithm 
            - If moving backwards: SUBTRACT driveFF
            - Else If moving forwards: ADD driveFF
            - Else: Driving is Complete
        */
        if (getY() < -k_minError) {
            drive_cmd -= driveTrain.getDriveFF();
        } else if (getY() > k_minError) {
            drive_cmd += driveTrain.getDriveFF();
        } else {
            drive_cmd = 0;
        }

        /* Steer FeedForward Algorithm
            - If steering LEFT, and NOT moving in X: SUBTRACT steerFF
            - Else if steering RIGHT, and NOT moving in X: ADD steerFF
            - Else if steering LEFT, and moving in X: SUBTRACT drivingSteerFF
            - Else if steering RIGHT, and moving in X: ADD drivingSteerFF
            - Else: Steering is Complete
        */
        if (getX() < -k_minError) {
            steer_cmd -= driveTrain.getSteerFF();
        } else if (getX() > k_minError) {
            steer_cmd += driveTrain.getSteerFF();
        } else {
            System.out.println(steer_cmd);
            steer_cmd = 0;
        }
 
        // Constrain values so that the drive_cmd does not exceed maxDrive value
        if (drive_cmd > k_maxDrive) {
            drive_cmd = k_maxDrive;
        } else if (drive_cmd < -k_maxDrive) {
            drive_cmd = -k_maxDrive;
        }

        // Update final values
        if (invertRot) {
            limelightSteerCommand = -steer_cmd; // NEGEATIVE: steers LEFT | POSITIVE: steers RIGHT
        } else {
            limelightSteerCommand = steer_cmd; // NEGEATIVE: steers LEFT | POSITIVE: steers RIGHT
        }

        if (invertFwd) {
            limelightDriveCommand = -drive_cmd; // Inverted = [ NEGATIVE: drives BACKWARDS | POSITIVE: drives FORWARDS ]
        } else {
            limelightDriveCommand = drive_cmd; // Inverted = [ NEGATIVE: drives BACKWARDS | POSITIVE: drives FORWARDS ]
        }

        if (fwd == 0) {
            driveTrain.drive(limelightDriveCommand, limelightSteerCommand); // Update values through drivetrain object passed through params | NOTE: "strafe" value is unchanged by tracking algorithm
        } else {
            driveTrain.drive(fwd, limelightSteerCommand); // Update values through drivetrain object passed through params | NOTE: "strafe" value is unchanged by tracking algorithm
        }
    }

}