package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LimeLight;
import frc.robot.subsystems.subColorWheel;
import frc.robot.subsystems.subDriveTrain;

public class cDriveTrain_TeleOp extends CommandBase {
  private final subDriveTrain driveTrain;
  private final subColorWheel colorWheel;
  public cDriveTrain_TeleOp(subDriveTrain _driveTrain, subColorWheel subWheel) {
    driveTrain = _driveTrain;
    colorWheel = subWheel;
    addRequirements(driveTrain);
    addRequirements(colorWheel);
  }

  @Override
  public void initialize() {
    driveTrain.TeleOp(driveTrain);
  }

  @Override
  public void execute() {
    colorWheel.PrintColor();
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
