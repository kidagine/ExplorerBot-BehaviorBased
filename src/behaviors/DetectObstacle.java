package behaviors;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DetectObstacle implements Behavior {
	
    private EV3LargeRegulatedMotor ev3LargeRegulatedMotorRight;
    private EV3LargeRegulatedMotor ev3LargeRegulatedMotorLeft;
	private EV3UltrasonicSensor ultrasonicSensor;
	private SampleProvider sampleProvider;
	private float[] distance;
    private int motorSpeed = 4000;
	private boolean isSuppressed;
	
	
	public DetectObstacle(Port portSensor, EV3LargeRegulatedMotor motorRight,  EV3LargeRegulatedMotor motorLeft) {	
		ev3LargeRegulatedMotorRight = motorRight;
		ev3LargeRegulatedMotorLeft = motorLeft;
		ultrasonicSensor = new EV3UltrasonicSensor(portSensor);
		sampleProvider = ultrasonicSensor.getDistanceMode();
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
        Delay.msDelay(1000);
        Button.LEDPattern(0);
		ev3LargeRegulatedMotorRight.stop();
		ev3LargeRegulatedMotorLeft.stop();
        Delay.msDelay(2000);
		ev3LargeRegulatedMotorRight.setAcceleration(motorSpeed);
		ev3LargeRegulatedMotorLeft.setAcceleration(motorSpeed);
        ev3LargeRegulatedMotorRight.backward();
        ev3LargeRegulatedMotorLeft.backward();
        Delay.msDelay(1000);
        ev3LargeRegulatedMotorRight.rotate(180, true);
        Delay.msDelay(5000);
        isSuppressed = true;
	    while (!isSuppressed) {
	    	Thread.yield();
	    }
	    ev3LargeRegulatedMotorRight.stop();
	    ev3LargeRegulatedMotorLeft.stop();
	}
}
