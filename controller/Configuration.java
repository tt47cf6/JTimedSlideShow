package controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Parses and stores the command-line args.
 * 
 * @author Robert
 */
public class Configuration {

	/**
	 * The directory to load pictures from.
	 */
	private final File myDir;

	/**
	 * The total number of minutes to run for. Is always bigger than 0.
	 */
	private final int myMins;

	/**
	 * The number of seconds to show each slide for. Is always bigger than 0.
	 */
	private final int myDelay;

	/**
	 * Privately constructs a new configurations with the given fields.
	 * 
	 * @param directory
	 *            the directory
	 * @param mins
	 *            the number of minutes
	 * @param delay
	 *            the slide delay time in seconds
	 */
	private Configuration(final File directory, final int mins, final int delay) {
		myDir = new File(directory.getAbsolutePath());
		myMins = mins;
		myDelay = delay;
	}

	/**
	 * Returns a List of Strings of picture filenames and their full path in the
	 * directory. Pictures must be a GIF, JPG, or PNG.
	 * 
	 * @return a List of Strings of pictures' full filenames
	 */
	public List<String> getImages() {
		final List<String> result = new ArrayList<String>();
		final File[] files = myDir.listFiles(new FilenameFilter() {
			/**
			 * Only accepts GIF, JPG, or PNG files.
			 */
			@Override
			public boolean accept(final File dir, final String name) {
				final String lowerName = name.toLowerCase();
				return lowerName.endsWith(".gif") || lowerName.endsWith(".jpg")
						|| lowerName.endsWith(".png");
			}
		});
		// create List of full filename Strings
		for (File file : files) {
			result.add(file.getAbsolutePath());
		}
		// sort alphabetically
		Collections.sort(result);
		return result;
	}

	/**
	 * Returns the number of total minutes to run for.
	 * 
	 * @return the number of minutes to run for
	 */
	public int getMins() {
		return myMins;
	}

	/**
	 * Returns the number of seconds to show each slide.
	 * 
	 * @return the number of seconds to show each slide
	 */
	public int getDelay() {
		return myDelay;
	}

	/**
	 * Given command line arguments, return a new Configuration if the input is
	 * valid. If not, return null.
	 * 
	 * @param args
	 *            the command line arguments
	 * @return a new Configuration if valid, else null
	 */
	public static Configuration parseArgs(final String[] args) {
		final File directory = new File(args[0]);
		int mins;
		int delay;
		try {
			// attempt to parse ints
			mins = Integer.parseInt(args[1]);
			delay = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			mins = -1;
			delay = -1;
		}
		// check number input and directory exists
		if (!directory.exists() || !directory.isDirectory() || mins <= 0
				|| delay <= 0) {
			return null;
		}
		return new Configuration(directory, mins, delay);
	}

}
