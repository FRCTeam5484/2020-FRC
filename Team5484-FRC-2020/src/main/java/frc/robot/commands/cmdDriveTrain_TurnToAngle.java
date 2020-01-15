package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.NavX;
import frc.robot.subsystems.subDriveTrain;

public class cmdDriveTrain_TurnToAngle extends PIDCommand {
  public cmdDriveTrain_TurnToAngle(double targetAngleDegrees, subDriveTrain drive) {
    super(
        new PIDController(NavX.kTurnP, NavX.kTurnI, NavX.kTurnD),
        () -> drive.getAngle(),
        () -> targetAngleDegrees,
        output -> drive.AutoDrive(0, output),drive);
        
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(NavX.kTurnToleranceDeg, NavX.kTurnRateToleranceDegPerS);
  }

  
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
