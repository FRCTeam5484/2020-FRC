/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subArm extends SubsystemBase {
  /**
   * Creates a new subArm.
   */
  private VictorSP arm;
  private DigitalInput downContact;
  private DigitalInput upContact;
  private DigitalInput downContactBackup;
  private DigitalInput upContactBackup;
  
  public subArm() {
    arm = new VictorSP(Victors.kArm);
    arm.setInverted(MotorDirections.kInvertArm);
    downContact = new DigitalInput(DigitalSensors.kLimitBottomArm);
    upContact = new DigitalInput(DigitalSensors.kLimitTopArm);
    downContactBackup = new DigitalInput(DigitalSensors.kLimitBottomArmBackup);
    upContactBackup = new DigitalInput(DigitalSensors.kLimitTopArmBackup);
  }

  public void armUp() {
    arm.set(IntakeMotors.kArmUpSpeed);
  }
  public void armDown() {
    arm.set(-IntakeMotors.kArmDownSpeed);
  }
  public void armStop() {
    arm.set(0);
  }

  public void autoArm() {
    if (!downContact.get() && !downContactBackup.get()) {
      
    }
    else {
      
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
