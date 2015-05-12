package controller;

import java.io.IOException;

import view.Frame;

public class Runner implements Runnable {
	
	private final Configuration myConfig;
	
	private final Frame myFrame;
	
	private volatile boolean myRunFlag;
	
	public Runner(final Configuration config) throws IllegalArgumentException, IOException {
		myConfig = config;
		myFrame = new Frame(myConfig, this);
		myRunFlag = false;
	}

	@Override
	public void run() {
		myFrame.setVisible(true);
		myRunFlag = true;
		runForTime(myFrame, myConfig);
		myFrame.dispose();
	}
	
	public void end() {
		myRunFlag = false;
	}
	
	private void runForTime(final Frame frame, final Configuration config) {
		final int delay = config.getDelay();
		int totalSecsCounter = 0;
		while (totalSecsCounter / 60 < config.getMins() && myRunFlag) {
			try {
				frame.advance();
			} catch (IOException e) {
				System.err.println("Pictures could not be opened!");
				frame.dispose();
				myRunFlag = false;
			}
			try {
				Thread.sleep(delay * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			totalSecsCounter += delay;
		}
	}

}
