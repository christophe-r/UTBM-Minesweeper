package fr.utbm.lp24.minesweeper;

import javax.swing.UIManager;


/**
 * Main Controller of the game 
 * @author Vincent
 *
 */

public class MinesweeperGame {
	
	private GameState gameState = GameState.STOPED;
	private int nbMines;
	private int width;
	private int height;
	private BoardController myBoard;
	private MinesweeperWindow window;
	private static PreferencesManager userPreferences;
	
	/**
	 * Main constructor
	 * Initialize all variables
	 *  load the initial view
	 * 
	 */ 
	MinesweeperGame(){
		
		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		
		this.Majpreferences();
		// Launch the windows
	
		window = new MinesweeperWindow(this);
		this.newGame();
		gameState = GameState.PAUSED;
	}

	
	/**
	 * This method is called when a left-click happens.
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 * @author Vincent
	 */
	public void leftClickOnBoard(int x, int y){
		// TODO
		/*
		 * possible bug with coordinate
		 * 
		 */
		// transform pixel coordinate in array coordinate
		x = ((x-21)/window.getsquaresize())-1;
		y = ((y-21)/window.getsquaresize())-1;
		
		if(myBoard.getTile(x,y) != null){
			switch(gameState){
				case STOPED: 
					window.updateMsgPanel(" ");
					this.newGame();//to restart the game
					gameState = GameState.PAUSED;
				break;	
				case PAUSED:
					myBoard.populateMines(nbMines, x, y);
					System.out.println("Generated board:\r\n"+myBoard);
					myBoard.revealTilesRecursively(y,x);
					
					window.drawBoard(myBoard.displayBoard());
					gameState = GameState.RUN;
				break;
				case RUN:
					window.updateMsgPanel(" ");
					myBoard.revealTilesRecursively(y,x);
					window.drawBoard(myBoard.displayBoard());
					if(myBoard.getTile(x,y).getContent() == TileContent.MINE){
						window.updateMsgPanel("Sorry but you lose, click to restart.");
						new LostWindow(this);				
					}
					
					if(myBoard.isWon()){
						window.updateMsgPanel("Great you have won the game, click to restart.");
						gameState = GameState.STOPED;
						new WonWindow(this);		
					}
					
					
				break;
				default: break;
			}
			
		}
		// use to debug
		System.out.println("Click gauche");
		//System.out.println("content : "+ myBoard.getTile(x,y).getContent());
		System.out.println("Corrd X : " + x);
		System.out.println("Corrd Y : " + y);
		System.out.println("");
	}
	
	
	/**
	 * This method is called when a right-click happens.
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 * @author Vincent
	 */
	public void rightClickOnBoard(int x, int y){
		// TODO
		// transform pixel coordinate in array coordinate
		x = ((x-21)/window.getsquaresize())-1;
		y = ((y-21)/window.getsquaresize())-1;
		
		Tile tile = myBoard.getTile(x,y);
		if(tile != null){
			switch( tile.getState() ){
				case UNDISCOVERED:
					tile.setState(TileState.FLAGGED);
					myBoard.incrementNbFlags();
				break;
				
				case FLAGGED:
					tile.setState(TileState.QUESTION_MARK);
					myBoard.decrementNbFlags();
				break;
				
				case QUESTION_MARK:
					tile.setState(TileState.UNDISCOVERED);
				break;
				
				default: break;
			}
		}		
		window.drawBoard(myBoard.displayBoard());
		System.out.println("Click droit");
		System.out.println("Corrd X : " + x);
		System.out.println("Corrd Y : " + y);
		System.out.println("Nb flags : " + myBoard.getNbFlags());
		System.out.println("");
	}
	
	
	/**
	 * Update the preferences
	 * 
	 */
	public void Majpreferences(){
		// load the defaults value for width, height, nbMines
		userPreferences = new PreferencesManager();
		
		userPreferences.setPref("difficulty_custom_width","10");
		userPreferences.setPref("difficulty_custom_height","10");
		userPreferences.setPref("difficulty_custom_height","10");
		
		String level = userPreferences.getPref("difficulty", "beginner");
		
		if( level.equals("intermediate") ){
			this.width = this.height = 16;
			this.nbMines = 40;
		} else if( level.equals("advanced") ){
			this.width = 30;
			this.height = 16;
			this.nbMines = 99;
		} else if( level.equals("custom") ){
			this.width = Integer.parseInt(userPreferences.getPref("difficulty_custom_width", "25"));
			this.height = Integer.parseInt(userPreferences.getPref("difficulty_custom_height", "20"));
			this.nbMines = Integer.parseInt(userPreferences.getPref("difficulty_custom_mines", "300"));
		} else { // Beginner
			this.width = this.height = 9;
			this.nbMines = 10;
		} 
		
	}

	
	/**
	 * Method to start a new the game
	 * 
	 */
	public void newGame(){
		System.out.println("newgame");
		this.Majpreferences();
		myBoard = new BoardController(width, height);// gen a new board
		window.drawBoard(myBoard.displayBoard()); // display the new board
		gameState = GameState.PAUSED;
	}
	
	/**
	 * Method to restart the same game
	 * 
	 */
	public void restartGame(){
		System.out.println("restart game");
		myBoard.Hidemines(); // hide all mine
		window.drawBoard(myBoard.displayBoard()); // display the new board
		gameState = GameState.RUN;
	}
	
}
