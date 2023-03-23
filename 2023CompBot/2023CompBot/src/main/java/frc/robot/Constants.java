// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static double drivePower;

    /**
   * CamMode enumeration that allows for switching
   * between regular camera difficult to see vision targeting
   */
  public static enum CamMode {
    VISION,
    CAMERA
  }

  /**
   * LedMode enumeration that defines which leds
   * the limelight should be using
   */
  public static enum LedMode {
    PIPE_SETTING,
    OFF,
    BLINK,
    ON,
  }

  /**
   * PipeType enumeration that defines what pipeline the
   * limelight should use
   */
  public static enum PipeType {
    CONE, // TRACK CONE
    CUBE, // TRACK CUBE
    TAGS, // TRACK ALL TAGS
    POLES, // TRACK CONE POLES
    INTAKE // TRACK INTAKE STATION TAG
  }
  
  public static class Ports {

    public static class ControllerPorts {
      public static final int kDriverControllerPort = 0;
      public static final int kOperatorControllerPort = 1;
    }

      //TODO get actual ports
    public static class DriveConstants{
      public static final int kFrontLeftPort = 3;
      public static final int kBackLeftPort = 4;
      public static final int kFrontRightPort = 1;
      public static final int kBackRightPort = 2;
      public static final int kPigeonPort = 0;
    }
    
    public static class ArmConstants{
      public static final int kArmRotate = 5;
      public static final int kArmExtend = 7;
      public static final int kArmAngle = 6;
      public static final int kClawForward = 2;
      public static final int kClawReverse = 3;
    }

    public static class LEDConstants
    {
      public static final int TimerBlinkenPWM = 9;
      public static final int MessageBlinkenPWM = 8;
    }

  }




public static final class LightConstants {

public static class LEDColors
{

public static final double SolidPurple = .91;
public static final double SolidYellow = .69;
public static final double FirePattern = -0.59;
public static final double SolidDarkBlue = 0.85;
public static final double SolidRed = 0.61; 
public static final double SolidGreen = 0.77;

public static final double DarkRed = 0.59;
public static final double BreathingRed = -0.17;
public static final double HeartbeatRed = -0.25;
public static final double StrobeRed = -0.11;

public static final double DarkBlue = 0.85;
public static final double BreathingBlue = -0.15;
public static final double HeartbeatBlue = -0.23;
public static final double StrobeBlue = -0.09;

public static final double White = 0.93;
public static final double BreathingWhite = -0.13;
public static final double HeartbeatWhite = -0.21;
public static final double StrobeWhite = -0.05;
}}

}

