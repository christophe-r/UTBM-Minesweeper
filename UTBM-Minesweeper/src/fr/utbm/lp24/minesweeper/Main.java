package fr.utbm.lp24.minesweeper;


/**
 * Main class
 * @author Christophe and Vincent
 */
public class Main {

	private static PreferencesManager userPreferences;
	
	public static void main(String[] args) {
		userPreferences = new PreferencesManager();
		
		String level = userPreferences.getPref("difficulty", "beginner");
		
		int width;
		int height;
		int nbMines;		
		
		if( level.equals("intermediate") ){
			width = height = 16;
			nbMines = 40;
		} else if( level.equals("advanced") ){
			width = 16;
			height = 30;
			nbMines = 99;
		} else if( level.equals("custom") ){
			width = Integer.parseInt(userPreferences.getPref("difficulty_custom_width", "25"));
			height = Integer.parseInt(userPreferences.getPref("difficulty_custom_height", "20"));
			nbMines = Integer.parseInt(userPreferences.getPref("difficulty_custom_mines", "300"));
		} else { // Beginner
			width = height = 9;
			nbMines = 10;
		} 
		
		
		// Launching the game
		new MinesweeperGame(width, height, nbMines);
		
	}

}
