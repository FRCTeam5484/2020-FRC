package frc.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colors;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

public class subColorWheel extends SubsystemBase {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  public subColorWheel() {
    m_colorMatcher.addColorMatch(Colors.kBlueTarget);
    m_colorMatcher.addColorMatch(Colors.kGreenTarget);
    m_colorMatcher.addColorMatch(Colors.kRedTarget);
    m_colorMatcher.addColorMatch(Colors.kYellowTarget); 
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Color Detected: ", GetColor());
  }

  public final String GetColor(){
    final Color detectedColor = m_colorSensor.getColor();
    final ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
    
    if (match.color == Colors.kBlueTarget) {
      return "Blue";
    } else if (match.color == Colors.kRedTarget) {
      return "Red";
    } else if (match.color == Colors.kGreenTarget) {
      return "Green";
    } else if (match.color == Colors.kYellowTarget) {
      return "Yellow";
    } else {
      return "Unknown";
    }
  }
}