/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveControllers;
import frc.robot.Constants.LimeLight;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.cDriveTrain_TeleOp;
import frc.robot.commands.cLimeLight_Off;
import frc.robot.commands.cLimeLight_On;

public class RobotContainer {
  //Controllers
  XboxController driverOne = new XboxController(DriveControllers.DriverOne);

  //SubSystems
  private final subDriveTrain driveTrain = new subDriveTrain();
  private final subLimeLight limeLight = new subLimeLight();

  //Commands
  private final cDriveTrain_TeleOp driveTrain_TeleOp = new cDriveTrain_TeleOp(driveTrain);
  //private final cLimeLight_On limeLight_On - new cLimeLight_On(limeLight);
  
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public RobotContainer() {
    configureButtonBindings();
    driveTrain.setDefaultCommand(driveTrain_TeleOp);
    limeLight.setLED(LimeLight.LED.kOff);
  }

  private void configureButtonBindings() {
    new JoystickButton(driverOne, Button.kA.value)
        .whenPressed(new cLimeLight_On(limeLight));
    new JoystickButton(driverOne, Button.kA.value)
        .whenReleased(new cLimeLight_Off(limeLight));
  }


  public Command getAutonomousCommand() {
    return m_autoCommand;
  }
}
