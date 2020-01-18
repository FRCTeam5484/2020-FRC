package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.LimeLight;
import frc.robot.subsystems.subLimeLight;
import frc.robot.USBLogging.Level;

public class Robot extends TimedRobot {
  public static DriverStation driverStation;
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  private subLimeLight limeLight = new subLimeLight();

  @Override
  public void robotInit() {
    USBLogging.openLog();
    USBLogging.setLogLevel(Level.INFO);

    USBLogging.info("Robot.robotInit()");
    m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {    
    USBLogging.info("########  Robot disabled");
    limeLight.setLEDMode(LimeLight.ledMode.kOff);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    USBLogging.info("########  Autonomous enabled");

    if (driverStation == null)
      driverStation = DriverStation.getInstance();

    if (driverStation.isFMSAttached()) {
      String fmsInfo = "FMS info:";
      fmsInfo += "  " + driverStation.getEventName();
      fmsInfo += " " + driverStation.getMatchType();
      fmsInfo += " match " + driverStation.getMatchNumber();
      if (driverStation.getReplayNumber() > 0) {
        fmsInfo += " replay " + driverStation.getReplayNumber();
      }
      fmsInfo += ";  " + driverStation.getAlliance() + " alliance";
      fmsInfo += ",  Driver Station " + driverStation.getLocation();
      USBLogging.info(fmsInfo);
    } else {
      USBLogging.info("FMS not connected");
    }
      
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    USBLogging.info("########  Teleop enabled");
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {
  }
}
