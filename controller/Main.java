package controller;

import view.Frame;

public class Main {

	public static void main(final String[] args) {
//		if (args.length != 2) {
//			System.out.println("Usage: /pictures/directory/path display_mins");
//			return;
//		}
		final String[] testArgs = new String[] {
				"C:\\Users\\Robert\\Downloads", "1" };
		final Configuration config = Configuration.parseArgs(testArgs);

		final Frame frame = new Frame(config);
		frame.addContent();
		frame.setVisible(true);
	}

}
