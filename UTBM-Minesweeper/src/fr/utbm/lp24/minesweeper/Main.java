package fr.utbm.lp24.minesweeper;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {
		
		JFrame myFrame = new JFrame("Minesweeper");
		myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		myFrame.setSize(300,400);
		myFrame.setLocationRelativeTo(null); // Center the window on the screen
		myFrame.setVisible(true);

	}

}
