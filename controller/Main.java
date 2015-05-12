package controller;

import java.io.IOException;

import view.Frame;

public class Main {

	public static void main(final String[] args) {
		// if (args.length != 3) {
		// System.out.println("Usage: /pictures/directory/path display_mins delay_secs");
		// return;
		// }
		final String[] testArgs = new String[] {
				"C:\\Users\\Robert\\Downloads", "1", "5" };
		final Configuration config = Configuration.parseArgs(testArgs);

		System.out.println("Hello world");
		
		try {
			final Frame frame = new Frame(config);
			frame.setVisible(true);
			runForTime(frame, config);
			frame.dispose();
		} catch (IllegalArgumentException e) {
			System.err.println("Directory does not contain any pictures!");
		} catch (IOException e) {
			System.err.println("Pictures could not be opened!");
		}

	}

	private static void runForTime(final Frame frame, final Configuration config) {
		final int delay = config.getDelay();
		int totalSecsCounter = 0;
		while (totalSecsCounter / 60 < config.getMins() && frame.isVisible()) {
			try {
				Thread.sleep(delay * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				frame.advance();
			} catch (IOException e) {
				System.err.println("Pictures could not be opened!");
				frame.dispose();
			}
			totalSecsCounter += delay;
		}
	}

}
