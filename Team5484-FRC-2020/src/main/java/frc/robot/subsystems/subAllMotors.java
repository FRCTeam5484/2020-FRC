package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.*;

public class subAllMotors extends SubsystemBase {
  private final CANSparkMax spark1 = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax spark2 = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax spark3 = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax spark4 = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax spark5 = new CANSparkMax(5, MotorType.kBrushless);
  private final CANSparkMax spark6 = new CANSparkMax(6, MotorType.kBrushless);

  public subAllMotors() {
    spark1.setInverted(AllMotors.kInverted);
    spark2.setInverted(AllMotors.kInverted);
    spark3.setInverted(AllMotors.kInverted);
    spark4.setInverted(AllMotors.kInverted);
    spark5.setInverted(AllMotors.kInverted);
    spark6.setInverted(AllMotors.kInverted);
  }

  public void TestIndividualMotor(int deviceID) {
    switch (deviceID) {
      case 1:
        spark1.set(AllMotors.kTestSpeed);
        break;
      case 2:
        spark2.set(AllMotors.kTestSpeed);
        break;
      case 3:
        spark3.set(AllMotors.kTestSpeed);
        break;
      case 4:
        spark4.set(AllMotors.kTestSpeed);
        break;
      case 5:
        spark5.set(AllMotors.kTestSpeed);
        break;
      case 6:
        spark6.set(AllMotors.kTestSpeed);
        break;
    }
  }

  @Override
  public void periodic() {
  }
}
