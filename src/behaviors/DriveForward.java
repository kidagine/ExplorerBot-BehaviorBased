package behaviors;

import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DriveForward implements Behavior {

	private MovePilot movePilot;
	private boolean isSuppressed;
	
	
	public DriveForward(MovePilot movePilot) {
		this.movePilot = movePilot;
	}
	
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
	    Button.LEDPattern(4);
        Delay.msDelay(500);
        Button.LEDPattern(0);
        movePilot.forward();
	    while (!isSuppressed) {
	    	Thread.yield();
	    }	
	    movePilot.stop();
	}
}
