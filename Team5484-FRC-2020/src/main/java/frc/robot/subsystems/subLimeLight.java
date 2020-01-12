package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.*;
import frc.robot.Constants.LimeLight;

public class subLimeLight extends SubsystemBase {
  public boolean m_HasValidTarget = false;
  public double m_ShotSpeedCommand = 0.0;
  public double m_TurnCommand = 0.0;
  public double m_HeightCommand = 0.0;

  public subLimeLight() {

  }

  @Override
  public void periodic() {
        if (GetNetworkTableValue(LimeLight.Detection.kHasTarget) < 1.0)
        {
          m_HasValidTarget = false;
          m_ShotSpeedCommand = 0.0;
          m_TurnCommand = 0.0;
          m_HeightCommand = 0.0;
          return;
        }

        m_HasValidTarget = true;

        double steer_cmd = GetNetworkTableValue(LimeLight.Detection.kHorizontalOffset) * LimeLight.Detection.STEER_K;
        m_TurnCommand = steer_cmd;

        double height_cmd = GetNetworkTableValue(LimeLight.Detection.kVerticalOffset) * LimeLight.Detection.AIM_K;
        m_HeightCommand = height_cmd;

        double drive_cmd = (LimeLight.Detection.DESIRED_TARGET_AREA - GetNetworkTableValue(LimeLight.Detection.kDistanceOffset)) * LimeLight.Detection.DRIVE_K;

        if (drive_cmd > LimeLight.Detection.MAX_DRIVE)
        {
          drive_cmd = LimeLight.Detection.MAX_DRIVE;
        }
        //m_DriveCommand = drive_cmd;
  }
  public void setLED(Number ledState) {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(ledState);
  }
  public double GetNetworkTableValue(String value)
  {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(value).getDouble(0);
  }
}
