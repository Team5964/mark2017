
package org.usfirst.frc.team5964.robot;

import java.io.File;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.usfirst.frc.team5964.robot.subsystems.Actuators;
import org.usfirst.frc.team5964.robot.subsystems.Drive;
import org.usfirst.frc.team5964.utils.DataLogger;
import org.usfirst.frc.team5964.utils.EventLogger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	PowerDistributionPanel pdp;

	DriverStation driverStation = DriverStation.getInstance();

	static Logger logger = LoggerFactory.getLogger(Robot.class);
	
	public static Drive drive;
	public static Actuators actuators;

	Command autonomousCommand;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	DataLogger dataLogger;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// Set dataLogger and Time information
		TimeZone.setDefault(TimeZone.getTimeZone("America/NewYork"));

		File logDirectory = null;
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/u"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/v"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/x"));
		if (logDirectory == null) logDirectory = findLogDirectory(new File("/y"));
		if (logDirectory == null) {
			logDirectory = new File("/home/lvuser/logs");
			if (!logDirectory.exists())
			{
				logDirectory.mkdir();
			}
		}
		if (logDirectory != null && logDirectory.isDirectory())
		{
			String logMessage = String.format("Log directory is %s\n", logDirectory);
			System.out.print (logMessage);
			//			EventLogger.writeToDS(logMessage);
			EventLogger.setup(logDirectory);
			dataLogger = new DataLogger(logDirectory);
			dataLogger.setMinimumInterval(1000);
		}

		logger.info ("Starting robotInit");

		RobotMap.init();

		drive = new Drive();
		actuators = new Actuators();

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		oi = new OI();
		// chooser.addObject("My Auto", new MyAutoCommand());
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		
		this.pdp = new PowerDistributionPanel();
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		allEndOfPeriodic();
	}


	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		allEndOfPeriodic();

	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		
		Robot.actuators.gearHold();
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		Robot.drive.setJoyStabalType(Constants.JOY_STABAL_NONE);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		allEndOfPeriodic();

	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();	
		allEndOfPeriodic();
	}
	

	/**
	 * This gets called at the end of any *periodic().
	 * We're putting this here "just in case".
	 */
	void allEndOfPeriodic()
	{
		if (dataLogger.shouldLogData())
		{
			dataLogger.addDataItem("gearOpenerPosition", RobotMap.gearOpener.get());
			dataLogger.addDataItem("gearMoverPosition", RobotMap.gearMover.get());

			dataLogger.addDataItem("rightDriveCurrentA", pdp.getCurrent(14));
			dataLogger.addDataItem("rightDriveCurrentB", pdp.getCurrent(15));
			dataLogger.addDataItem("rightDriveMotorPower", RobotMap.driveSpeedController2.get());
			dataLogger.addDataItem("leftDriveCurrentA", pdp.getCurrent(1));
			dataLogger.addDataItem("leftDriveCurrentB", pdp.getCurrent(0));
			dataLogger.addDataItem("leftDriveMotorPower", RobotMap.driveSpeedController3.get());
			dataLogger.saveDataItems();

		}
	}
	
	public static void commandInitialized(Object o) {
		logger.info("Command init: {}", o);
	}

	public static void commandEnded(Object o) {
		logger.info("Command end: {}", o);
	}

	public static void commandInterrupted(Object o) {
		logger.info("Command interupt: {}", o);
	}

	public File findLogDirectory (File root) {
		// does the root directory exist?
		if (!root.isDirectory()) return null;

		File logDirectory = new File(root, "logs");
		if (!logDirectory.isDirectory()) return null;

		return logDirectory;
	}

}
