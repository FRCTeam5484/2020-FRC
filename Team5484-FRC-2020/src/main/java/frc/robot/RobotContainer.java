package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;



public class RobotContainer {
  //Controllers
  XboxController driverOne = new XboxController(DriveControllers.DriverOne);

  //SubSystems
  private final subDriveTrain driveTrain = new subDriveTrain(); 
  private final subLimeLight limeLight = new subLimeLight();
  private final subColorWheel colorWheel = new subColorWheel();
  private final subControlSystems controlSystems = new subControlSystems();

  //Commands
  
  private final cAutonomous m_autoCommand = new cAutonomous(driveTrain, limeLight);

  public RobotContainer() {
    configureButtonBindings();
    driveTrain.setDefaultCommand(
        new RunCommand(() -> driveTrain.tankDrive(driverOne.getY(Hand.kLeft), driverOne.getY(Hand.kRight)), driveTrain));
  }

  private void configureButtonBindings() {
    new JoystickButton(driverOne, Button.kA.value)
        .whileHeld(() -> controlSystems.setGreen());
    new JoystickButton(driverOne, Button.kB.value)
        .whileHeld(() -> controlSystems.setRed());
    new JoystickButton(driverOne, Button.kX.value)
        .whileHeld(() -> controlSystems.setBlue());
    new JoystickButton(driverOne, Button.kY.value)
        .whileHeld(() -> controlSystems.setYellow());
    new JoystickButton(driverOne, Button.kBumperRight.value)
        .whenPressed(() -> limeLight.setLEDMode(LimeLight.ledMode.kOn))
        .whenReleased(() -> limeLight.setLEDMode(LimeLight.ledMode.kOff));
    new JoystickButton(driverOne, Button.kBumperLeft.value)
        .whenHeld(new cLimeLight_AlignToTarget(driveTrain, limeLight));
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}