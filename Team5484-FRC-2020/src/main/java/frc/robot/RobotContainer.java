package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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

  //Commands
  private final cDriveTrain_TeleOp driveTrain_TeleOp = new cDriveTrain_TeleOp(driveTrain);
  
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public RobotContainer() {
    configureButtonBindings();
    driveTrain.setDefaultCommand(driveTrain_TeleOp);
    limeLight.setLED(LimeLight.ledMode.kOff);
  }

  private void configureButtonBindings() {
    new JoystickButton(driverOne, Button.kA.value)
        .whenPressed(() -> limeLight.setLED(LimeLight.ledMode.kOn))
        .whenReleased(() -> limeLight.setLED(LimeLight.ledMode.kOff));
    new JoystickButton(driverOne, Button.kB.value)
        .whenHeld(new cLimeLight_AlignToTarget(driveTrain, limeLight));
    new JoystickButton(driverOne, Button.kX.value)
        .whileHeld(() -> colorWheel.PrintColor());
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}