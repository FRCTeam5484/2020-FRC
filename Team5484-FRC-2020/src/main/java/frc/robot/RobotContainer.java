package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;



public class RobotContainer {
    NetworkTableEntry shootSpeed = Shuffleboard.getTab("Test")
        .add("Shooter Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
    NetworkTableEntry intakeSpeed = Shuffleboard.getTab("Test")
        .add("Intake Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 1))
        .getEntry();
    NetworkTableEntry windowIntakeSpeed = Shuffleboard.getTab("Test")
        .add("Window Speed", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", -1, "max", 1))
        .getEntry();
    NetworkTableEntry constantOfProportionality = Shuffleboard.getTab("Test")
        .add("Constant of Proportionality", 0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", .5))
        .getEntry();
    

    //Controllers
    XboxController driverOne = new XboxController(DriveControllers.DriverOne);
    XboxController driverTwo = new XboxController(DriveControllers.DriverTwo);

    //SubSystems
    private final subDriveTrain driveTrain = new subDriveTrain(); 
    private final subLimeLight limeLight = new subLimeLight();
    private final subColorWheel colorWheel = new subColorWheel();
    private final subControlSystems controlSystems = new subControlSystems();
    private final subShooter shooter = new subShooter();
    private final subTargetingLight targetingLight = new subTargetingLight();
    private final subLED leds = new subLED();
    private final subIntake intake = new subIntake();

    //Commands
    private final cmdAutonomous commandAutoCommand = new cmdAutonomous(driveTrain, limeLight);

    public RobotContainer() {
        configureButtonBindings();
        CommandScheduler.getInstance().onCommandInitialize(command -> USBLogging.printCommandStatus(command, "initialized"));
        CommandScheduler.getInstance().onCommandFinish(command -> USBLogging.printCommandStatus(command, "FINISHeD"));
        CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));

        driveTrain.setDefaultCommand(
            new RunCommand(() -> driveTrain.tankDrive(driverOne.getY(Hand.kLeft),
                driverOne.getY(Hand.kRight), driverOne.getTriggerAxis(Hand.kRight) > DriveControllers.minRTriggerPress),
                driveTrain));
            // Comment below later
            //leds.setDefaultCommand(new RunCommand(() -> leds.setLEDStatus("detected", colorWheel.GetColor()), leds));
        // Comment out following two lines for actual competition/drive practice
        // intake.setDefaultCommand(new RunCommand(() -> intake.runIntake(intakeSpeed.getDouble(0), windowIntakeSpeed.getDouble(0)), intake));
        shooter.setDefaultCommand(new RunCommand(() -> shooter.Shoot(shootSpeed.getDouble(0)), shooter));

        // Sets LEDs to FMS-determined color at the beginning of the match
        
        // uncomment below for comp
        //leds.setLEDStatus(colorWheel.getGameData());
        
        // comment below for comp
        leds.setLEDStatus("rainbow");
        CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));
    }

    private void configureButtonBindings() {
        
        // Driver One Controls
        new JoystickButton(driverOne, Button.kA.value)
            .whenPressed(() -> limeLight.setLEDMode(LimeLight.ledMode.kOn))
            .whenReleased(() -> limeLight.setLEDMode(LimeLight.ledMode.kOff));
        new JoystickButton(driverOne, Button.kBumperRight.value)
            .whileHeld(() -> intake.runIntake())
            .whenReleased(() -> intake.stopIntake());
        new JoystickButton(driverOne, Button.kBumperLeft.value)
            .toggleWhenPressed(new RunCommand(() -> driveTrain.DriveStraight(constantOfProportionality.getDouble(.2))))
            .whenPressed(() -> driveTrain.FindCurrentEncoders());
        // new JoystickButton(driverOne, Button.kBumperLeft.value)
        //     .whenHeld(new cmdLimeLight_AlignToTarget(driveTrain, limeLight));
        // new JoystickButton(driverOne, Button.kBack.value)
        //     .whenPressed(new cmdDriveTrain_TurnToAngle(-90, driveTrain));
        // new JoystickButton(driverOne, Button.kStart.value)
        //     .whenPressed(new cmdDriveTrain_TurnToAngle(90, driveTrain));
        // new JoystickButton(driverOne, Button.kBack.value)
        //     .whenPressed(new cmdDriveTrain_TurnToAngle(-90, driveTrain));
        

        // Driver Two Controls
        // new JoystickButton(driverTwo, Button.kA.value)
        //     .whileHeld(() -> colorWheel.turnFourTimes());
        // new JoystickButton(driverTwo, Button.kB.value)
        //     .whileHeld(() -> colorWheel.turnToColor());
        // new JoystickButton(driverTwo, Button.kBumperLeft.value)
        //     .toggleWhenPressed(new RunCommand(() -> driveTrain.DriveStraight()));
        // new JoystickButton(driverTwo, Button.kX.value)
        //     .whenPressed(() -> targetingLight.TurnOn())
        //     .whenReleased(() -> targetingLight.TurnOff());



        new JoystickButton(driverTwo, Button.kBumperLeft.value)
            .whileHeld(() -> intake.runWindow(true))
            .whenReleased(() -> intake.stopWindow());
        new JoystickButton(driverTwo, Button.kBumperRight.value)
            .whileHeld(() -> intake.runWindow(false))
            .whenReleased(() -> intake.stopWindow());
        new JoystickButton(driverTwo, Button.kX.value)
            .whileHeld(() -> intake.runBallFeed(true))
            .whenReleased(() -> intake.stopBallFeed());
        new JoystickButton(driverTwo, Button.kB.value)
            .whileHeld(() -> intake.runBallFeed(false))
            .whenReleased(() -> intake.stopBallFeed());
  }

  public Command getAutonomousCommand() {
    return commandAutoCommand;
  }
}