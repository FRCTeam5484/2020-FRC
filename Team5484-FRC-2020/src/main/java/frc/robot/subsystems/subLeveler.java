/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subLeveler extends SubsystemBase {
  /**
   * Creates a new subLeveler.
   */
  private CANSparkMax leveler;
  
  public subLeveler() {
    leveler = new CANSparkMax(Sparks.kLeveler, MotorType.kBrushless);
    leveler.setInverted(MotorDirections.kInvertLeveler);
  }

  public void levelForward() {
    leveler.set(MotorSpeeds.kLevelerSpeed);
  }
  public void levelBackward() {
    leveler.set(-MotorSpeeds.kLevelerSpeed);
  }
  public void levelStop() {
    leveler.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
