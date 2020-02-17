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
import frc.robot.Constants.Lift;
import frc.robot.Constants.Sparks;

public class subLift extends SubsystemBase {
  /**
   * Creates a new subLift.
   */
  private CANSparkMax liftMaster;
  private CANSparkMax liftSlave;

  public subLift() {
    liftMaster = new CANSparkMax(Sparks.kRightLift, MotorType.kBrushless);
    liftSlave = new CANSparkMax(Sparks.kLeftLift, MotorType.kBrushless);
    liftSlave.follow(liftMaster, true);
  }

  public void LiftUp() {
    liftMaster.set(Lift.liftSpeed);
  }
  public void LiftDown() {
    liftMaster.set(-Lift.liftSpeed);
  }
  public void LiftStop() {
    liftMaster.set(0);
  }

  public void setLift(double speed) {
    liftMaster.set(speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
