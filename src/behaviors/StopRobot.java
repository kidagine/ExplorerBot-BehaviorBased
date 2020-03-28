package behaviors;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.robotics.subsumption.Behavior;

public class StopRobot implements Behavior{
	private boolean isSuppressed;
	
	
	@Override
	public boolean takeControl() {
		return Button.ESCAPE.isDown();
	}
	
	@Override
	public void suppress() {
		isSuppressed = true;
	}

	@Override
	public void action() {
		isSuppressed = false;
		if (!isSuppressed) {
	        Sound.beepSequence();
	        System.out.println("Exit");
	        System.exit(0);		
		}
	}
}