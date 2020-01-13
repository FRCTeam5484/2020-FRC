package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;
import frc.robot.subsystems.subLimeLight;

public class cLimeLight_AlignToTarget extends CommandBase {
  private final subDriveTrain driveTrain;
  private final subLimeLight limeLight;
  public cLimeLight_AlignToTarget(subDriveTrain _driveTrain, subLimeLight _limeLight) {
    driveTrain = _driveTrain;
    limeLight = _limeLight;
    addRequirements(driveTrain);
    addRequirements(limeLight);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveTrain.LimeDrive(driveTrain, limeLight);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }  
}