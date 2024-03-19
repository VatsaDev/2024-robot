// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
// import frc.robot.Dashboard;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import edu.wpi.first.wpilibj.drive.SwerveDrive; // fake, find swerve equivalent
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSubsystem extends SubsystemBase {

  // fix can ids
  private CANSparkMax motorLeftFront1 = new CANSparkMax(
    5,
    MotorType.kBrushless
  ); // 5
  private CANSparkMax motorLeftBack1 = new CANSparkMax(2, MotorType.kBrushless); // 2
  private CANSparkMax motorRightFront1 = new CANSparkMax(
    4,
    MotorType.kBrushless
  ); // 4
  private CANSparkMax motorRightBack1 = new CANSparkMax(
    3,
    MotorType.kBrushless
  ); // 3
  private CANSparkMax motorLeftFront2 = new CANSparkMax(
    6,
    MotorType.kBrushless
  ); // concept
  private CANSparkMax motorLeftBack2 = new CANSparkMax(7, MotorType.kBrushless); // concept
  private CANSparkMax motorRightFront2 = new CANSparkMax(
    8,
    MotorType.kBrushless
  ); // concept
  private CANSparkMax motorRightBack2 = new CANSparkMax(
    9,
    MotorType.kBrushless
  ); // concept

  private RelativeEncoder encoderLeftFront1; // add 1 more each motor encoder, so 4 more
  private RelativeEncoder encoderLeftBack1;
  private RelativeEncoder encoderRightFront1;
  private RelativeEncoder encoderRightBack1;
  private RelativeEncoder encoderLeftFront2; // add these are concepts
  private RelativeEncoder encoderLeftBack2;
  private RelativeEncoder encoderRightFront2;
  private RelativeEncoder encoderRightBack2;

  private SwerveDrive SwerveDrive = new SwerveDrive(
    motorLeftFront1::set,
    motorLeftBack1::set,
    motorRightFront1::set,
    motorRightBack1::set,
    motorLeftFront2::set,
    motorLeftBack2::set,
    motorRightFront2::set,
    motorRightBack2::set
  );

  public SwerveSubsystem() {
    // extra motors and encoders here
    motorLeftFront1.restoreFactoryDefaults();
    motorLeftBack1.restoreFactoryDefaults();
    motorRightFront1.restoreFactoryDefaults();
    motorRightBack1.restoreFactoryDefaults();

    encoderLeftFront1 = motorLeftFront1.getEncoder();
    encoderLeftBack1 = motorLeftBack1.getEncoder();
    encoderRightFront1 = motorRightFront1.getEncoder();
    encoderRightBack1 = motorRightBack1.getEncoder();
    encoderLeftFront2 = motorLeftFront2.getEncoder();
    encoderLeftBack2 = motorLeftBack2.getEncoder();
    encoderRightFront2 = motorRightFront2.getEncoder();
    encoderRightBack2 = motorRightBack2.getEncoder();

    resetEncoders();
  }

  public double avgEncoderPosition() {
    return (
      (
        encoderLeftFront1.getPosition() +
        encoderLeftBack1.getPosition() +
        encoderRightFront1.getPosition() +
        encoderRightBack1.getPosition() +
        encoderLeftFront2.getPosition() +
        encoderLeftBack2.getPosition() +
        encoderRightFront2.getPosition() +
        encoderRightBack2.getPosition()
      ) / 8
    );
  }

  public void drive(double xSpeed, double ySpeed, double rotation) {
    motorLeftFront1.setInverted(true);
    motorLeftBack1.setInverted(true);
    motorLeftFront2.setInverted(true);
    motorLeftBack2.setInverted(true);

    SwerveDrive.ChassisSpeeds(ySpeed, xSpeed, rotation);
  }
}
