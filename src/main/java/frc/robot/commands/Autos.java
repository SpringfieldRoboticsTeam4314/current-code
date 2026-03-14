// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.FuelConstants.SPIN_UP_SECONDS;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.CANDriveSubsystem;
import frc.robot.subsystems.CANFuelSubsystem;

public final class Autos {
  // Example autonomous command which drives forward for 1 second.
  public static final Command middleAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    return new SequentialCommandGroup(
        // Drive backwards for .25 seconds. The driveArcadeAuto command factory
        // creates a command which does not end which allows us to control
        // the timing using the withTimeout decorator
        driveSubsystem.driveArcade(() -> -1, () -> 0).withTimeout(.44),
        // Stop driving. Use a very short timeout so the "stop" command ends
        // immediately instead of using the RunCommand returned by driveArcade
        // which never finishes on its own.
        driveSubsystem.driveArcade(() -> 0.0, () -> 0.0).withTimeout(0.01),
    
    ballSubsystem.spinUpLauncherCommand().withTimeout(1),
    ballSubsystem.spinUpCommand().withTimeout(SPIN_UP_SECONDS),
    // Ensure the launch command is explicitly followed by a guaranteed stop that
    // requires the subsystem. This avoids leaving the motors at the last
    // commanded voltage if the launch command is interrupted or times out.
    ballSubsystem.launchCommand().withTimeout(2)
      .andThen(Commands.runOnce(() -> ballSubsystem.stop(), ballSubsystem)));
  }


  public static final Command driveAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem) {
    return new SequentialCommandGroup(
        // Drive backwards for .25 seconds. The driveArcadeAuto command factory
        // creates a command which does not end which allows us to control
        // the timing using the withTimeout decorator
        driveSubsystem.driveArcade(() -> 0, () -> .5).withTimeout(.1),
        driveSubsystem.driveArcade(() -> 0.75, () -> 0).withTimeout(5),
        // Stop driving. This line uses the regular driveArcade command factory so it
        // ends immediately after commanding the motors to stop
        driveSubsystem.driveArcade(() -> 0, () -> 0).withTimeout(0.01));
  }
  public static final Command leftAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem)  {
    return new SequentialCommandGroup(
        // Drive backwards for .25 seconds. The driveArcadeAuto command factory
        // then turns for .25 seconds to the (I have no idea, change this after testing)
        // creates a command which does not end which allows us to control
        // the timing using the withTimeout decorator
        driveSubsystem.driveArcade(() -> -.6, () -> 0).withTimeout(.44),
        driveSubsystem.driveArcade(() -> 0, () -> -.6).withTimeout(.25),
        // Stop driving. This line uses the regular driveArcade command factory so it
        // ends immediately after commanding the motors to stop
        driveSubsystem.driveArcade(() -> 0, () -> 0).withTimeout(0.01),

        ballSubsystem.spinUpLauncherCommand().withTimeout(1),
        ballSubsystem.spinUpCommand().withTimeout(SPIN_UP_SECONDS),
        ballSubsystem.launchCommand().withTimeout(2),
        

        ballSubsystem.runOnce(() -> ballSubsystem.stop()).withTimeout(0.01));


        
  }
  public static final Command rightAuto(CANDriveSubsystem driveSubsystem, CANFuelSubsystem ballSubsystem)  {
    return new SequentialCommandGroup(
       // Drive backwards for .25 seconds. The driveArcadeAuto command factory
        // then turns for .25 seconds to the (I have no idea, change this after testing)
        // creates a command which does not end which allows us to control
        // the timing using the withTimeout decorator
        driveSubsystem.driveArcade(() -> -.6, () -> 0).withTimeout(.44),
        driveSubsystem.driveArcade(() -> 0, () -> .6).withTimeout(.25),
        // Stop driving. This line uses the regular driveArcade command factory so it
        // ends immediately after commanding the motors to stop
        driveSubsystem.driveArcade(() -> 0, () -> 0).withTimeout(0.01),

        ballSubsystem.spinUpLauncherCommand().withTimeout(1),
        ballSubsystem.spinUpCommand().withTimeout(SPIN_UP_SECONDS),
        ballSubsystem.launchCommand().withTimeout(2),
        

        ballSubsystem.runOnce(() -> ballSubsystem.stop()).withTimeout(0.01));

        
  }
}
