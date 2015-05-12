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

	public Frame(final Configuration config) {
		myLabel = new JLabel();
		myPics = new ArrayList<String>(config.getImages());
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

	public void addContent() {
		if (!myPics.isEmpty()) {
			final Dimension screenSize = Toolkit.getDefaultToolkit()
					.getScreenSize();
			myLabel.setHorizontalAlignment(JLabel.CENTER);
			try {
				final BufferedImage original = ImageIO.read(new File(myPics
						.get(0)));
				myLabel.setIcon(new ImageIcon(original.getScaledInstance(
						(int) screenSize.getWidth(),
						(int) screenSize.getHeight(), Image.SCALE_SMOOTH)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			add(myLabel);
		}

	}

}
