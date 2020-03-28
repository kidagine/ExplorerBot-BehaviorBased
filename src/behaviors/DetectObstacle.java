package behaviors;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class DetectObstacle implements Behavior {

	private EV3UltrasonicSensor ultrasonicSensor;
	private SampleProvider sampleProvider;
	float[] sample;
	private boolean isSuppressed;
	
	
	public DetectObstacle(Port port) {
		/*
		 * ultrasonicSensor = new EV3UltrasonicSensor(port); sampleProvider =
		 * ultrasonicSensor.getDistanceMode(); sample = new
		 * float[sampleProvider.sampleSize()];
		 */
	}
	
	@Override
	public boolean takeControl() {
		//	sampleProvider.fetchSample(sample, 0);
	    return false;
	}

	@Override
	public void suppress() {
		isSuppressed = true;
	}

	@Override
	public void action() {
		isSuppressed = false;
		Motor.A.rotate(-180, true);
		Motor.C.rotate(-360, true);	
		while( Motor.C.isMoving() && !isSuppressed ) {
			Thread.yield();
		}
		Motor.A.stop();
		Motor.C.stop();		
	}
}
