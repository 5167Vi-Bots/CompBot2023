package frc.robot.util;

public class XYOutputs {
    public double x; // Limelight right+ backward- (Same as Drivetrain.Y)
    public double y; // Limelight forward+ backward- (Same as Drivetrain.X)

    public XYOutputs(double x, double y) {
      this.x = x;
      this.y = y;
    }
}
