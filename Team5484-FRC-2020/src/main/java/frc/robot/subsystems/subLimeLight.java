package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.LimeLight;

public class subLimeLight extends SubsystemBase {
  public boolean HasValidTarget = false;
  public double DriveCommand = 0.0;
  public double SteerCommand = 0.0;
  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tvHasTarget = table.getEntry("tv");
  private NetworkTableEntry taAreaDistance = table.getEntry("ta");
  private NetworkTableEntry txHorizontalOffset = table.getEntry("tx");
  //private NetworkTableEntry tyVerticalOffset = table.getEntry("ty");
  //private NetworkTableEntry tsRotationOffset = table.getEntry("ts");
  private NetworkTableEntry ledMode = table.getEntry(("ledMode"));
  private NetworkTableEntry pipeline = table.getEntry("pipeline");
  //private NetworkTableEntry camMode = table.getEntry("camMode");

  public subLimeLight() {
  }

  @Override
  public void periodic() {
  }
  public void triggeredPeriodic(){
    tvHasTarget = table.getEntry("tv");
    HasValidTarget = ((tvHasTarget.getDouble(0.0) < 1.0) ? false : true); 
    taAreaDistance = table.getEntry("ta");
    txHorizontalOffset = table.getEntry("tx");
    //tyVerticalOffset = table.getEntry("ty");
    //tsRotationOffset = table.getEntry("ts");
    //ledMode = table.getEntry("ledMode");
    //pipeline = table.getEntry("pipeline");
    //camMode = table.getEntry("camMode");

    /* SmartDashboard.putBoolean("LimeLight Has Target: ", HasValidTarget);
    SmartDashboard.putNumber("LimeLight ledMode: ", ledMode.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight pipeLine: ", pipeline.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight taAreaDistance: ", taAreaDistance.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight txHorizontalOffset: ", txHorizontalOffset.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight tyVerticalOffset: ", tyVerticalOffset.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight tsRotationOffset: ", tsRotationOffset.getDouble(0.0));
    SmartDashboard.putNumber("LimeLight camMode: ", camMode.getDouble(0.0)); */

    if (HasValidTarget){
      double steer_cmd = (LimeLight.Detection.DESIRED_ANGLE - txHorizontalOffset.getDouble(0.0)) * LimeLight.Detection.STEER_K;
      SteerCommand = ((steer_cmd > LimeLight.Detection.MAX_DRIVE) ? LimeLight.Detection.MAX_DRIVE : steer_cmd);
      double drive_cmd = (LimeLight.Detection.DESIRED_TARGET_AREA - taAreaDistance.getDouble(0)) * LimeLight.Detection.DRIVE_K;
      DriveCommand = ((drive_cmd > LimeLight.Detection.MAX_DRIVE) ? LimeLight.Detection.MAX_DRIVE : drive_cmd); 
      /* double steer_cmd = (LimeLight.Detection.DESIRED_ANGLE - txHorizontalOffset.getDouble(0.0)) * LimeLight.Detection.STEER_K;
      if(steer_cmd > LimeLight.Detection.MAX_DRIVE){
        SteerCommand = LimeLight.Detection.MAX_DRIVE;
      }
      else if (steer_cmd < LimeLight.Detection.MIN_DRIVE){
        SteerCommand = LimeLight.Detection.MIN_DRIVE;
      }
      else{
        SteerCommand = steer_cmd;
      }
      double drive_cmd = (LimeLight.Detection.DESIRED_TARGET_AREA - taAreaDistance.getDouble(0)) * LimeLight.Detection.DRIVE_K;
      if(drive_cmd > LimeLight.Detection.MAX_DRIVE){
        DriveCommand = LimeLight.Detection.MAX_DRIVE;
      }
      else if (drive_cmd < LimeLight.Detection.MIN_DRIVE){
        DriveCommand = LimeLight.Detection.MIN_DRIVE;
      }
      else{
        DriveCommand = drive_cmd;  
      } */
    }
    else{
      DriveCommand = -0.2;
      SteerCommand = 0.2;
    }
  }
  public void setLEDMode(Number ledState) {
      ledMode.setNumber(ledState);
  }
  public void setPipeline(Number Pipeline){
      pipeline.setNumber(Pipeline);
  }
}