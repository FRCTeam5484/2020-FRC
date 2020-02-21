/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Lift;
import frc.robot.Constants.Sparks;

public class subLift extends SubsystemBase {
  /**
   * Creates a new subLift.
   */
  private CANSparkMax liftMaster;
  private CANSparkMax liftSlave;
  Servo servoLeftLock;
  Servo servoRightLock;

  public subLift() {
    liftMaster = new CANSparkMax(Sparks.kRightLift, MotorType.kBrushless);
    liftSlave = new CANSparkMax(Sparks.kLeftLift, MotorType.kBrushless);
    // servoLeftLock = new Servo(Lift.servoLeft);
    // servoRightLock = new Servo(Lift.servoRight);
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
    if (speed < -.2 || speed > .2)
      liftMaster.set(-.5 * speed);
    else
      liftMaster.set(0);
  }

  public void LiftLock(){
    servoLeftLock.setAngle(0);
    servoRightLock.setAngle(90);
  }
  public void LiftUnlock(){
    servoLeftLock.setAngle(90);
    servoRightLock.setAngle(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
