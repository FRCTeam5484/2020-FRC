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
  private final CANSparkMax shooterMaster = new CANSparkMax(Sparks.kShooterMaster, MotorType.kBrushless);
  private final CANSparkMax shooterSlave = new CANSparkMax(Sparks.kShooterSlave, MotorType.kBrushless);
  private final CANSparkMax turret = new CANSparkMax(Sparks.kTurret, MotorType.kBrushless);

  public subShooter() {
    shooterSlave.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    shooterMaster.setSmartCurrentLimit(ShooterMotors.kAmpLimit);
    turret.setInverted(MotorDirections.kInvertTurret);
    shooterMaster.setInverted(MotorDirections.kInvertShooterMaster);

    shooterSlave.follow(shooterMaster, MotorDirections.kInvertShooterSlave);
  }

  public void shoot() {
    shooterMaster.set(ShooterMotors.kShootSpeed);
  }
  public void shoot(double speed) {
    shooterMaster.set(speed);
  }
  public void stopShoot() {
    shooterMaster.set(0);
  }

  public void turretClock() {
    turret.set(ShooterMotors.kTurretSpeed);
  }
  public void turretCounter() {
    turret.set(-ShooterMotors.kTurretSpeed);
  }
  public void turretStop() {
    turret.set(0);
  }

  public void setTurret(double speed) {
    turret.set(speed);
  }

  public void seekAndShoot() {
    
  }

  @Override
  public void periodic() {
  }
}
