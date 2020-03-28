package main;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import behaviors.DetectObstacle;
import behaviors.DriveForward;
import behaviors.StopRobot;

public class ExplorerBot {
   public static void main(String [] args) {
     LCD.drawString("Press any key", 0, 0);
     Button.LEDPattern(4);
     Sound.beepSequenceUp();

     Button.waitForAnyPress();
     LCD.clear();
     
	 Behavior driveForwardBehavior = new DriveForward();
	 Behavior detectObstacleBehavior = new DetectObstacle(SensorPort.S1);
	 Behavior stopRobot = new StopRobot();
	 Behavior [] behaviors = {driveForwardBehavior, detectObstacleBehavior, stopRobot};
	 Arbitrator arbitrator = new Arbitrator(behaviors);
	 arbitrator.go();
   }
}
