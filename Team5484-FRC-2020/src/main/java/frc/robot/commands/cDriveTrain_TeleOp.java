package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subDriveTrain;

public class cDriveTrain_TeleOp extends CommandBase {
  private final subDriveTrain driveTrain;
  public cDriveTrain_TeleOp(subDriveTrain _driveTrain) {
    driveTrain = _driveTrain;
    addRequirements(driveTrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    driveTrain.TeleOp(driveTrain);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
