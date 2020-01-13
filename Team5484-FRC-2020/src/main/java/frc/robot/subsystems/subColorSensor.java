package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colors;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;


public class subColorSensor extends SubsystemBase {
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorSensor;
  private final ColorMatch m_colorMatcher;
  
  private String colorString = "Unknown";

  public subColorSensor() {
    m_colorSensor = new ColorSensorV3(i2cPort);
    m_colorMatcher = new ColorMatch();
    m_colorMatcher.addColorMatch(Colors.kBlueTarget);
    m_colorMatcher.addColorMatch(Colors.kGreenTarget);
    m_colorMatcher.addColorMatch(Colors.kRedTarget);
    m_colorMatcher.addColorMatch(Colors.kYellowTarget);
  }

  @Override
  public void periodic() {
    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == Colors.kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == Colors.kRedTarget) {
      colorString = "Red";
    } else if (match.color == Colors.kGreenTarget) {
      colorString = "Green";
    } else if (match.color == Colors.kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
  }
  public String GetColor()
  {
    return colorString;
  }
}
