package org.usfirst.frc.team5964.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static SpeedController driveSpeedController0;
	public static SpeedController driveSpeedController1;
	public static SpeedController driveSpeedController2;
	public static SpeedController driveSpeedController3;
	public static RobotDrive robotDrive;
	public static AnalogGyro gyro;
	public static Encoder leftCoder;
	public static Encoder rightCoder;
	public static Victor chainClimber;
	public static Servo gearMover;
	public static Servo gearOpener;
	

	public static void init() {
		driveSpeedController3 = new Victor(3);
		driveSpeedController2 = new Victor(2);
		//		LiveWindow.addActuator("Drive", "Speed Controller 0", (Victor) driveSpeedController0);
		//		LiveWindow.addActuator("Drive", "Speed Controller 2", (Victor) driveSpeedController2);

		gearOpener = new Servo(0);
		gearMover = new Servo(1);
		
		robotDrive = new RobotDrive(driveSpeedController3, driveSpeedController2);
	
		gyro = new AnalogGyro(0);
		//	        LiveWindow.addSensor("Drive", "gyro", gyro);
		gyro.setSensitivity(0.007);
	}

}
