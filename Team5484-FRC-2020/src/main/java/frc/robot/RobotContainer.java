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

    // NetworkTableEntry liftSpeed = Shuffleboard.getTab("Test")
    //     .add("Lift Speed", 0)
    //     .withWidget(BuiltInWidgets.kNumberSlider)
    //     .withProperties(Map.of("min", -1, "max", 1))
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
    

    // Controllers
    XboxController driverOne = new XboxController(DriveControllers.DriverOne);
    XboxController driverTwo = new XboxController(DriveControllers.DriverTwo);

    // SubSystems
    private final subDriveTrain driveTrain = new subDriveTrain(); 
    private final subLimeLight limeLight = new subLimeLight();
    private final subColorWheel colorWheel = new subColorWheel();
    private final subControlSystems controlSystems = new subControlSystems();
    private final subShooter shooter = new subShooter();
    private final subTargetingLight targetingLight = new subTargetingLight();
    private final subLED leds = new subLED();
    private final subIntake intake = new subIntake();
    public final subBallIndexer ballIndexer = new subBallIndexer();
    public final subLift lift = new subLift();
    public final subLeveler leveler = new subLeveler();
    public final subArm arm = new subArm();

    //Commands
    private final cmdAutonomous commandAutoCommand = new cmdAutonomous(driveTrain, limeLight);
    private final cmdLimeLight_AlignToTarget align = new cmdLimeLight_AlignToTarget(driveTrain, limeLight, shooter);
    private final cFeedBall feedBall = new cFeedBall(ballIndexer);

    public RobotContainer() {
        configureButtonBindings();
        CommandScheduler.getInstance().onCommandInitialize(command -> USBLogging.printCommandStatus(command, "initialized"));
        CommandScheduler.getInstance().onCommandFinish(command -> USBLogging.printCommandStatus(command, "FINISHeD"));
        CommandScheduler.getInstance().onCommandInterrupt(command -> USBLogging.printCommandStatus(command, "Interrupted"));

        driveTrain.setDefaultCommand(
            new RunCommand(() -> driveTrain.tankDrive(driverOne.getY(Hand.kLeft),
                driverOne.getY(Hand.kRight), driverOne.getTriggerAxis(Hand.kRight) > DriveControllers.minRTriggerPress),
                driveTrain));
        lift.setDefaultCommand(new RunCommand(() -> lift.setLift(driverTwo.getY(Hand.kRight)), lift));
            // Comment below later
        //shooter.setDefaultCommand(new RunCommand(() -> shooter.shoot(shootSpeed.getDouble(0)), shooter));
        // intake.setDefaultCommand(new RunCommand(() -> intake.runAutoFeed(), intake));

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
            .whileHeld(new cmdLimeLight_AlignToTarget(driveTrain, limeLight, shooter));
        new JoystickButton(driverOne, Button.kBumperLeft.value)
            .whileHeld(() -> intake.runIntake(!ballIndexer.ball1Present))
            .whenReleased(() -> intake.stopIntake());
        new JoystickButton(driverOne, Button.kBumperRight.value)
            .whileHeld(() -> intake.runIntakeBackward())
            .whenReleased(() -> intake.stopIntake());
        new JoystickButton(driverOne, Button.kB.value)
            .toggleWhenPressed(new RunCommand(() -> driveTrain.DriveStraight()))
            .whenPressed(() -> driveTrain.findCurrentEncoders());
        new JoystickButton(driverOne, Button.kStart.value)
            .whileHeld(() -> leveler.levelForward())
            .whenReleased(() -> leveler.levelStop());
        new JoystickButton(driverOne, Button.kBack.value)
            .whileHeld(() -> leveler.levelBackward())
            .whenReleased(() -> leveler.levelStop());
        // new JoystickButton(driverOne, Button.kBumperRight.value)
        //     .whileHeld(() -> intake.runIntake())
        //     .whenReleased(() -> intake.stopIntake());
        // new Trigger(() -> driverOne.getTriggerAxis(Hand.kLeft) > .3)
        //     .toggleWhenActive(new RunCommand(() -> driveTrain.DriveStraight()))
        //     .whenActive(() -> driveTrain.findCurrentEncoders());

        // Driver Two Controls
        new Trigger(() -> driverTwo.getTriggerAxis(Hand.kLeft) > .3)
            .whileActiveContinuous(() -> ballIndexer.RunIndexerFaster())
            .whenInactive(() -> ballIndexer.StopIndexer());
        new JoystickButton(driverTwo, Button.kA.value)
            .toggleWhenPressed(new RunCommand(() -> ballIndexer.LoadIndexer()))
            .whenPressed(() -> ballIndexer.StopIndexer());
    // new Trigger(() -> driverTwo.getTriggerAxis(Hand.kRight) > .3)
    //     .whenActive(() -> intake.increasePosition());
        new Trigger(() -> driverTwo.getTriggerAxis(Hand.kRight) > .3)
            .whileActiveContinuous(() -> shooter.shoot())
            .whenInactive(() -> shooter.stopShoot());
        new JoystickButton(driverTwo, Button.kBumperLeft.value)
            .whileHeld(() -> shooter.turretClock())
            .whenReleased(() -> shooter.turretStop());
        new JoystickButton(driverTwo, Button.kBumperRight.value)
            .whileHeld(() -> shooter.turretCounter())
            .whenReleased(() -> shooter.turretStop());
        // Temporary (BELOW)
        new JoystickButton(driverTwo, Button.kStart.value)
            .whileHeld(() -> arm.armUp())
            .whenReleased(() -> arm.armStop());
        new JoystickButton(driverTwo, Button.kBack.value)
            .whileHeld(() -> arm.armDown())
            .whenReleased(() -> arm.armStop());

        new JoystickButton(driverTwo, Button.kY.value)
            .whileHeld(align);
        new JoystickButton(driverTwo, Button.kX.value)
            .whenPressed(() -> lift.LiftLock());
        new JoystickButton(driverTwo, Button.kB.value)
            .whenPressed(() -> lift.LiftUnlock());
        // new Trigger(() -> upContact.get() == true)
        //     .whenActive(() -> intake.stopWindow());
    }



  public Command getAutonomousCommand() {
    return commandAutoCommand;
  }
}