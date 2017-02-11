package org.usfirst.frc.team5964.robot.subsystems;

import org.usfirst.frc.team5964.robot.Constants;
import org.usfirst.frc.team5964.robot.Robot;
import org.usfirst.frc.team5964.robot.RobotMap;
import org.usfirst.frc.team5964.robot.RobotMode;
import org.usfirst.frc.team5964.robot.commands.DriveArcade;
import org.usfirst.frc.team5964.robot.hid.DoNothingJoystickStabilization;
import org.usfirst.frc.team5964.robot.hid.JoystickPosition;
import org.usfirst.frc.team5964.robot.hid.JoystickStabilization;
import org.usfirst.frc.team5964.robot.hid.RaiseToPowerJoystickStabilization;
import org.usfirst.frc.team5964.robot.hid.SlewLimitJoystickStabilization;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class Drive extends Subsystem {

	RobotDrive robotDrive = RobotMap.robotDrive;
	Gyro gyro = RobotMap.gyro;
	JoystickStabilization joystickStabilization = null;
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());

		setDefaultCommand(new DriveArcade());

		gyro.reset();
	}
	/*
	 * pull this out to a separate method so that we can get to it from telemetry and other places
	 */
	public double getJoystickY(GenericHID hid) {
		return hid.getRawAxis(1);
	}

	/*
	 * pull this out to a separate method so that we can get to it from telemetry and other places
	 */
	public double getJoystickX(GenericHID hid) {
		return hid.getRawAxis(4);
	}

	public void arcadeDrive(GenericHID hid)
	{
		double move = getJoystickY(hid);
		double  rX = getJoystickX(hid);

		JoystickPosition joystickPosition = joystickStabilization.stabilizeJoystick(rX, move);
		
		robotDrive.arcadeDrive(joystickPosition.getY(), joystickPosition.getX());

	}

	public void turnMotorsOn()
	{
		robotDrive.arcadeDrive(1, 0);

	}


	public void turnMotorsOff()
	{
		robotDrive.arcadeDrive(0, 0);
	}

	public void allInit(RobotMode mode)
	{
		Robot.drive.setJoyStabalType(Constants.JOY_STABAL_NONE);
	}

	public void setJoyStabalType(String type)
	{
		if(type.equals(Constants.JOY_STABAL_RAISE_TO_POWER))
		{
			joystickStabilization = new RaiseToPowerJoystickStabilization();

		}
		else if(type.equals(Constants.JOY_STABAL_SLEW_LIMIT)) {

			joystickStabilization = new SlewLimitJoystickStabilization();
		}
		else
		{
			joystickStabilization = new DoNothingJoystickStabilization();
		}
		System.out.println("Stabilization = " + joystickStabilization + " " + type);
	}
}

