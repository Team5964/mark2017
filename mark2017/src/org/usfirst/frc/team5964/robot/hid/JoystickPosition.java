package org.usfirst.frc.team5964.robot.hid;

public class JoystickPosition {
	private double x, y;

	public JoystickPosition (double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}
}
