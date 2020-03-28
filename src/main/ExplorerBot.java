package main;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import behaviors.DetectObstacle;
import behaviors.DriveForward;

public class ExplorerBot {
   public static void main(String [] args) {
	      Behavior driveForwardBehavior = new DriveForward();
	      Behavior detectObstacleBehavior = new DetectObstacle();
	      Behavior [] behaviors = {driveForwardBehavior, detectObstacleBehavior};
	      Arbitrator arbitrator = new Arbitrator(behaviors);
	      arbitrator.go();
	}
}
