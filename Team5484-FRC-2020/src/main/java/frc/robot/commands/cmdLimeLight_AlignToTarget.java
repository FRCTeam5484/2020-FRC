package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LimeLight;
import frc.robot.subsystems.*;

public class cmdLimeLight_AlignToTarget extends CommandBase {
  private final subDriveTrain driveTrain;
  private final subLimeLight limeLight;
  private final subShooter shooter;
  public cmdLimeLight_AlignToTarget(final subDriveTrain _driveTrain, final subLimeLight _limeLight, final subShooter _shooter) {
    driveTrain = _driveTrain;
    limeLight = _limeLight;
    shooter = _shooter;
    addRequirements(driveTrain);
    addRequirements(limeLight);
    addRequirements(shooter);
  }

  @Override
  public void initialize() {
    limeLight.setPipeline(1);
    limeLight.setLEDMode(LimeLight.ledMode.kOn);
  }

  @Override
  public void execute() {
    limeLight.triggeredPeriodic();
    driveTrain.AutoDrive(limeLight.DriveCommand, limeLight.SteerCommand);
    shooter.setTurret(limeLight.TurretCommand);
  }

  @Override
  public void end(final boolean interrupted) {
    limeLight.setPipeline(0);
    limeLight.setLEDMode(LimeLight.ledMode.kOff);
  }

  @Override
  public boolean isFinished() {
    return false;
  }  
}