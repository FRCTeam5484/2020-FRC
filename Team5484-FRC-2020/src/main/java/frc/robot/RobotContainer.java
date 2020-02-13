package frc.robot;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;



public class RobotContainer {
    // private DigitalInput downContact;
    // private DigitalInput downContactBackup;
    // private DigitalInput upContact;
    // private DigitalInput upContactBackup;

    // NetworkTableEntry shootSpeed = Shuffleboard.getTab("Test")
    //     .add("Shooter Speed", 0)
    //     .withWidget(BuiltInWidgets.kNumberSlider)
    //     .withProperties(Map.of("min", 0, "max", 1))
    //     .getEntry();
    // NetworkTableEntry intakeSpeed = Shuffleboard.getTab("Test")
    //     .add("Intake Speed", 0)
    //     .withWidget(BuiltInWidgets.kNumberSlider)
    //     .withProperties(Map.of("min", 0, "max", 1))
    //     .getEntry();
    // NetworkTableEntry windowIntakeSpeed = Shuffleboard.getTab("Test")
    //     .add("Window Speed", 0)
    //     .withWidget(BuiltInWidgets.kNumberSlider)
    //     .withProperties(Map.of("min", -1, "max", 1))
    //     .getEntry();
    

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
    private final subAltShooter altShooter = new subAltShooter();

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
        //shooter.setDefaultCommand(new RunCommand(() -> shooter.shoot(shootSpeed.getDouble(0)), shooter));
        // intake.setDefaultCommand(new RunCommand(() -> intake.runAutoFeed(), intake));

        // downContact = new DigitalInput(DigitalSensors.kLimitSwitch1Port);
        // upContact = new DigitalInput(DigitalSensors.kLimitSwitch2Port);
        // downContactBackup = new DigitalInput(DigitalSensors.kLimitSwitch3Port);
        // upContactBackup = new DigitalInput(DigitalSensors.kLimitSwitch4Port);

        // Sets LEDs to FMS-determined color at the beginning of the match
        
        // uncomment below for comp
        leds.setLEDStatus(colorWheel.getGameData());
        
        // comment below for comp
        //leds.setLEDStatus("rainbow");
        CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));
    }

    private void configureButtonBindings() {
        
        // Driver One Controls
        new JoystickButton(driverOne, Button.kA.value)
            .whenPressed(() -> limeLight.setLEDMode(LimeLight.ledMode.kOn))
            .whenReleased(() -> limeLight.setLEDMode(LimeLight.ledMode.kOff))
            .whileHeld(new cmdLimeLight_AlignToTarget(driveTrain, limeLight));
        new JoystickButton(driverOne, Button.kBumperLeft.value)
            .whileHeld(() -> intake.runIntake())
            .whenReleased(() -> intake.stopIntake());
        new JoystickButton(driverOne, Button.kBumperRight.value)
            .whileHeld(() -> intake.runIntakeBackward())
            .whenReleased(() -> intake.stopIntake());
        // new JoystickButton(driverOne, Button.kBumperRight.value)
        //     .whileHeld(() -> intake.runIntake())
        //     .whenReleased(() -> intake.stopIntake());
        // new Trigger(() -> driverOne.getTriggerAxis(Hand.kLeft) > .3)
        //     .toggleWhenActive(new RunCommand(() -> driveTrain.DriveStraight()))
        //     .whenActive(() -> driveTrain.findCurrentEncoders());

        // Driver Two Controls

        new Trigger(() -> driverTwo.getTriggerAxis(Hand.kLeft) > .3)
            .whileActiveContinuous(() -> intake.runBallFeedIn())
            .whenInactive(() -> intake.stopBallFeed());
        new Trigger(() -> driverTwo.getTriggerAxis(Hand.kRight) > .3)
            .whileActiveContinuous(() -> shooter.shoot())
            .whenInactive(() -> shooter.stopShoot());
        new JoystickButton(driverTwo, Button.kBumperLeft.value)
            .whileHeld(() -> shooter.turretClock())
            .whenReleased(() -> shooter.turretStop());
        new JoystickButton(driverTwo, Button.kBumperRight.value)
            .whileHeld(() -> shooter.turretCounter())
            .whenReleased(() -> shooter.turretStop());
        // new Trigger(() -> driverTwo.getTriggerAxis(Hand.kRight) > .3)
        //     .whenActive(() -> intake.increasePosition());



        // new Trigger(() -> upContact.get() == true)
        //     .whenActive(() -> intake.stopWindow());
    }



  public Command getAutonomousCommand() {
    return commandAutoCommand;
  }
}