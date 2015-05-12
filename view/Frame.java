package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Configuration;
import controller.Runner;

/**
 * A fullscreen-frame that displays a picture, stretched and blown up to fill
 * the screen. To display the first and every subsequent slide, advance() must
 * be called. When advance is called, new files will be checked for.
 * 
 * @author Robert
 */
public class Frame extends JFrame {

	/**
	 * A reference to the user configuration.
	 */
	private final Configuration myConfig;

	/**
	 * The runner that is running this frame, used for calling end()
	 */
	private final Runner myRunner;

	/**
	 * The label to change the image of. This is the main content.
	 */
	private final JLabel myLabel;

	/**
	 * A list of String filenames of the pictures to display.
	 */
	private List<String> myPics;

	/**
	 * The current index of myPics of the image that is being displayed.
	 */
	private int myIndex;

	/**
	 * Initializes the Frame and all fields.
	 * 
	 * @param config
	 *            the user configuration
	 * @param runner
	 *            the runner
	 * @throws IllegalArgumentException
	 *             if there are no pictures in the directory
	 */
	public Frame(final Configuration config, final Runner runner)
			throws IllegalArgumentException {
		// init fields
		myLabel = new JLabel();
		myLabel.setHorizontalAlignment(JLabel.CENTER);
		myRunner = runner;
		myConfig = config;
		myPics = new ArrayList<String>(myConfig.getImages());
		myIndex = -1;
		// check for images
		if (myPics.isEmpty()) {
			throw new IllegalArgumentException("No images in folder");
		}
		// set up JFrame
		add(myLabel);
		addEscapeListener();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
	}

	/**
	 * Adds a listener for the escape key that disposes this frame and asks the
	 * Runner to end.
	 */
	private void addEscapeListener() {
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
					myRunner.end();
				}
			}
		});

	}

	/**
	 * Advances the current slide. In order to display the first and every
	 * subsequent slide, this must be called. When advance is called on the last
	 * slide, new pictures will be checked for.
	 * 
	 * @throws IOException
	 *             if the slide cannot be displayed
	 */
	public void advance() throws IOException {
		myIndex++;
		if (myIndex == myPics.size()) {
			// look for new pictures
			myPics = new ArrayList<String>(myConfig.getImages());
		}
		myIndex = myIndex % myPics.size();
		loadPicture(myIndex);
		repaint();
	}

	/**
	 * Loads a picture from the given index, and stretches the image to fill the
	 * whole screen. Lastly, sets the icon on myLabel to the loaded image.
	 * 
	 * @param index
	 *            the index to load from
	 * @throws IOException
	 *             if the picture cannot be loaded
	 */
	private void loadPicture(final int index) throws IOException {
		// load
		final BufferedImage original = ImageIO
				.read(new File(myPics.get(index)));
		// stretch
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		myLabel.setIcon(new ImageIcon(original.getScaledInstance(
				(int) screenSize.getWidth(), (int) screenSize.getHeight(),
				Image.SCALE_SMOOTH)));

	}

}
