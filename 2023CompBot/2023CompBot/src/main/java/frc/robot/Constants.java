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
      public static final int TimerBlinkenPWM = 0;
      public static final int MessageBlinkenPWM = 8;
    }

  }




public static final class LightConstants {

  public enum LEDColors
  {
    SolidPurple  ( .91),
    SolidYellow  ( .69),
    FirePattern  ( -0.59),
    SolidDarkBlue  ( 0.85),
    SolidRed  ( 0.61),
    SolidGreen  ( 0.77),
   
    DarkRed  ( 0.59),
    BreathingRed  ( -0.17),
    HeartbeatRed  ( -0.25),
    StrobeRed  ( -0.11),
   
    DarkBlue  ( 0.85),
    BreathingBlue  ( -0.15),
    HeartbeatBlue  ( -0.23),
    StrobeBlue  ( -0.09),
   
    White  ( 0.93),
    BreathingWhite  ( -0.13),
    HeartbeatWhite  ( -0.21),
    StrobeWhite  ( -0.05),


    //From REV list for the Blinken
Rainbow_Rainbow_Palette (-0.99),
Rainbow_Party_Palette (-0.97),
Rainbow_Ocean_Palette (-0.95),
Rainbow_Lave_Palette (-0.93),
Rainbow_Forest_Palette (-0.91),
Rainbow_with_Glitter (-0.89),
Confetti (-0.87),
Shot_Red (-0.85),
Shot_Blue (-0.83),
Shot_White (-0.81),
Sinelon_Rainbow_Palette (-0.79),
Sinelon_Party_Palette (-0.77),
Sinelon_Ocean_Palette (-0.75),
Sinelon_Lava_Palette (-0.73),
Sinelon_Forest_Palette (-0.71),
Beats_per_Minute_Rainbow_Palette (-0.69),
Beats_per_Minute_Party_Palette (-0.67),
Beats_per_Minute_Ocean_Palette (-0.65),
Beats_per_Minute_Lava_Palette (-0.63),
Beats_per_Minute_Forest_Palette (-0.61),
Fire_Medium (-0.59),
Fire_Large (-0.57),
Twinkles_Rainbow_Palette (-0.55),
Twinkles_Party_Palette (-0.53),
Twinkles_Ocean_Palette (-0.51),
Twinkles_Lava_Palette (-0.49),
Twinkles_Forest_Palette (-0.47),
Color_Waves_Rainbow_Palette (-0.45),
Color_Waves_Party_Palette (-0.43),
Color_Waves_Ocean_Palette (-0.41),
Color_Waves_Lava_Palette (-0.39),
Color_Waves_Forest_Palette (-0.37),
Larson_Scanner_Red (-0.35),
Larson_Scanner_Gray (-0.33),
Light_Chase_Red (-0.31),
Light_Chase_Blue (-0.29),
Light_Chase_Gray (-0.27),
Heartbeat_Red (-0.25),
Heartbeat_Blue (-0.23),
Heartbeat_White (-0.21),
Heartbeat_Gray (-0.19),
Breath_Red (-0.17),
Breath_Blue (-0.15),
Breath_Gray (-0.13),
Strobe_Red (-0.11),
Strobe_Blue (-0.09),
Strobe_Gold (-0.07),
Strobe_White (-0.05),
Color_1_End_to_End_Blend_to_Black (-0.03),
Color_1_Larson_Scanner (-0.01),
Color_1_Light_Chase (.01),
Color_1_Heartbeat_Slow (.03),
Color_1_Heartbeat_Medium (.05),
Color_1_Heartbeat_Fast (.07),
Color_1_Breath_Slow (.09),
Color_1_Breath_Fast (.11),
Color_1_Shot (.13),
Color_1_Strobe (.15),
Color_2_End_to_End_Blend_to_Black (.17),
Color_2_Larson_Scanner (.19),
Color_2_Light_Chase (.21),
Color_2_Heartbeat_Slow (.23),
Color_2_Heartbeat_Medium (.25),
Color_2_Heartbeat_Fast (.27),
Color_2_Breath_Slow (.29),
Color_2_Breath_Fast (.31),
Color_2_Shot (.33),
Color_2_Strobe (.35),
Sparkle_Color_1_on_Color_2 (.37),
Sparkle_Color_2_on_Color_1 (.39),
Color_Gradient_Color_1_and_2 (.41),
Beats_per_Minute_Color_1_and_2 (.43),
End_to_End_Blend_Color_1_to_2 (.45),
End_to_End_Blend (.47),
Color_1_and_Color_2_no_blending (.49),
Twinkles_Color_1_and_2 (.51),
Color_Waves_Color_1_and_2 (.53),
Sinelon_Color_1_and_2 (.55),
Solid_Hot_Pink (.57),
Solid_Dark_red (.59),
Solid_Red (.61),
Solid_Red_Orange (.63),
Solid_Orange (.65),
Solid_Gold (.67),
Solid_Yellow (.69),
Solid_Lawn_Green (.71),
Solid_Lime (.73),
Solid_Dark_Green (.75),
Solid_Green (.77),
Solid_Blue_Green (.79),
Solid_Aqua (.81),
Solid_Sky_Blue (.83),
Solid_Dark_Blue (.85),
Solid_Blue (.87),
Solid_Blue_Violet (.89),
Solid_Violet (.91),
Solid_White (.93),
Solid_Gray (.95),
Solid_Dark_Gray (.97),
Solid_Black (.99);

    public final double PWMValue;
    LEDColors(double PWMValue)
    {
      this.PWMValue = PWMValue;
    }
   
  }

}

}

