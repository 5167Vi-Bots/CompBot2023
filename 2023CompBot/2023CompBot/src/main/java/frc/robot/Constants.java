// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.security.auth.x500.X500Principal;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  
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
      public static final int kClawForward = 1;
      public static final int kClawReverse = 2;
    }

  }

}

