package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Constants.LimeLight;

public class subLimeLight extends SubsystemBase {
  public boolean HasValidTarget = false;
  public double DriveCommand = 0.0;
  public double SteerCommand = 0.0;

  public subLimeLight() {

  }

  @Override
  public void periodic() {
    Update_Limelight_Tracking();
    SmartDashboard.putBoolean("LimeLight Has Target: ", HasValidTarget);
    SmartDashboard.putNumber("LimeLight ledMode: ", GetNetworkTableValue(Constants.LimeLight.Types.kledMode));
    SmartDashboard.putNumber("LimeLight pipeLine: ", GetNetworkTableValue(Constants.LimeLight.Types.kpipeline));
    SmartDashboard.putNumber("LimeLight ta: ", GetNetworkTableValue(Constants.LimeLight.Detection.kDistanceOffset));
    SmartDashboard.putNumber("LimeLight tx: ", GetNetworkTableValue(Constants.LimeLight.Detection.kHorizontalOffset));
    SmartDashboard.putNumber("LimeLight ty: ", GetNetworkTableValue(Constants.LimeLight.Detection.kVerticalOffset));
  }
  public void Update_Limelight_Tracking(){
    if (GetNetworkTableValue(LimeLight.Detection.kHasTarget) < 1.0)
    {
      HasValidTarget = false;
      DriveCommand = 0.0;
      SteerCommand = 0.0;
      return;
    }

    HasValidTarget = true;

    SteerCommand = GetNetworkTableValue(LimeLight.Detection.kHorizontalOffset) * LimeLight.Detection.STEER_K;

    double drive_cmd = (LimeLight.Detection.DESIRED_TARGET_AREA - GetNetworkTableValue(LimeLight.Detection.kDistanceOffset)) * LimeLight.Detection.DRIVE_K;

    if (drive_cmd > LimeLight.Detection.MAX_DRIVE)
    {
      drive_cmd = LimeLight.Detection.MAX_DRIVE;
    }
    DriveCommand = drive_cmd;
  }
  public void setLED(Number ledState) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledState);
  }
  public double GetNetworkTableValue(String value)
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(value).getDouble(0);
  }
}