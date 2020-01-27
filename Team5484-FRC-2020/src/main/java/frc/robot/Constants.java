package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    public final static class Colors {
        public final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        public final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
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
            public final static double MIN_DRIVE = 0.1;
        }
    }
    public final static class LED {
        public final static int StrandOneID = 9;
        public final static int StrandOneCount = 60;
    }
    public final static class ShooterMotors {
        public final static int kAmpLimit = 38;
        public final static int kLeftMotor = 5;
        public final static int kRightMotor = 6;
        public final static double kLeftSpeed = .1;
        public final static double kRightSpeed = -.1;
    }   
    public final static class DriveMotors {
        public final static int kAmpLimit = 38;
        public final static int kLeftMotor1 = 3;
        public final static int kLeftMotor2 = 4;
        public final static int kRightMotor1 = 1;
        public final static int kRightMotor2 = 2;
        public final static boolean leftInvert = true;
        public final static boolean rightInvert = false;
        public final static String kTestMotor = "Left2";
        public final static double kMaxSpeed = 0.7;
        public final static double kUnitsPerInch = 100;
        public final static double kBoostSpeed = 1;
        public final static double kTestSpeed = -.85;
        public final static double kLeftDriveStraightSpeed = -.02;
        public final static double kRightDriveStraightSpeed = -.02;
    }
    public final static class DriveControllers {
        public final static int DriverOne = 0;
        public final static int DriverTwo = 1;
        public final static double minRTriggerPress = .5;
    }
    public final static class WheelSystem {
        public final static int kAmpLimit = 38;
        public final static int kMotor = 5;
        public final static double kMaxSpeed = 0.5;
        public final static double kFourTurnUnitCount = 50.0;
    }
    public final static class UltrasonicSensor {
        public final static int kUltrasonicPort = 0;
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
}