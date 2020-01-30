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

public class subLED extends SubsystemBase {
  /**
   * Creates a new subLED.
   */
  private final AddressableLED m_led = new AddressableLED(9);
  private AddressableLEDBuffer m_buffer = new AddressableLEDBuffer(150);
  private int m_rainbowFirstPixelHue;
  public subLED() {
    m_led.setLength(m_buffer.getLength());
    m_led.setData(m_buffer);
    m_led.start();
  }

  private void rainbow() {
    for (int i = 0; i < m_buffer.getLength(); i++) {
      final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_buffer.getLength())) % 180;
      m_buffer.setHSV(i, hue, 255, 128);
    }
    m_rainbowFirstPixelHue += 3;
    m_rainbowFirstPixelHue %= 180;
  }

  @Override
  public void periodic() {
    rainbow();
    m_led.setData(m_buffer);
  }
}
