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

public class Frame extends JFrame {

	private final List<String> myPics;

	private final JLabel myLabel;

	private int myIndex;

	public Frame(final Configuration config) throws IllegalArgumentException, IOException {
		myLabel = new JLabel();
		myPics = new ArrayList<String>(config.getImages());
		if (myPics.isEmpty()) {
			throw new IllegalArgumentException("No images in folder");
		}
		myIndex = 0;
		loadPicture(0);
		add(myLabel);
		addEscapeListener();
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
	}

	private void addEscapeListener() {
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
				}
			}
		});

	}

	public void advance() throws IOException {
		myIndex++;
		myIndex = myIndex % myPics.size();
		loadPicture(myIndex);
		repaint();
	}

	private void loadPicture(final int index) throws IOException {
		final Dimension screenSize = Toolkit.getDefaultToolkit()
				.getScreenSize();
		myLabel.setHorizontalAlignment(JLabel.CENTER);

		final BufferedImage original = ImageIO
				.read(new File(myPics.get(index)));
		myLabel.setIcon(new ImageIcon(original.getScaledInstance(
				(int) screenSize.getWidth(), (int) screenSize.getHeight(),
				Image.SCALE_SMOOTH)));

	}

}
