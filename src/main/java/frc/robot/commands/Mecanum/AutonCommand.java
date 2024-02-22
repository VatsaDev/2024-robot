// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Mecanum;

// import frc.robot.Dashboard;
import frc.robot.subsystems.MecanumSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class AutonCommand extends Command {

  double maxSpeed = .5;
  double setPoint; // Ft
  double totalRotations;
  double kp;
  double error;
  double outputSpeed;

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private MecanumSubsystem mec_subsystem;
  // private Dashboard dashboard;

  public AutonCommand(MecanumSubsystem subsystem, double setPoint) {
    System.err.println("Auton Command subs" + subsystem);
    this.mec_subsystem = subsystem;
    this.setPoint = setPoint;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(mec_subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.err.println(" in autoCommand Execute method");
    System.err.println("Sp: "+setPoint);
    //setPoint = 10; 
    totalRotations = ((setPoint * 12)/(6 * Math.PI)) * 12.57;
    System.err.println("total Rotations: " + totalRotations);

    error = totalRotations - mec_subsystem.getEncoderLeftBack().getPosition();
    System.err.println("Error:" + error);
    kp = 1/totalRotations;
    outputSpeed = error * kp;
    System.err.println("OutSpeed In Auto: "+outputSpeed);
    if (outputSpeed > 0)
    {
      mec_subsystem.drive(0, outputSpeed, 0);
    }
    else
    {
      System.err.println("STOP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    // Update the dashboard with current values
    // dashboard.updateTeleopTab(leftBumper, rightBumper, yAxis, zAxis);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
//12.428616523742676