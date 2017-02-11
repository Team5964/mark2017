package org.usfirst.frc.team5964.robot.subsystems;

import org.usfirst.frc.team5964.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Actuators extends Subsystem {
	
	public static final double LIMIT_GO_OPEN = 0.7;
	public static final double LIMIT_GO_CLOSE = 0.2;
	public static final double LIMIT_GM_LEFT = 0.2;
	public static final double LIMIT_GM_RIGHT = 0.9;
	public static final double GM_MIDDLE = 0.55;
	

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Servo gearOpener = RobotMap.gearOpener;
	Servo gearMover = RobotMap.gearMover;
	
	public boolean isOpen = false;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void gearRelease() {
    	 this.gearOpener.set(LIMIT_GO_OPEN);
    }
    
    public void gearHold() {
    	this.gearOpener.set(LIMIT_GO_CLOSE);
    }
    
    public void gearPositionLeft() {
    	this.gearMover.set(LIMIT_GM_LEFT);
    }
    
    public void gearPositionRight() {
    	this.gearMover.set(LIMIT_GM_RIGHT);
    }
    
    public void gearPositionMiddle() {
    	this.gearMover.set(GM_MIDDLE);
    }
    
    public void gearPositionTo(double x) {
    	this.gearMover.set(x);
    }
    
}

