package controller;

import java.io.IOException;

import view.Frame;

/**
 * This class is a Runnable object that is started in a Thread and can contains
 * some error handling. This class runs the slideshow for the given number of
 * seconds with the given delay, as contained in the Configuration object. If
 * the user presses the Escape key, this will terminate at most 500ms later.
 * 
 * @author Robert
 */
public class Runner implements Runnable {

	/**
	 * How often, while displaying a slide, to check to see that myRunFlag is
	 * still true.
	 */
	private static final int CHECK_INT = 500;

	/**
	 * A reference to the user-input configuration to run under.
	 */
	private final Configuration myConfig;

	/**
	 * A reference to the frame to advance the slides with.
	 */
	private final Frame myFrame;

	/**
	 * True if the the thread should be ran, false otherwise.
	 */
	private volatile boolean myRunFlag;

	/**
	 * Constructs a new Runner. A new Frame is also constructed here. If the
	 * frame detects that either the given folder has no pictures in it, an
	 * IllegalArgumentException will be thrown. If the given folder cannot be
	 * opened, an IOException will be thrown.
	 * 
	 * @param config
	 *            the user input configuration
	 * @throws IllegalArgumentException
	 *             if directory has no pictures
	 */
	public Runner(final Configuration config) throws IllegalArgumentException {
		myConfig = config;
		myFrame = new Frame(myConfig, this);
		myRunFlag = false;
	}

	/**
	 * Displays the frame and blocks on runForTime() until the total run time is
	 * up or the user escapes out. The frame is then disposed, and the thread
	 * terminates.
	 */
	@Override
	public void run() {
		myFrame.setVisible(true);
		myRunFlag = true;
		runForTime(myFrame, myConfig);
		myFrame.dispose();
	}

	/**
	 * Sets the run flag to false which will cause the runForTime() method, if
	 * running, to stop within CHECK_INT milliseconds.
	 */
	public void end() {
		myRunFlag = false;
	}

	/**
	 * Runs the slideshow for the given time, checking myRunFlag all the while.
	 * This is done with two nested while loops to track the total progression,
	 * each of which check myRunFlag.
	 * 
	 * @param frame
	 *            the frame to call advance on
	 * @param config
	 *            the user configuration
	 */
	private void runForTime(final Frame frame, final Configuration config) {
		final int delay = config.getDelay();
		int totalSecsCounter = 0;
		// run for the total time or until myRunFlag is false, whichever happens
		// first
		while (totalSecsCounter / 60 < config.getMins() && myRunFlag) {
			try {
				frame.advance();
			} catch (IOException e) {
				// if a picture cannot be opened, notify and dispose
				e.printStackTrace();
				System.err.println("Picture could not be opened!");
				frame.dispose();
				myRunFlag = false;
			}
			// wait for the delay time on a slide
			int currentSlideTime = 0; // in millis
			while (currentSlideTime < delay * 1000 && myRunFlag) {
				try {
					Thread.sleep(CHECK_INT);
				} catch (InterruptedException e) {
				}
				currentSlideTime += CHECK_INT;
			}
			totalSecsCounter += delay;
		}
	}

}
