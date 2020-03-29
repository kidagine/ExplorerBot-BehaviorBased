package behaviors;

import java.util.Random;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DetectObstacle implements Behavior {
	
    private MovePilot movePilot;
	private SampleProvider sampleProvider;
	private float[] distance;
	private boolean isSuppressed;
	
	
	public DetectObstacle(MovePilot movePilot, SampleProvider sampleProvider) {	
		this.movePilot = movePilot;
		this.sampleProvider = sampleProvider;
		distance = new float[sampleProvider.sampleSize()];
	}
	
	@Override
	public boolean takeControl() {
		sampleProvider.fetchSample(distance, 0);
	    return distance[0] <= 0.1;
	}

	@Override
	public void suppress() {
		isSuppressed = true;
	}

	@Override
	public void action() {
		isSuppressed = false;
	    Sound.beepSequence();
	    Button.LEDPattern(4);
        Delay.msDelay(500);
        Button.LEDPattern(0);
		movePilot.stop();
        Delay.msDelay(2000);
		movePilot.backward();
        Delay.msDelay(1500);
        
        Random random = new Random();
        int minimumRandomRotation = 90;
        int maximumRandomRotation = 270;
        int randomRotation = random.nextInt(maximumRandomRotation - minimumRandomRotation) + minimumRandomRotation; 
        LCD.clear();
        LCD.drawString("Random: " + randomRotation, 0, 5);
        movePilot.rotate(randomRotation);
        
        isSuppressed = true;
	    while (!isSuppressed && movePilot.isMoving()) {
	    	Thread.yield();
	    }
	    movePilot.stop();
	}
}
