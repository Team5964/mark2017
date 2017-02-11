package org.usfirst.frc.team5964.robot.hid;
/**
 * 
 */

/**
 * @author Dante
 *
 */
public class DoNothingJoystickStabilization extends JoystickStabilization {

	@Override
	public JoystickPosition stabilizeJoystick(double x, double y)
	{
		double newx = x;
		double newy = y;
		JoystickPosition returnValue = new JoystickPosition(newx, newy);
		return returnValue;
	}
}
