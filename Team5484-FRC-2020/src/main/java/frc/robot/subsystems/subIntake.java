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
  // private AnalogInput ballSensor1;
  // private AnalogInput ballSensor2;
  // private AnalogInput ballSensor3;
  // private AnalogInput ballSensor4;
  // private AnalogInput ballSensor5;
  // private AnalogInput ballSensor6;
  private int ballSensor1Active;
  private int ballSensor2Active;
  private int ballSensor3Active;
  private int ballSensor4Active;
  private int ballSensor5Active;
  private int ballSensor6Active;
  private int ballCount = 0;
  private int ballPosition = 0;

  public subIntake() {
    // ballSensor1 = new AnalogInput(AnalogSensors.kBallSensor1Port);
    // ballSensor2 = new AnalogInput(AnalogSensors.kBallSensor2Port);
    // ballSensor3 = new AnalogInput(AnalogSensors.kBallSensor3Port);
    // ballSensor4 = new AnalogInput(AnalogSensors.kBallSensor4Port);
    // ballSensor5 = new AnalogInput(AnalogSensors.kBallSensor5Port);
    // ballSensor6 = new AnalogInput(AnalogSensors.kBallSensor6Port);
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

  public void increasePosition() {
    // ballPosition++;
  }

  public void runAutoFeed() {

    // if (ballSensor4Active != 1) {
    //   RunBallFeed();
    // }
    // else {
    //   stopBallFeed();
    // }
    // switch (ballPosition) {
    //   case 0:
    //     if (ballSensor4.getValue() > 8) {
    //       RunBallFeed();
    //     }
    //     else {
    //       stopBallFeed();
    //     }
    //     break;
    //   case 1:
    //     if (ballSensor3.getValue() > 8) {
    //       RunBallFeed();
    //     }
    //     else {
    //       stopBallFeed();
    //     }
    //     break;
    //   case 2:
    //     if (ballSensor2.getValue() > 8) {
    //       RunBallFeed();
    //     }
    //     else {
    //       stopBallFeed();
    //     }
    //     break;
    //   case 3:
    //     if (ballSensor1.getValue() > 8) {
    //       RunBallFeed();
    //     }
    //     else {
    //       stopBallFeed();
    //     }
    //     break;
    //   default:
    //     break;
    // }
    
  //   if (ballSensor3Active != 1) {
  //     RunBallFeed();
  //   }
  //   else {
  //     stopBallFeed();
  //   }
  //   if (ballSensor2Active != 1) {
  //     RunBallFeed();
  //   }
  //   else {
  //     stopBallFeed();
  //   }
  //   if (ballSensor1Active != 1) {
  //     RunBallFeed();
  //   }
  //   else {
  //     stopBallFeed();
  //   }
  }

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

  public int getBalls() {
    return ballCount;
  }
  // private void UpdateBallCount() {
  //   if (ballSensor1.getValue() < 8)
  //     ballSensor1Active = 1;
  //   else
  //     ballSensor1Active = 0;
  //   if (ballSensor2.getValue() < 8)
  //     ballSensor2Active = 1;
  //   else
  //     ballSensor2Active = 0;
  //   if (ballSensor3.getValue() < 8)
  //     ballSensor3Active = 1;
  //   else
  //     ballSensor3Active = 0;
  //   if (ballSensor4.getValue() < 8)
  //     ballSensor4Active = 1;
  //   else
  //     ballSensor4Active = 0;
  //   if (ballSensor5.getValue() < 8)
  //     ballSensor5Active = 1;
  //   else
  //     ballSensor5Active = 0;
  //   if (ballSensor6.getValue() < 8)
  //     ballSensor6Active = 1;
  //   else
  //     ballSensor6Active = 0;
  //   ballCount = ballSensor1Active + ballSensor2Active + ballSensor3Active + ballSensor4Active + ballSensor5Active + ballSensor6Active;
  // }

  @Override
  public void periodic() {
    // UpdateBallCount();
    SmartDashboard.putNumber("Ball Count", ballCount);
  }
}