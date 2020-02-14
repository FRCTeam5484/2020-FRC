package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.subBallIndexer;

public class cFeedBall extends CommandBase {
  private final subBallIndexer ballIndexer;
  public cFeedBall(subBallIndexer kballIndexer) {
    ballIndexer = kballIndexer;
    addRequirements(ballIndexer);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    while(!ballIndexer.ball5Present && !ballIndexer.ball4Present && !ballIndexer.ball3Present && !ballIndexer.ball2Present && !ballIndexer.ball1Present){
      ballIndexer.RunIndexer();
    }
    if(ballIndexer.ballIntakePresent && !ballIndexer.ball5Present) {
      while(!ballIndexer.ball5Present){
        ballIndexer.RunIndexer();
      }
      ballIndexer.StopIndexer();
    }
    if(ballIndexer.ballIntakePresent && ballIndexer.ball5Present) {
      while(!ballIndexer.ball4Present){
        ballIndexer.RunIndexer();
      }
      ballIndexer.StopIndexer();
      if(ballIndexer.ball4Present && !ballIndexer.ball5Present){
        while(!ballIndexer.ball5Present){
          ballIndexer.ReverseIndexer();
        }
        ballIndexer.StopIndexer();
      }
    }
    if(ballIndexer.ballIntakePresent && ballIndexer.ball4Present) {
      while(!ballIndexer.ball3Present){
        ballIndexer.RunIndexer();
      }
      ballIndexer.StopIndexer();
      if(ballIndexer.ball3Present && !ballIndexer.ball4Present){
        while(!ballIndexer.ball4Present){
          ballIndexer.ReverseIndexer();
        }
        ballIndexer.StopIndexer();
      }
    }
    if(ballIndexer.ballIntakePresent && ballIndexer.ball3Present) {
      while(!ballIndexer.ball2Present){
        ballIndexer.RunIndexer();
      }
      ballIndexer.StopIndexer();
      if(ballIndexer.ball2Present && !ballIndexer.ball3Present){
        while(!ballIndexer.ball3Present){
          ballIndexer.ReverseIndexer();
        }
        ballIndexer.StopIndexer();
      }
    }
    if(ballIndexer.ballIntakePresent && ballIndexer.ball2Present) {
      while(!ballIndexer.ball1Present){
        ballIndexer.RunIndexer();
      }
      ballIndexer.StopIndexer();
      if(ballIndexer.ball1Present && !ballIndexer.ball2Present){
        while(!ballIndexer.ball2Present){
          ballIndexer.ReverseIndexer();
        }
        ballIndexer.StopIndexer();
      }
    }
  }


  @Override
  public void end(boolean interrupted) {
    ballIndexer.StopIndexer();
  }

  @Override
  public boolean isFinished() {
    if(ballIndexer.ball5Present && ballIndexer.ball4Present && ballIndexer.ball3Present && ballIndexer.ball2Present && ballIndexer.ball1Present){
      return true;
    }
    else{
      return false;
    }
  }
}
