package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Colors;
import frc.robot.Constants.WheelSystem;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class subColorWheel extends SubsystemBase {

  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch colorMatcher = new ColorMatch();
  private final CANSparkMax wheelMotor = new CANSparkMax(WheelSystem.kMotor, MotorType.kBrushless);
  private CANEncoder wheelEncoder = new CANEncoder(wheelMotor);
  private String detectedColor = "NA";

  public subColorWheel() {

    colorMatcher.addColorMatch(Colors.kBlueTarget);
    colorMatcher.addColorMatch(Colors.kGreenTarget);
    colorMatcher.addColorMatch(Colors.kRedTarget);
    colorMatcher.addColorMatch(Colors.kYellowTarget); 
    colorMatcher.setConfidenceThreshold(Colors.kConfidence);
    wheelMotor.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    SmartDashboard.putString("Detected Color", GetColor());
  }

  public final void turnToColor(){

      if(getGameData() == "NA") {
        turnWheel(0);
      }
      else if(getGameData() != detectedColor){
        turnWheel(1);
      }
      else{
        turnWheel(0);
      }
  }
  public final void turnFourTimes(){
    double currentValue = wheelEncoder.getPosition();
    double targetValue = currentValue + WheelSystem.kFourTurnUnitCount;
    if(currentValue < targetValue) {
      turnWheel(1);
    }
    else{
      turnWheel(0);
    }
  }
  public final void turnWheel(double speed){
    wheelMotor.set(speed * WheelSystem.kMaxSpeed);
  }

  public final String GetColor() {
    final ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
    SmartDashboard.putString("Red", Double.toString(match.color.red));
    SmartDashboard.putString("Blue", Double.toString(match.color.blue));
    SmartDashboard.putString("Green", Double.toString(match.color.green));
    if (match.color == Colors.kBlueTarget) {
      detectedColor = "red";
    } else if (match.color == Colors.kRedTarget) {
      detectedColor = "blue";
    } else if (match.color == Colors.kGreenTarget) {
      detectedColor = "yellow";
    } else if (match.color == Colors.kYellowTarget) {
      detectedColor = "green";
    } else {
      detectedColor = "NA";
    }
    return detectedColor;
  }
  public String getGameData(){
    String gameColor = "Unknown";
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          gameColor = "blue";
        case 'G' :
          gameColor = "green";
        case 'R' :
          gameColor = "red";
        case 'Y' :
          gameColor = "yellow";
        default :
          gameColor = "NA";
      }
    }
    return gameColor;
  }
}