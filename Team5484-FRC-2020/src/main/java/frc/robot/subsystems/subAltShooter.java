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

public class subAltShooter extends SubsystemBase {
  /**
   * Creates a new subAltShooter.
   */
  private CANSparkMax topShooter = new CANSparkMax(AltShooterMotors.kShooterMotorTop, MotorType.kBrushless);
  private CANSparkMax bottomShooter = new CANSparkMax(AltShooterMotors.kShooterMotorBottom, MotorType.kBrushless);
  public subAltShooter() {
    topShooter.setInverted(AltShooterMotors.invertTMotor);
    bottomShooter.setInverted(AltShooterMotors.invertBMotor);
  }

  public void shoot(double speed) {
    topShooter.set(speed);
    bottomShooter.set(speed);
  }
  public void stop() {
    //topShooter.set(0);
    bottomShooter.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
