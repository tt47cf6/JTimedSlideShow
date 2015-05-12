package controller;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(final String[] args) {
		// if (args.length != 3) {
		// printUsage();
		// return;
		// }
		final String[] testArgs = new String[] {
				"C:\\Users\\Robert\\Downloads", "1", "5" };
		final Configuration config = Configuration.parseArgs(testArgs);
		
		if (config == null) {
			printUsage();
			return;
		}

		try {
			final Runner runner = new Runner(config);
			new Thread(runner).start();
			moveMouse();
		} catch (IllegalArgumentException e) {
			System.err.println("Directory does not contain any pictures!");
		} catch (IOException e) {
			System.err.println("Pictures could not be opened!");
		}

	}
	
	private static void moveMouse() {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		try {
			new Robot().mouseMove(screenSize.width, screenSize.height);
		} catch (AWTException e) {
		}
	}
	
	private static void printUsage() {
		System.out.println("Usage: timedSlideShow.jar /../path/../ display_mins delay_secs ");
		System.out.println("    /../path/../     the directory to display pictures  ");
		System.out.println("                     from. Only GIF, JPG, and PNG files ");
		System.out.println("                     will be displayed.                 ");
		System.out.println("    display_mins     the number of minutes to run the   ");
		System.out.println("                     slideshow for, in whole numbers,   ");
		System.out.println("                     1 or bigger.                       ");
		System.out.println("    delay_secs       the number of seconds to show each ");
		System.out.println("                     slide for, whole number 1 or bigger");
		System.out.println("  Example: timedSlideShow.jar c:\\pictures 60 5         ");
	}

}
