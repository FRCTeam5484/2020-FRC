/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeMotors;

public class subIntake extends SubsystemBase {
  /**
   * Creates a new subIntake.
   */
  private VictorSP intakeMotor1 = new VictorSP(IntakeMotors.kMotor1);
  private VictorSP window1 = new VictorSP(IntakeMotors.kWindow1);

  public subIntake() {
    intakeMotor1.setInverted(IntakeMotors.kMotor1Invert);
  }

  public void runIntake() {
    intakeMotor1.set(IntakeMotors.kMotorSpeed);
  }
  public void runIntake(double speed1) {
    intakeMotor1.set(speed1);

  }
  public void runIntake(double speed1, double speedWindow) {
    intakeMotor1.set(speed1);
    window1.set(speedWindow);
  }

  public void runWindow() {
    window1.set(IntakeMotors.kWindowSpeed);
  }

  @Override
  public void periodic() {
  }
}
