/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LED;

public class subLED extends SubsystemBase {
  /**
   * Creates a new subLED.
   */
  private final AddressableLED m_led = new AddressableLED(9);
  private AddressableLEDBuffer m_buffer = new AddressableLEDBuffer(150);
  private int m_rainbowFirstPixelHue;
  private String colorStatus = "";
  private String detectedColor = "";
  private int customRed;
  private int customBlue;
  private int customGreen;
  private int timer;
  private int lastTime;

  public subLED() {
    timer = 0;
    m_led.setLength(m_buffer.getLength());
    m_led.setData(m_buffer);
    m_led.start();
  }

  private void ResetTimer() {
    timer = 0;
  }

  public void setLEDStatus(String status) {
    if (status != "custom")
      colorStatus = status;
  }
  public void setLEDStatus(String status, String colorWheelValue) {
    if (status != "custom")
      colorStatus = status;
    detectedColor = colorWheelValue;
  }
  public void setLEDStatus(int Red, int Blue, int Green) {
    colorStatus = "custom";
    customRed = Red;
    customBlue = Blue;
    customGreen = Green;
  }

  private void Rainbow() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_buffer.getLength())) % 180;
      m_buffer.setHSV(i, hue, 255, 128);
    }
    m_rainbowFirstPixelHue += 3;
    m_rainbowFirstPixelHue %= 180;
  }

  private void GreenLightsaber() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      if (timer > LED.timerInterval + lastTime) {
        m_buffer.setRGB(i, 0, 255, 0);
        lastTime = timer;
      }
      else {
        i--;
      }
    }
  }
  
  private void Blue() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, 0, 0, 255);
    }   
  }

  private void Green() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, 0, 255, 0);
    }   
  }

  private void Yellow() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, 0, 255, 255);
    }   
  }

  private void Red() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, 255, 0, 0);
    }   
  }

  private void CustomColor(int r, int g, int b) {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      m_buffer.setRGB(i, r, g, b);
    }   
  }

  @Override
  public void periodic() {
    if (timer > 2147483600)
      ResetTimer();
    timer++;
    switch (colorStatus) {
      case "rainbow":
        Rainbow();
        break;
      case "blue":
        Blue();
        break;
      case "red":
        Red();
        break;
      case "yellow":
        Yellow();
        break;
      case "green":
        Green();
        break;
      case "green lightsaber":
        GreenLightsaber();
        break;
      case "custom":
        CustomColor(customRed, customBlue, customGreen);
        break;
      default:
        Rainbow();
        break;
    }
    m_led.setData(m_buffer);
  }
}
