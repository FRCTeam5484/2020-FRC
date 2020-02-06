/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeMotors;

public class subIntake extends SubsystemBase {
  /**
   * Creates a new subIntake.
   */
  private CANSparkMax intakeMotor1 = new CANSparkMax(IntakeMotors.kIntake, MotorType.kBrushless);
  private CANSparkMax ballFeed = new CANSparkMax(IntakeMotors.kBallFeed, MotorType.kBrushless);
  private VictorSP window1 = new VictorSP(IntakeMotors.kWindow1);

  public subIntake() {
    intakeMotor1.setInverted(IntakeMotors.kMotor1Invert);
    window1.setInverted(IntakeMotors.kWindow1Invert);
    ballFeed.setInverted(IntakeMotors.kBallFeedInvert);
  }

  public void runIntake() {
    intakeMotor1.set(IntakeMotors.kMotorSpeed);
  }
  public void stopIntake() {
    intakeMotor1.set(0);
  }
  // public void runIntake(double speed1) {
  //   intakeMotor1.set(speed1);
  // }
  // public void runIntake(double speed1, double speedWindow) {
  //   intakeMotor1.set(speed1);
  //   window1.set(speedWindow);
  // }

  public void runBallFeed(boolean reverse){
    if (!reverse)
      ballFeed.set(IntakeMotors.kBallFeedSpeed);
    else {
      ballFeed.set(IntakeMotors.kBallFeedSpeedReversed);
    }
  }
  public void stopBallFeed() {
    ballFeed.set(0);
  }

  public void runWindow(boolean reverse) {
    if (!reverse)
      window1.set(IntakeMotors.kWindowSpeed);
    else
      window1.set(IntakeMotors.kWindowSpeedReversed);
  }
  public void stopWindow() {
    window1.set(0);
  }

  @Override
  public void periodic() {
  }
}
