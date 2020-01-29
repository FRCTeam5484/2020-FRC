/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Relay;

public class subTargetingLight extends SubsystemBase {
  /**
   * Creates a new subTargetingLight.
   */
  private final Relay m_relay = new Relay(0);
  public subTargetingLight() {
    
  }

  public void TurnOn() {
    m_relay.set(Relay.Value.kReverse);
  }

  public void TurnOff() {
    m_relay.set(Relay.Value.kOff);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
