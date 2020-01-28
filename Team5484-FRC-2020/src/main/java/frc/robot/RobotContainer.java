package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;



public class RobotContainer {
  //Controllers
  XboxController driverOne = new XboxController(DriveControllers.DriverOne);
  XboxController driverTwo = new XboxController(DriveControllers.DriverTwo);

  //SubSystems
  private final subDriveTrain driveTrain = new subDriveTrain(); 
  private final subLimeLight limeLight = new subLimeLight();
  private final subColorWheel colorWheel = new subColorWheel();
  private final subControlSystems controlSystems = new subControlSystems();
  private final subShooter shooter = new subShooter();

  //Commands
  private final cmdAutonomous commandAutoCommand = new cmdAutonomous(driveTrain, limeLight);

  public RobotContainer() {
    configureButtonBindings();

    CommandScheduler.getInstance().onCommandInitialize(command -> USBLogging.printCommandStatus(command, "initialized"));

    CommandScheduler.getInstance().onCommandFinish(command -> USBLogging.printCommandStatus(command, "FINISHeD"));

    CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));


 driveTrain.setDefaultCommand(
        new RunCommand(() -> driveTrain.tankDrive(driverOne.getY(Hand.kLeft), driverOne.getY(Hand.kRight), driverOne.getTriggerAxis(Hand.kRight) > DriveControllers.minRTriggerPress), driveTrain));

    
    driveTrain.setDefaultCommand(
        new RunCommand(() -> driveTrain.tankDrive(driverOne.getY(Hand.kLeft), driverOne.getY(Hand.kRight), driverOne.getTriggerAxis(Hand.kRight) > DriveControllers.minRTriggerPress), driveTrain));



    CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));
  }

  private void configureButtonBindings() {
    
    // Driver One Controls
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
        .whenHeld(new cmdLimeLight_AlignToTarget(driveTrain, limeLight));
    new JoystickButton(driverOne, Button.kBack.value)
        .whenPressed(new cmdDriveTrain_TurnToAngle(-90, driveTrain));
    new JoystickButton(driverOne, Button.kStart.value)
        .whenPressed(new cmdDriveTrain_TurnToAngle(90, driveTrain));
    new JoystickButton(driverOne, Button.kBack.value)
        .whenPressed(new cmdDriveTrain_TurnToAngle(-90, driveTrain));

    // Driver Two Controls
    // new JoystickButton(driverTwo, Button.kA.value)
    //     .whileHeld(() -> colorWheel.turnFourTimes());
    // new JoystickButton(driverTwo, Button.kB.value)
    //     .whileHeld(() -> colorWheel.turnToColor());
    // new JoystickButton(driverTwo, Button.kBumperLeft.value)
    //     .toggleWhenPressed(new RunCommand(() -> driveTrain.DriveStraight()));
    new JoystickButton(driverTwo, Button.kY.value)
        .whileHeld(() -> shooter.Shoot(1));
    new JoystickButton(driverTwo, Button.kX.value)
        .whenPressed(() -> shooter.Shoot(-.9))
        .whenReleased(() -> shooter.Stop());
    new JoystickButton(driverTwo, Button.kB.value)
        .whenPressed(() -> shooter.Shoot(-.8))
        .whenReleased(() -> shooter.Stop());
    new JoystickButton(driverTwo, Button.kA.value)
        .whenPressed(() -> shooter.Shoot(-.7))
        .whenReleased(() -> shooter.Stop());
    new JoystickButton(driverTwo, Button.kBumperRight.value)
        .whenPressed(() -> shooter.Shoot(-.1))
        .whenReleased(() -> shooter.Stop());
    
  }

  public Command getAutonomousCommand() {
    return commandAutoCommand;
  }
}