// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc.team5964.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public JoystickButton gearOpenBT;
	public JoystickButton gearMoveLeftBT;
	public JoystickButton gearMoveRightBT;
	public JoystickButton gearMoveResetBT;
	public JoystickButton climbUpBT;
	public JoystickButton climbDownBT;
	
	public Joystick driverJoystick;
	
	public OI() {
		driverJoystick = new Joystick(0);

	}
	public Joystick getDriverJoystick() {
		return driverJoystick;
	}
}
