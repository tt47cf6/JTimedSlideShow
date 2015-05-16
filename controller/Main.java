package controller;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;


/**
 * Main entry point that starts initializes objects and starts threads. All user
 * interaction on the command line occurs from this class.
 * Slides will be displayed in alphabetical order by their filename.
 * 
 * @author Robert
 */
public class Main {

	/**
	 * Main method. Reads in command-line arguments, and prints the usage if the
	 * arguments are of the wrong format or the directory does not exist. Also 
	 * checks that there are pictures in the directory.
	 * 
	 * @param args
	 *            should be three long, directory, display time, slide delay
	 */
	public static void main(final String[] args) {
		// parse args
		if (args.length != 3) {
			printUsage();
			return;
		}
		final Configuration config = Configuration.parseArgs(args);

		// arguments were in a bad format
		if (config == null) {
			printUsage();
			return;
		}
		
		// check for files in directory
		if (config.getImages().isEmpty()) {
			System.out.println("Directory does not contain any pictures!");
			return;
		}

		// all good, let's run
		final Runner runner = new Runner(config);
		new Thread(runner).start();
		moveMouse();

	}

	/**
	 * Moves the mouse to the bottom-right corner of the screen, so that it is
	 * not on top of the display. This may not work on all operating systems,
	 * which is ok.
	 */
	private static void moveMouse() {
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		try {
			new Robot().mouseMove(screenSize.width, screenSize.height);
		} catch (AWTException e) {
			// that's ok, just ignore
		}
	}

	/**
	 * Prints the usage information to the screen.
	 */
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
