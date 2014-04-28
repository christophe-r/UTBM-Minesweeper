package fr.utbm.lp24.minesweeper;

import javax.swing.UIManager;

/**
 * Main class
 * @author Christophe
 */
public class Main {

	public static void main(String[] args) {
		
		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		
		
		new MinesweeperWindow();
		

	}

}
