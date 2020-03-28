package behaviors;

import lejos.hardware.motor.Motor;
import lejos.robotics.subsumption.Behavior;

public class DriveForward implements Behavior {

	private boolean isSuppressed;
	
	
	@Override
	public boolean takeControl() {
	      return true;
	}

	@Override
	public void suppress() {
		isSuppressed = true;
	}
	
	@Override
	public void action() {		
		isSuppressed = false;
	    Motor.A.forward();
	    Motor.D.forward();
	    while (!isSuppressed) {
	    	Thread.yield();
	    }	
	    Motor.A.stop();
	    Motor.D.stop();
	}
}
