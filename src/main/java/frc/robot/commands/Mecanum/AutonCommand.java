// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.MecanumSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutonCommand extends Command {

  // double maxSpeed = .5;
  // double setPoint; // Ft
  // double totalRotations;
  // double kp;
  // double error;
  // double outputSpeed;
  int aprilId = (int) SmartDashboard.getNumber("id", -1);
  double aprilAngle = SmartDashboard.getNumber("angle", 1000);
  double aprilDist = SmartDashboard.getNumber("dist", -1);

  AutoConstants autoConstants = new AutoConstants();

  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private MecanumSubsystem mec_subsystem;

  public AutonCommand(MecanumSubsystem subsystem, double setPoint) {
    System.err.println("Auton Command subs" + subsystem);
    this.mec_subsystem = subsystem;
    // this.setPoint = setPoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mec_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @SuppressWarnings("static-access")
  @Override
  public void execute() {
    if (mec_subsystem.avgEncoderPosition() <= 7 * autoConstants.ftToRev) {
      mec_subsystem.drive(autoConstants.movementSpeed, 0, 0);
    }

    // // Define target angle and tolerance
    // double targetAngle = 90.0; // Target 90 degrees
    // double tolerance = 5.0; // Tolerance of +/- 5 degrees

    // // Read initial angle from gyroscope
    // double initialAngle = Gyro.getAngle();

    if (DriverStation.getAlliance().equals("Red")) {
      if (DriverStation.getLocation().getAsInt() == 1 || DriverStation.getLocation().getAsInt() == 2) {
        // Loop until the robot turns 90 degrees
        // while (Math.abs(gyro.getAngle() - initialAngle) < targetAngle - tolerance) {
        // // Continue turning
        // mec_subsystem.drive(0, 0, rotationSpeed);
        // }

        if (aprilDist <= autoConstants.distToAmp * 12) {
          mec_subsystem.drive(autoConstants.movementSpeed, 0, 0);
        }
      } else if (DriverStation.getLocation().getAsInt() == 3) {

      }
    } else if (DriverStation.getAlliance().equals("Blue")) {

    } else {
      System.err.println("Driver Station Alliance Invalid State: " + DriverStation.getAlliance());
    }

    if (aprilId > 0) {
      if (aprilId == 12 && aprilAngle >= 89 && aprilAngle <= 91) { // 7
        SmartDashboard.putBoolean("April-Present", true);
      } else {
        SmartDashboard.putBoolean("April-Present", false);
      }
      mec_subsystem.drive(.25, 0, 180 - aprilAngle);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}