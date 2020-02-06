/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class subShooter extends SubsystemBase {
  /**
   * Creates a new subShooter.
   */
  private final CANSparkMax leftShooter = new CANSparkMax(ShooterMotors.kLeftMotor, MotorType.kBrushless);
  private final CANSparkMax rightShooter = new CANSparkMax(ShooterMotors.kRightMotor, MotorType.kBrushless);

  public subShooter() {
    // Limits Amps
    rightShooter.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    leftShooter.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    // Inverts motors
    rightShooter.setInverted(ShooterMotors.invertRMotor);
    leftShooter.setInverted(ShooterMotors.invertLMotor);
  }

  public void Shoot(double speed) {
    leftShooter.set(ShooterMotors.kLeftSpeed);
    leftShooter.set(speed);
    //rightShooter.set(speed);
  }

  @Override
  public void periodic() {
  }
}
