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
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.AnalogSensors;
import frc.robot.Constants.DigitalSensors;
import frc.robot.Constants.IntakeMotors;

public class subIntake extends SubsystemBase {
  /**
   * Creates a new subIntake.
   */
  private CANSparkMax intakeMotor1 = new CANSparkMax(IntakeMotors.kIntake, MotorType.kBrushless);
  private CANSparkMax ballFeed = new CANSparkMax(IntakeMotors.kBallFeed, MotorType.kBrushless);
  private VictorSP window1 = new VictorSP(IntakeMotors.kWindow1);
  private AnalogInput ballSensor1;
  private AnalogInput ballSensor2;
  private AnalogInput ballSensor3;
  private AnalogInput ballSensor4;
  private AnalogInput ballSensor5;
  private AnalogInput ballSensor6;

  public subIntake() {
    ballSensor1 = new AnalogInput(AnalogSensors.kBallSensor1Port);
    ballSensor2 = new AnalogInput(AnalogSensors.kBallSensor2Port);
    ballSensor3 = new AnalogInput(AnalogSensors.kBallSensor3Port);
    ballSensor4 = new AnalogInput(AnalogSensors.kBallSensor4Port);
    ballSensor5 = new AnalogInput(AnalogSensors.kBallSensor5Port);
    ballSensor6 = new AnalogInput(AnalogSensors.kBallSensor6Port);
    intakeMotor1.setInverted(IntakeMotors.kMotor1Invert);
    window1.setInverted(IntakeMotors.kWindow1Invert);
  }

  public void runIntake() {
    intakeMotor1.set(IntakeMotors.kMotorSpeed);
  }
  public void runIntakeBackward() {
    intakeMotor1.set(-IntakeMotors.kMotorSpeed);
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

  public void runBallFeedIn(){
    ballFeed.set(-IntakeMotors.kBallFeedSpeed);
  }

  public void runBallFeedOut() {
    ballFeed.set(IntakeMotors.kBallFeedSpeed);
  }

  public void stopBallFeed() {
    ballFeed.set(0);
  }

  public void runWindowUp() {
    window1.set(IntakeMotors.kWindowSpeed);
  }
  public void runWindowDown() {
    window1.set(-IntakeMotors.kWindowSpeed);
  }
  public void stopWindow() {
    window1.set(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Ball Sensor 1", ballSensor1.getValue());
    SmartDashboard.putNumber("Ball Sensor 2", ballSensor2.getValue());
    SmartDashboard.putNumber("Ball Sensor 3", ballSensor3.getValue());
    SmartDashboard.putNumber("Ball Sensor 4", ballSensor4.getValue());
    SmartDashboard.putNumber("Ball Sensor 5", ballSensor5.getValue());
    SmartDashboard.putNumber("Ball Sensor 6", ballSensor6.getValue());
  }
}
