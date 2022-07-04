package com.filenamechanger.frame;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

	private Container contentPane;

	public MainFrame(String title) {
		this.contentPane = getContentPane();
		setTitle(title);
		setSize(300, 120);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void add(JComponent component, String BorderLayoutConstraint) {
		contentPane.add(component, BorderLayoutConstraint);
	}

	public void disp() {
		setVisible(true);
	}

}
