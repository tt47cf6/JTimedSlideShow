package controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class Configuration {

	private final File myDir;

	private final int myMins;
	
	private final int myDelay;

	private Configuration(final File directory, final int mins, final int delay) {
		myDir = new File(directory.getAbsolutePath());
		myMins = mins;
		myDelay = delay;
	}

	public List<String> getImages() {
		final List<String> result = new ArrayList<String>();
		final File[] files = myDir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				final String lowerName = name.toLowerCase();
				return lowerName.endsWith(".gif") || lowerName.endsWith(".jpg")
						|| lowerName.endsWith(".png");
			}
		});
		for (File file : files) {
			result.add(file.getAbsolutePath());
		}
		return result;
	}
	
	public int getMins() {
		return myMins;
	}
	
	public int getDelay() {
		return myDelay;
	}

	public static Configuration parseArgs(final String[] args) {
		final File directory = new File(args[0]);
		int mins;
		int delay;
		try {
			mins = Integer.parseInt(args[1]);
			delay = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			mins = -1;
			delay = -1;
		}
		if (!directory.exists() || !directory.isDirectory() || mins <= 0 || delay <= 0) {
			return null;
		}
		return new Configuration(directory, mins, delay);
	}

}
