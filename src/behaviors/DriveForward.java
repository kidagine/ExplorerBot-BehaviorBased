package behaviors;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class DriveForward implements Behavior {

    private EV3LargeRegulatedMotor ev3LargeRegulatedMotorRight;
    private EV3LargeRegulatedMotor ev3LargeRegulatedMotorLeft;
    private int motorSpeed = 4000;
	private boolean isSuppressed;
	
	
	public DriveForward(EV3LargeRegulatedMotor motorRight,  EV3LargeRegulatedMotor motorLeft) {
		ev3LargeRegulatedMotorRight = motorRight;
		ev3LargeRegulatedMotorLeft = motorLeft;
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
        Delay.msDelay(1000);
        Button.LEDPattern(0);
        ev3LargeRegulatedMotorRight.setAcceleration(motorSpeed);
		ev3LargeRegulatedMotorLeft.setAcceleration(motorSpeed);
		ev3LargeRegulatedMotorRight.forward();
		ev3LargeRegulatedMotorLeft.forward();
	    while (!isSuppressed) {
	    	Thread.yield();
	    }	
	    ev3LargeRegulatedMotorRight.stop();
	    ev3LargeRegulatedMotorLeft.stop();
	}
}
