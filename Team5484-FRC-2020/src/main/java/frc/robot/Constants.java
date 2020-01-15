package frc.robot;

import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

public final class Constants {
    public final static class Colors{
        public final static Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
        public final static Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
        public final static Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
        public final static Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);
    }
    public final static class LimeLight{
        public final static class Pipeline{
            public final static int kDriveCamera = 0;
            public final static int kHighTarget = 1;
            public final static int kLowTarget = 2;
        }
        public final static class Types{
            public final static String kledMode = "ledMode";
            public final static String kcamMode = "camMode";
            public final static String kpipeline = "pipeline";
            public final static String kstream = "stream";
            public final static String ksnapshot = "snapshot";
        }
        public final static class ledMode{
            public final static Number kDefault = 0;
            public final static Number kOn = 3;
            public final static Number kOff = 1;
            public final static Number kBlink = 2;
        }
        public final static class Detection{
            public final static String kHasTarget = "tv";
            public final static String kHorizontalOffset = "tx";
            public final static String kVerticalOffset = "ty";
            public final static String kDistanceOffset = "ta";

            public final static double STEER_K = 0.03;
            public final static double AIM_K = 0.03;
            public final static double DRIVE_K = 0.26;
            public final static double DESIRED_TARGET_AREA = 13.0;
            public final static double MAX_DRIVE = 0.7;
        }
    }
    public final static class LED{
        public final static int StrandOneID = 9;
        public final static int StrandOneCount = 60;
    }
    public final static class DriveMotors {
       public final static int kLeftMotor1 = 1;
       public final static int kLeftMotor2 = 2;
       public final static int kRightMotor1 = 3;
       public final static int kRightMotor2 = 4;
    }
    public final static class DriveControllers{
        public final static int DriverOne = 0;
        public final static int DriverTwo = 1;
    }
}