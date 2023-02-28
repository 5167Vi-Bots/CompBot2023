package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CamMode;
import frc.robot.Constants.LedMode;
import frc.robot.Constants.PipeType;
import frc.robot.util.XYOutputs;

public class LimelightSubsystem extends SubsystemBase{
    private NetworkTableEntry tv, tx, ty, ta, camMode, ledMode, pipeline;
    private NetworkTable limelightTable;
    private double limelightDriveCommand, limelightSteerCommand, k_drive, k_steer, k_minError, k_maxDrive;
    private boolean invertRot, invertFwd;

    public LimelightSubsystem(String name, double k_drive, double k_steer, double k_minError, double k_maxDrive, boolean invertFwd, boolean invertRot) {
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

    /**
     * Get the PipeType from the limelight
     * 
     * @return : Returns one of the following
     *    PipeType.CONE
     *    PipeType.CUBE
     *    PipeType.INTAKE (Station)
     *    PipeType.POLES
     *    PipeType.TAGS (All)
     */
    public PipeType getPipe() {
        switch((int)pipeline.getDouble(0)) {
            case 0:
                return PipeType.CONE;
            case 1:
                return PipeType.CUBE;
            case 2:
                return PipeType.INTAKE;
            case 3:
                return PipeType.POLES;
            case 4:
                return PipeType.TAGS;
            default:
                return PipeType.POLES;
        }
    }
    

    /**
     * PipeType defined as enum to set the pipeline for the limelight
     * 
     * 
     * @param pipeType : The type of pipeline to switch to
     *    0 - CONE
     *    1 - CUBE
     *    2 - INTAKE (Station)
     *    3 - POLES
     *    4 - TAGS (All)
     */
    public void setPipe(PipeType pipeType) {
        switch(pipeType) {
            case CONE:
                pipeline.setDouble(0);
                break;
            case CUBE:
                pipeline.setDouble(1);
                break;
            case INTAKE:
                pipeline.setDouble(2);
                break;
            case POLES:
                pipeline.setDouble(3);
                break;
            case TAGS:
                pipeline.setDouble(4);
                break;
            default:
                pipeline.setDouble(3); // Default is cones
                break;
        }
    }

    /**
     * DEPRECATED FOR 2023
     * @param ac : The passed in Alliance object from driver station
     * @deprecated
     */
    public void setAlliancePipe(Alliance ac) {
        // if (ac == Alliance.Red) {
        //     setPipe(1);
        // } else {
        //     setPipe(0);
        // }
    }

    /* LEDMode Values
        0: Use the ledMode set in the current pipeline
        1: Force Off
        2: Force Blink
        3: Force On
    */
    public LedMode getLedMode() {
        switch ((int)ledMode.getDouble(0)) {
            case 0:
                return LedMode.PIPE_SETTING;
            case 1:
                return LedMode.OFF;
            case 2:
                return LedMode.BLINK;
        }
        // Returns ON by default
        return LedMode.ON;
    }

    public void setLedMode(LedMode ledMode) {
        switch (ledMode) {
            case PIPE_SETTING:
                this.ledMode.setDouble(0);
                break;
            case OFF:
                this.ledMode.setDouble(1);
                break;
            case BLINK:
                this.ledMode.setDouble(2);
                break;
            default:
                this.ledMode.setDouble(3);
                break;
        }
    }

     
    /**
     * Camera Mode Values
     *   0: Camera using Vision Processing
     *   1: Driver Camera (Increases exposure, disables vision processing)
     * @return double for camMode
     */
    public CamMode getCamMode() {
        switch((int)this.camMode.getDouble(0)) {
            case 1:
                return CamMode.CAMERA;
        }

        return CamMode.VISION;
    }

    /**
     * Sets Camera Mode Values
     *   0: Camera using Vision Processing
     *   1: Driver Camera (Increases exposure, disables vision processing)
     * @param cameraMode : Enables or disables vision using camMode
     */
    public void setCamMode(CamMode camMode) {
        switch(camMode) {
            case CAMERA:
                this.camMode.setDouble(1);
                break;
            default:
                this.camMode.setDouble(0);
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

    /**
     * General purpose updateTracking
     * 
     * - Useful for being able to control complex systems through output of this function
     *  FWD - Param of other overload not necessary here since you can opt to not use either
     *  the XYOutputs.X or XYOutputs.Y of this function
     * 
     * @return : Returns a new XYOutputs function that allows the caller to control various unique subsystems using VI-Lime
     *
     */
    public XYOutputs updateTracking(double driveFF, double steerFF) {
        
        // Check if we have target before trying to follow a target
        if (!this.hasTarget()) {
            return new XYOutputs(0, 0); // return allows us to exit the function at this point without unnecessarily executing code below
        }
  
        // Find our commands (This is really our error)
        double steer_cmd = getX() * k_steer; 
        double drive_cmd = getY() * k_drive;

        /* Drive FeedForward Algorithm 
            - If moving backwards: SUBTRACT driveFF
            - Else If moving forwards: ADD driveFF
            - Else: Driving is Complete
        */
        if (getY() < -k_minError) {
            drive_cmd -= driveFF;
        } else if (getY() > k_minError) {
            drive_cmd += driveFF;
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
            steer_cmd -= steerFF;
        } else if (getX() > k_minError) {
            steer_cmd += steerFF;
        } else {
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

        return new XYOutputs(limelightDriveCommand, limelightSteerCommand); // Return new values from the VI-Lime to be used as generic PID style output
    }

    /**
     * Calculate the distance the limelight is and return a tick amount to move
     * 
     * @param ticksPerInch : The number of ticks per inch in the mecanism we are trying to move
     * @param mountAngleDegrees : The amount of degrees back the limelight is from being perfectly vertical
     * @param lensHeightInches : The distance from the center of the limelight camera lens to the floor in inches
     * @param targetHeightInches : The distance from the target to the floor in inches
     * 
     * @return Gives the distance from the limelight and the target in ticks relative to the controlling mechanism
     */
    public double calculateDistanceAsTicks(int ticksPerInch, double mountAngleDegrees, double lensHeightInches, double targetHeightInches) {

        // Check if we have target before trying to follow a target
        if (!this.hasTarget()) {
            return 0.0; // return allows us to exit the function at this point without unnecessarily executing code below
        }

        double angleToTargetDegrees = mountAngleDegrees + getY();
        double angleToTargetRadians = angleToTargetDegrees * (3.14159 / 180.0);

        // Calculate distance
        return (targetHeightInches - lensHeightInches)/Math.tan(angleToTargetRadians);
    }

}