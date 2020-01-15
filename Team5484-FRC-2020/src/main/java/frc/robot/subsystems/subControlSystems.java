package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LED;

public class subControlSystems extends SubsystemBase {
  private final PowerDistributionPanel pdp = new PowerDistributionPanel(0);
  private final AddressableLED led = new AddressableLED(LED.StrandOneID);
  private final AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(LED.StrandOneCount);
  private int rainbowFirstPixelHue;

  public subControlSystems() {

  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("PDP Voltage: ", pdp.getVoltage());
    SmartDashboard.putNumber("PDP Temp: ", pdp.getTemperature());
    SmartDashboard.putNumber("PDP Current: ", pdp.getTotalCurrent());
    SmartDashboard.putNumber("PDP Energy: ", pdp.getTotalEnergy());
  }
  public void setRed(){
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 255, 0, 0);
    }   
    led.setData(ledBuffer);
  }
  public void setBlue(){
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 0, 0, 255);
    }   
    led.setData(ledBuffer);
  }
  public void setGreen(){
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 0, 255, 0);
    }   
    led.setData(ledBuffer);
  }
  public void setYellow(){
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      ledBuffer.setRGB(i, 255, 255, 0);
    }   
    led.setData(ledBuffer);
  }
  public void SetRainbow() {
    for (var i = 0; i < ledBuffer.getLength(); i++) {
      final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
      ledBuffer.setHSV(i, hue, 255, 128);
    }
    rainbowFirstPixelHue += 3;
    rainbowFirstPixelHue %= 180;
    led.setData(ledBuffer);
  }
}
