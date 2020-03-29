package main;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import behaviors.DetectObstacle;
import behaviors.DriveForward;
import behaviors.StopRobot;

public class ExplorerBot {
	
	private MovePilot movePilot;
	private EV3UltrasonicSensor ultrasonicSensor;
	private SampleProvider sampleProvider;
	private int linearAcceleration = 90;
	private int angularAcceleration = 75;
	private int linearSpeed = 180;
	private int angularSpeed = 150;

	
	
	public void Initialize() {
		ShowStartUp();
		SetUpPilot();
		SetUpUltrasonicSensor();
		StartArbitrator();
	}
	
	private void ShowStartUp() {
	    LCD.drawString("Press any key", 0, 0);
	    Button.LEDPattern(4);
	    Sound.beepSequenceUp();

	    Button.waitForAnyPress();
	    LCD.clear();
	    Button.LEDPattern(0);
	}
	
	private void SetUpPilot() {
		Wheel wheelRight = WheeledChassis.modelWheel(Motor.D, 56).offset(60);
		Wheel wheelLeft = WheeledChassis.modelWheel(Motor.A, 56).offset(-60);
		Chassis chassis = new WheeledChassis(new Wheel[] {wheelRight, wheelLeft}, WheeledChassis.TYPE_DIFFERENTIAL);
		movePilot = new MovePilot(chassis);
		movePilot.setLinearAcceleration(linearAcceleration);
		movePilot.setAngularAcceleration(angularAcceleration);
		movePilot.setLinearSpeed(linearSpeed);
		movePilot.setAngularSpeed(angularSpeed);
	}
	
	private void SetUpUltrasonicSensor() {
		ultrasonicSensor = new EV3UltrasonicSensor(SensorPort.S1);
		sampleProvider = ultrasonicSensor.getDistanceMode();
	}

	private void StartArbitrator() {
		 Behavior driveForwardBehavior = new DriveForward(movePilot);
		 Behavior detectObstacleBehavior = new DetectObstacle(movePilot, sampleProvider);
		 Behavior stopRobot = new StopRobot();
		 Behavior [] behaviors = {driveForwardBehavior, detectObstacleBehavior, stopRobot};
		 Arbitrator arbitrator = new Arbitrator(behaviors);
		 arbitrator.go();
	}   
}
