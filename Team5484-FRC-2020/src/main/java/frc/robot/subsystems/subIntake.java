/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subIntake extends SubsystemBase {
  /**
   * Creates a new subIntake.
   */
  private CANSparkMax intakeMotor1 = new CANSparkMax(Sparks.kIntake, MotorType.kBrushless);
  
  public subIntake() {
    intakeMotor1.setInverted(MotorDirections.kInvertIntake);
  }

  public void runIntake(boolean enabled) {
    if (enabled)
      intakeMotor1.set(IntakeMotors.kMotorSpeed);
  }
  public void runIntakeBackward() {
    intakeMotor1.set(-IntakeMotors.kMotorSpeed);
  }
  public void stopIntake() {
    intakeMotor1.set(0);
  }

  @Override
  public void periodic() {
  }
}