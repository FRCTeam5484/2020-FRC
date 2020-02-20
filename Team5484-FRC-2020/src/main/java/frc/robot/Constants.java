package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    public final static class Sparks {
        public final static int kRightDrive = 1;
        public final static int kRightDriveSlave = 2;
        public final static int kLeftDrive = 3;
        public final static int kLeftDriveSlave = 4;
        public final static int kLeftLift = 5;
        public final static int kRightLift = 6;
        public final static int kShooterSlave = 7;
        public final static int kShooterMaster = 8;
        public final static int kColorWheel = 9;
        public final static int kBallIndexer = 10;
        public final static int kIntake = 11;
        public final static int kTurret = 12;
        public final static int kLeveler = 13;
    }
    public static final class Victors {
        public final static int kArm = 0;
    }
    public final static class MotorDirections {
        public final static boolean kInvertShooterSlave = true;
        public final static boolean kInvertShooterMaster = true; 
        public final static boolean kInvertIntake = true;
        public final static boolean kInvertTurret = false;
        public final static boolean kInvertArm = true;
        public final static boolean kInvertLeftDrive = true;
        public final static boolean kInvertRightDrive = true;
        public final static boolean kInvertLeveler = false;
    }
    public final static class AnalogSensors {
        public final static int kBallSensor1 = 0;
        public final static int kBallSensor2 = 5;
        public final static int kBallSensor3 = 2;
        public final static int kBallSensor4 = 3;
        public final static int kBallSensor5 = 4;
        public final static int kBallSensor6 = 6;
    }
    public final static class DigitalSensors {
        public final static int kLimitTurretClockwise = 0;
        public final static int kLimitTurretCounter = 1;
        public final static int kLimitTopArm = 2;
        public final static int kLimitTopArmBackup = 3;
        public final static int kLimitBottomArm = 4;
        public final static int kLimitBottomArmBackup = 5;
    }
    public final static class MotorSpeeds {
        public final static double kLevelerSpeed = .8;
    }
    public final static class Colors {
        public final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.231, 0.114);
        public final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.523, 0.113);
        public final static double kConfidence = .01;
    }
    public final static class LimeLight {
        public final static class Pipeline {
            public final static int kDriveCamera = 0;
            public final static int kHighTarget = 1;
            public final static int kLowTarget = 2;
        }
        public final static class Types {
            public final static String kledMode = "ledMode";
            public final static String kcamMode = "camMode";
            public final static String kpipeline = "pipeline";
            public final static String kstream = "stream";
            public final static String ksnapshot = "snapshot";
        }
        public final static class ledMode {
            public final static Number kDefault = 0;
            public final static Number kOn = 3;
            public final static Number kOff = 1;
            public final static Number kBlink = 2;
        }
        public final static class Detection {
            public final static String kHasTarget = "tv";
            public final static String kHorizontalOffset = "tx";
            public final static String kVerticalOffset = "ty";
            public final static String kDistanceOffset = "ta";

            public final static double STEER_K = 0.023;
            public final static double DRIVE_K = -0.35;
            public final static double DESIRED_TARGET_AREA = 2.320;
            public final static double DESIRED_ANGLE = -3.86;
            public final static double MAX_DRIVE = 0.7;
            public final static double MAX_TURRET = .2;
            public final static double MIN_DRIVE = 0.1;
        }
    }
    public final static class LED {
        public final static int StrandOneID = 9;
        public final static int StrandOneCount = 150;
        public final static int timerInterval = 3000;
    }
    public final static class ShooterMotors {
        public final static int kAmpLimit = 39;
        public final static double kShootSpeed = .7;
        public final static double kTurretSpeed = .05;
    }   
    public final static class AltShooterMotors {
        public final static int kShooterMotorTop = 10;
        public final static int kShooterMotorBottom = 9;
        public final static double kBottomSpeed = .5;
        public final static double kTopSpeed = .1;
        public final static boolean invertTMotor = true; 
        public final static boolean invertBMotor = false; 
    }   
    public final static class IntakeMotors {
        public final static double kMotorSpeed = 1;
        public final static double kArmUpSpeed = .6;
        public final static double kArmDownSpeed = .3;
        public final static double kBallFeedSpeed = .5;
        public final static double kBallFeedSpeedSlow = .3;
    }
    public final static class BallIndexer {
        public final static double kMotorSpeed = .2;
        public final static double kMotorSpeedMAX = .5;
        public final static double kShootSpeed = .2;
    }
    public final static class DriveMotors {
        public final static double kA = .04; // Constant of Proportionality
        public final static int kAmpLimit = 39;
        public final static String kTestMotor = "Left2";
        public final static double kMaxSpeed = 0.7;
        public final static double kUnitsPerInch = 100;
        public final static double kBoostSpeed = 1;
        public final static double kTestSpeed = .85;
        public final static double kLeftDriveStraightSpeed = -.5;
        public final static double kRightDriveStraightSpeed = -.5;
        public final static double kTickTolerance = .1;
        public final static double ketchup = .8;
        public final static double kStraightPower = .5;
        public final static double kConstantofProportionality = .3;
        
    }
    public final static class DriveControllers {
        public final static int DriverOne = 0;
        public final static int DriverTwo = 1;
        public final static double minRTriggerPress = .5;
    }
    public final static class WheelSystem {
        public final static int kAmpLimit = 38;
        public final static double kMaxSpeed = 0.5;
        public final static double kFourTurnUnitCount = 50.0;
    }
    public final static class NavX {
        public final static double kTurnP = 0;
        public final static double kTurnI = 0;
        public final static double kTurnD = 0;
        public final static boolean kGyroReversed = true;
        public static final double kMaxTurnRateDegPerS = 100;
        public static final double kMaxTurnAccelerationDegPerSSquared = 300;

        public static final double kTurnToleranceDeg = 5;
        public static final double kTurnRateToleranceDegPerS = 10;
    }
    public final static class Lift {
        public final static double liftSpeed = .6;
    }
}