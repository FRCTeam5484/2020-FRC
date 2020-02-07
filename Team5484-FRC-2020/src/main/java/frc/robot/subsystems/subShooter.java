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
  private final CANSparkMax shooterMaster = new CANSparkMax(ShooterMotors.kShooterMasterMotor, MotorType.kBrushless);
  private final CANSparkMax shooterSlave = new CANSparkMax(ShooterMotors.kShooterSlaveMotor, MotorType.kBrushless);

  public subShooter() {
    // Limits Amps
    shooterSlave.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    shooterMaster.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    // Inverts motors
    shooterSlave.setInverted(ShooterMotors.invertRMotor);
    shooterMaster.setInverted(ShooterMotors.invertLMotor);
    shooterSlave.follow(shooterMaster);
  }

  public void Shoot(double speed) {
    shooterMaster.set(speed);
  }

  @Override
  public void periodic() {
  }
}
