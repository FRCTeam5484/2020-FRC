package frc.robot.commands;
import frc.robot.Constants.LimeLight;
import frc.robot.subsystems.subLimeLight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class cLimeLight_Off extends CommandBase {
  private final subLimeLight m_limeLight;
  public cLimeLight_Off(subLimeLight limeLight) {
    m_limeLight = limeLight;
    addRequirements(m_limeLight);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_limeLight.setLED(LimeLight.LED.kOff);
    System.out.println("Turn Off LimeLight");
  }

  @Override
  public void end(boolean interrupted) {
    m_limeLight.setLED(LimeLight.LED.kOff);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
