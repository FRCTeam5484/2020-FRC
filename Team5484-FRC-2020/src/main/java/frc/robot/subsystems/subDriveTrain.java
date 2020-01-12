
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveControllers;
import frc.robot.Constants.DriveMotors;


public class subDriveTrain extends SubsystemBase {
  
  private final CANSparkMax sparkLeft1 = new CANSparkMax(DriveMotors.kLeftMotor1, MotorType.kBrushless);
  private final CANSparkMax sparkLeft2 = new CANSparkMax(DriveMotors.kLeftMotor2, MotorType.kBrushless);
  private final CANSparkMax sparkRight1 = new CANSparkMax(DriveMotors.kRightMotor1, MotorType.kBrushless);
  private final CANSparkMax sparkRight2 = new CANSparkMax(DriveMotors.kRightMotor2, MotorType.kBrushless);
  private final SpeedControllerGroup leftDrive = new SpeedControllerGroup(sparkLeft1, sparkLeft2);
  private final SpeedControllerGroup rightDrive = new SpeedControllerGroup(sparkRight1, sparkRight2);
  private final DifferentialDrive m_driveTrain = new DifferentialDrive(leftDrive, rightDrive);;
  private final XboxController driverOne = new XboxController(DriveControllers.DriverOne);

  public subDriveTrain() 
  {
      leftDrive.setInverted(true);
      rightDrive.setInverted(true);
  }

  public void TeleOp(subDriveTrain driveTrain) {
    driveTrain.m_driveTrain.tankDrive(driverOne.getY(Hand.kLeft), driverOne.getY(Hand.kRight));
  }


  @Override
  public void periodic() {
    
  }
}
