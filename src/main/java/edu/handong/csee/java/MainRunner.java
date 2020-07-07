package edu.handong.csee.java;

public class MainRunner {

	public static void main(String[] args) {
		MainRunner myRunner = new MainRunner();
		myRunner.run(args);
	}

	private void run(String[] args) {
		PlayFrame.getInstance();
	}

}
