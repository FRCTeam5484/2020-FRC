package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
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
  private String gameColor = "Unknown";
  private String detectedColor = "NA";

  public subColorWheel() {
    colorMatcher.addColorMatch(Colors.kBlueTarget);
    colorMatcher.addColorMatch(Colors.kGreenTarget);
    colorMatcher.addColorMatch(Colors.kRedTarget);
    colorMatcher.addColorMatch(Colors.kYellowTarget); 
    wheelMotor.restoreFactoryDefaults();
  }

  @Override
  public void periodic() {
    //SmartDashboard.putString("Color Detected: ", GetColor());
  }

  public final void turnToColor(){
      getGameData();
      if(gameColor == "NA") {
        turnWheel(0);
      }
      else if(gameColor != detectedColor){
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

  public final void GetColor(){
    final ColorMatchResult match = colorMatcher.matchClosestColor(colorSensor.getColor());
    
    if (match.color == Colors.kBlueTarget) {
      detectedColor = "Blue";
    } else if (match.color == Colors.kRedTarget) {
      detectedColor = "Red";
    } else if (match.color == Colors.kGreenTarget) {
      detectedColor = "Green";
    } else if (match.color == Colors.kYellowTarget) {
      detectedColor = "Yellow";
    } else {
      detectedColor = "NA";
    }
  }
  public void getGameData(){
    String gameData = DriverStation.getInstance().getGameSpecificMessage();
    if(gameData.length() > 0)
    {
      switch (gameData.charAt(0))
      {
        case 'B' :
          gameColor = "Blue";
        case 'G' :
          gameColor = "Green";
        case 'R' :
          gameColor = "Red";
        case 'Y' :
          gameColor = "Yellow";
        default :
          gameColor = "NA";
      }
    }
  }
}