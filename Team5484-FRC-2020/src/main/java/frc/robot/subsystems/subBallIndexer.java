package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Timer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.BallIndexer;
import frc.robot.Constants.AnalogSensors;

public class subBallIndexer extends SubsystemBase {
  private final CANSparkMax ballFeed = new CANSparkMax(BallIndexer.kMotor, MotorType.kBrushless);
  private final AnalogInput ballSensor1 = new AnalogInput(AnalogSensors.kBallSensor1);
  private final AnalogInput ballSensor2 = new AnalogInput(AnalogSensors.kBallSensor2);
  private final AnalogInput ballSensor3 = new AnalogInput(AnalogSensors.kBallSensor3);
  private final AnalogInput ballSensor4 = new AnalogInput(AnalogSensors.kBallSensor4);
  private final AnalogInput ballSensor5 = new AnalogInput(AnalogSensors.kBallSensor5);
  private final AnalogInput ballIntake = new AnalogInput(AnalogSensors.kBallSensor6);

  public boolean ball1Present = false;
  public boolean ball2Present = false;
  public boolean ball3Present = false;
  public boolean ball4Present = false;
  public boolean ball5Present = false;
  public boolean ballIntakePresent = false;

  private Timer myTestTimer = new Timer();

  public subBallIndexer() {
    ballFeed.setInverted(true);
    myTestTimer.start();
  }

  @Override
  public void periodic() {
    GetBallStatus();
  }
  public void RunIndexer(){
    ballFeed.set(BallIndexer.kMotorSpeed);
  }
  public void ReverseIndexer() {
    ballFeed.set(-BallIndexer.kMotorSpeed);
  }
  public void StopIndexer() {
    ballFeed.set(0);
  }
  public void FeedOneBallForward(){
    Timer myTimer = new Timer();
    myTimer.start();
    while(myTimer.get() < 1)
    {
      ballFeed.set(BallIndexer.kMotorSpeed);
    }
    ballFeed.set(0);
    myTimer.stop();
  }
  public void FeedOneBallBackwards(){
    Timer myTimer = new Timer();
    myTimer.start();
    while(myTimer.get() < 1)
    {
      ballFeed.set(-BallIndexer.kMotorSpeed);
    }
    ballFeed.set(0);
    myTimer.stop();
  }
  public void FeedToShooter(){
    Timer myTimer = new Timer();
    myTimer.start();
    while(myTimer.get() < 5)
    {
      ballFeed.set(BallIndexer.kMotorSpeed);
    }
    ballFeed.set(0);
    myTimer.stop();
  }
  public void LoadIndexer(){
    if(ballIntakePresent && !ball5Present) {
      while(!ball5Present){
        RunIndexer();
        GetBallStatus();
      }
      StopIndexer();
    }
    if(ballIntakePresent && ball5Present) {
      while(!ball4Present){
        RunIndexer();
        GetBallStatus();
      }
      StopIndexer();
      if(ball4Present && !ball5Present){
        while(!ball5Present){
          ReverseIndexer();
          GetBallStatus();
        }
        StopIndexer();
      }
    }
    if(ballIntakePresent && ball4Present) {
      while(!ball3Present){
        RunIndexer();
        GetBallStatus();
      }
      StopIndexer();
      if(ball3Present && !ball4Present){
        while(!ball4Present){
          ReverseIndexer();
          GetBallStatus();
        }
        StopIndexer();
      }
    }
    if(ballIntakePresent && ball3Present) {
      while(!ball2Present){
        RunIndexer();
        GetBallStatus();
      }
      StopIndexer();
      if(ball2Present && !ball3Present){
        while(!ball3Present){
          ReverseIndexer();
          GetBallStatus();
        }
        StopIndexer();
      }
    }
    if(ballIntakePresent && ball2Present) {
      while(!ball1Present){
        RunIndexer();
        GetBallStatus();
      }
      StopIndexer();
      if(ball1Present && !ball2Present){
        while(!ball2Present){
          ReverseIndexer();
          GetBallStatus();
        }
        StopIndexer();
      }
    }
  }

  public void GetBallStatus() {
    ball1Present = ballSensor1.getVoltage()<1 ? true : false;
    ball2Present = ballSensor2.getVoltage()<1 ? true : false;
    ball3Present = ballSensor3.getVoltage()<1 ? true : false;
    ball4Present = ballSensor4.getVoltage()<1 ? true : false;
    ball5Present = ballSensor5.getVoltage()<1 ? true : false;
    ballIntakePresent = ballIntake.getVoltage()<1 ? true : false;
    
    SmartDashboard.putNumber("Timer", myTestTimer.get());
    SmartDashboard.putNumber("Ball1v", ballSensor1.getVoltage());
    SmartDashboard.putNumber("Ball2v", ballSensor2.getVoltage());
    SmartDashboard.putNumber("Ball3v", ballSensor3.getVoltage());
    SmartDashboard.putNumber("Ball4v", ballSensor4.getVoltage());
    SmartDashboard.putNumber("Ball5v", ballSensor5.getVoltage());
    SmartDashboard.putNumber("BallIntakev", ballIntake.getVoltage());
    SmartDashboard.putBoolean("Ball1", ball1Present);
    SmartDashboard.putBoolean("Ball2", ball2Present);
    SmartDashboard.putBoolean("Ball3", ball3Present);
    SmartDashboard.putBoolean("Ball4", ball4Present);
    SmartDashboard.putBoolean("Ball5", ball5Present);
    SmartDashboard.putBoolean("BallIntake", ballIntakePresent);
  }  
}
