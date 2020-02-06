
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Constants.*;



public class subDriveTrain extends SubsystemBase {  
  private final CANSparkMax sparkLeft1 = new CANSparkMax(DriveMotors.kLeftMotor1, MotorType.kBrushless);
  private final CANSparkMax sparkLeft2 = new CANSparkMax(DriveMotors.kLeftMotor2, MotorType.kBrushless);
  private final CANSparkMax sparkRight1 = new CANSparkMax(DriveMotors.kRightMotor1, MotorType.kBrushless);
  private final CANSparkMax sparkRight2 = new CANSparkMax(DriveMotors.kRightMotor2, MotorType.kBrushless);
  private final SpeedControllerGroup leftDrive = new SpeedControllerGroup(sparkLeft1, sparkLeft2);
  private final SpeedControllerGroup rightDrive = new SpeedControllerGroup(sparkRight1, sparkRight2);
  private final DifferentialDrive driveTrain = new DifferentialDrive(leftDrive, rightDrive);
  private final Ultrasonic ultrasonic = new Ultrasonic(1, 2);
  public AHRS ahrs;

  private CANEncoder left1Encoder = new CANEncoder(sparkLeft1);
  private CANEncoder left2Encoder = new CANEncoder(sparkLeft2);
  private CANEncoder right1Encoder = new CANEncoder(sparkRight1);
  private CANEncoder right2Encoder = new CANEncoder(sparkRight2);
  private double resetLeftEncoder = 0;
  private double resetRightEncoder = 0;

  private double leftEncoderValue;
  private double rightEncoderValue;
  private double error;
  private double turnPower;


  public subDriveTrain() {
      sparkLeft1.restoreFactoryDefaults();
      sparkLeft2.restoreFactoryDefaults();
      sparkRight1.restoreFactoryDefaults();
      sparkRight2.restoreFactoryDefaults();

      sparkLeft1.setSmartCurrentLimit(DriveMotors.kAmpLimit);
      sparkLeft2.setSmartCurrentLimit(DriveMotors.kAmpLimit);
      sparkRight1.setSmartCurrentLimit(DriveMotors.kAmpLimit);
      sparkRight2.setSmartCurrentLimit(DriveMotors.kAmpLimit);

      leftDrive.setInverted(DriveMotors.leftInvert);
      rightDrive.setInverted(DriveMotors.rightInvert);

      left1Encoder = sparkLeft1.getEncoder();
      left2Encoder = sparkLeft2.getEncoder();
      right1Encoder = sparkRight1.getEncoder();
      right2Encoder = sparkRight2.getEncoder();

      try {
        ahrs = new AHRS(SPI.Port.kMXP); 
        ahrs.reset();
      } catch (RuntimeException ex ) {
          DriverStation.reportError("Error instantiating navX:  " + ex.getMessage(), true);
      }
  }

  public void tankDrive(final double left, final double right, Boolean boostEnabled) {
    if (boostEnabled) {
      driveTrain.tankDrive(left * DriveMotors.kBoostSpeed, right * DriveMotors.kBoostSpeed);
    }
    else {
      driveTrain.tankDrive(left * DriveMotors.kMaxSpeed, right * DriveMotors.kMaxSpeed);
    }
  }

  // public void DriveStraight() {
  //   if (rightEncoderValue < leftEncoderValue - DriveMotors.kTickTolerance) {
  //     driveTrain.tankDrive(DriveMotors.kLeftDriveStraightSpeed * DriveMotors.ketchup, DriveMotors.kRightDriveStraightSpeed);
  //   }
  //   else if (leftEncoderValue < rightEncoderValue - DriveMotors.kTickTolerance){
  //     driveTrain.tankDrive(DriveMotors.kLeftDriveStraightSpeed, DriveMotors.kRightDriveStraightSpeed * DriveMotors.ketchup);
  //   }
  //   else {
  //     driveTrain.tankDrive(DriveMotors.kLeftDriveStraightSpeed, DriveMotors.kRightDriveStraightSpeed);
  //   }
  // }
  public void DriveStraight() {
    leftEncoderValue = left1Encoder.getPosition() + left2Encoder.getPosition();
    rightEncoderValue = right1Encoder.getPosition() + right2Encoder.getPosition();
    error = leftEncoderValue - rightEncoderValue;
    turnPower = DriveMotors.kConstantofProportionality * error;
    driveTrain.arcadeDrive(DriveMotors.kStraightPower, turnPower, false);
  }
  public void DriveStraight(double constantOfProportionality) {
    leftEncoderValue = (left1Encoder.getPosition() + left2Encoder.getPosition()) - resetLeftEncoder;
    rightEncoderValue = (right1Encoder.getPosition() + right2Encoder.getPosition()) - resetRightEncoder;
    error = leftEncoderValue - rightEncoderValue;
    turnPower = constantOfProportionality * error;
    driveTrain.arcadeDrive(DriveMotors.kStraightPower, turnPower, false);
  }

  public void findCurrentEncoders() {
    resetLeftEncoder = left1Encoder.getPosition() + left2Encoder.getPosition();
    resetRightEncoder = right1Encoder.getPosition() + right2Encoder.getPosition();
  }

  public void AutoDrive(final double drive, final double turn) {
      driveTrain.arcadeDrive(drive, turn, true);
  }
  
  @Override
  public void periodic() {
    // leftEncoderValue = left1Encoder.getPosition() + left2Encoder.getPosition();
    // rightEncoderValue = right1Encoder.getPosition() + right2Encoder.getPosition();
    SmartDashboard.putNumber("Ultrasonic", ultrasonic.getRangeInches());


    // SmartDashboard.putNumber("Left(1) Encoder Ticks", left1Encoder.getCountsPerRevolution());
    // SmartDashboard.putNumber("Left(2) Encoder Ticks", left2Encoder.getCountsPerRevolution());
    // SmartDashboard.putNumber("Right(1) Encoder Ticks", right1Encoder.getCountsPerRevolution());
    // SmartDashboard.putNumber("Right(2) Encoder Ticks", right2Encoder.getCountsPerRevolution());
    
    /* SmartDashboard.putNumber("Left Drive Value: ", leftDrive.get());
    //Left Motor 1    
    SmartDashboard.putNumber("Left 1 Encoder Position", left1Encoder.getPosition());
    SmartDashboard.putNumber("Left 1 Encoder Velocity", left1Encoder.getVelocity());
    SmartDashboard.putNumber("Left 1 Bus Voltage", sparkLeft1.getBusVoltage());
    SmartDashboard.putNumber("Left 1 Current", sparkLeft1.getOutputCurrent());
    SmartDashboard.putNumber("Left 1 Applied Output", sparkLeft1.getAppliedOutput());
    SmartDashboard.putNumber("Left 1 Motor Temperature", sparkLeft1.getMotorTemperature());

    //Left Motor 2    
    SmartDashboard.putNumber("Left 2 Encoder Position", left2Encoder.getPosition());
    SmartDashboard.putNumber("Left 2 Encoder Velocity", left2Encoder.getVelocity());
    SmartDashboard.putNumber("Left 2 Bus Voltage", sparkLeft2.getBusVoltage());
    SmartDashboard.putNumber("Left 2 Current", sparkLeft2.getOutputCurrent());
    SmartDashboard.putNumber("Left 2 Applied Output", sparkLeft2.getAppliedOutput());
    SmartDashboard.putNumber("Left 2 Motor Temperature", sparkLeft2.getMotorTemperature());

    SmartDashboard.putNumber("Right Drive Value: ", rightDrive.get());
    //Right Motor 1    
    SmartDashboard.putNumber("Right 1 Encoder Position", right1Encoder.getPosition());
    SmartDashboard.putNumber("Right 1 Encoder Velocity", right1Encoder.getVelocity());
    SmartDashboard.putNumber("Right 1 Bus Voltage", sparkRight1.getBusVoltage());
    SmartDashboard.putNumber("Right 1 Current", sparkRight1.getOutputCurrent());
    SmartDashboard.putNumber("Right 1 Applied Output", sparkRight1.getAppliedOutput());
    SmartDashboard.putNumber("Right 1 Motor Temperature", sparkRight1.getMotorTemperature());

    //Right Motor 2    
    SmartDashboard.putNumber("Right 2 Encoder Position", right2Encoder.getPosition());
    SmartDashboard.putNumber("Right 2 Encoder Velocity", right2Encoder.getVelocity());
    SmartDashboard.putNumber("Right 2 Bus Voltage", sparkRight2.getBusVoltage());
    SmartDashboard.putNumber("Right 2 Current", sparkRight2.getOutputCurrent());
    SmartDashboard.putNumber("Right 2 Applied Output", sparkRight2.getAppliedOutput());
    SmartDashboard.putNumber("Right 2 Motor Temperature", sparkRight2.getMotorTemperature()); 

    // NavX
    SmartDashboard.putNumber("Get Angle: ", getAngle());*/
  }

  // NavX Methods
  public double getAngle(){
    double value = 0;
    try {
        value = Math.IEEEremainder(ahrs.getAngle(), 360) * (NavX.kGyroReversed ? -1.0 : 1.0);
    } catch( RuntimeException ex ) {
        DriverStation.reportError("Error communicating with navX:  " + ex.getMessage(), true);
    }
    return value;
  }
  public void zeroHeading(){
    try {
      ahrs.reset();
    } catch( RuntimeException ex ) {
        DriverStation.reportError("Error communicating with navX:  " + ex.getMessage(), true);
    }
  }
  public double getTurnRate() {
    return ahrs.getRate() * (NavX.kGyroReversed ? -1.0 : 1.0);
  }
  public void setMaxOutput(double maxOutput) {
    driveTrain.setMaxOutput(maxOutput);
  }
}
