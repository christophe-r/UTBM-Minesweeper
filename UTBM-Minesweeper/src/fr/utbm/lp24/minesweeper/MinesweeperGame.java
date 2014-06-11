package fr.utbm.lp24.minesweeper;

import javax.swing.UIManager;


/**
 * Main Controller of the game 
 * @author Vincent and Christophe
 *
 */
public class MinesweeperGame {

	private GameState gameState = GameState.STOPPED;
	private int nbMines;
	private int width;
	private int height;
	private boolean playAgain = false;
	private Score myScore = new Score();
	private BoardController myBoard;
	private MinesweeperWindow window;
	private StatisticsManager statistic;
	private static PreferencesManager userPreferences;
	private boolean pixelCheatEnabled = false;
	private Timer boardTimer;

	/**
	 * Main constructor
	 * Initialize all variables
	 * Load the initial view
	 */ 
	public MinesweeperGame(){

		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}

		this.updatePreferences();
		statistic = new StatisticsManager();

		// Launch the windows
		window = new MinesweeperWindow(this);
		this.newGame();
		gameState = GameState.PAUSED;


	}


	/**
	 * manage the help shadows feature
	 * @param x X coordinate in px
	 * @param y Y coordinate in px
	 */
	public void helpShadow(int x, int y){
		x = ((x-1)/window.getSquareSize())-1;
		y = ((y-1)/window.getSquareSize())-1;
		userPreferences = new PreferencesManager();
		String shadow = userPreferences.getPref("help_shadow", "false");
		if( shadow.equals("true") ){
			if( myBoard.getTile(x, y) != null ){
				window.drawShadow(x, y, true);	
			}			
		}

	}

	/**
	 * Cheat pixel cheat management
	 * @param x X coordinate in px
	 * @param y Y coordinate in px
	 */
	public void cheatPixel(int x, int y){
		x = ((x-1)/window.getSquareSize())-1;
		y = ((y-1)/window.getSquareSize())-1;


		if( this.pixelCheatEnabled ){

			if( myBoard.getTile(x, y) != null && myBoard.getTile(x, y).getContent() == TileContent.MINE ){
				window.drawCheatPixel(true); // true = Black pixel = mined
			} else {
				window.drawCheatPixel(false); // false = White pixel = not mined
			}

		}

	}

	/**
	 * Enable the cheat pixel
	 */
	public void enableCheatPixel(){
		this.pixelCheatEnabled = true;
		System.out.println("Cheat code activated.");
	}


	/**
	 * This method is called when a left-click happens.
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 */
	public void leftClickOnBoard(int x, int y){

		// Transform pixel coordinate in array coordinate
		x = ((x-1)/window.getSquareSize())-1;
		y = ((y-1)/window.getSquareSize())-1;

		if(myBoard.getTile(x, y) != null){
			switch(gameState){
			case STOPPED: 
				this.newGame(); // To restart the game
				gameState = GameState.PAUSED;
				break;

			case PAUSED:
				window.updateBottom((this.nbMines - myBoard.nbFlags), "flags");
				myBoard.populateMines(nbMines, x, y);

				boardTimer = new Timer(window);
				(new Thread(boardTimer)).start();

				System.out.println("Generated board:\r\n"+myBoard);
				myBoard.revealTilesRecursively(x, y);

				window.drawBoard(myBoard.displayBoard(),false);
				gameState = GameState.RUNNING;		
				break;

			case RUNNING:
				window.updateBottom((this.nbMines - myBoard.nbFlags), "flags");
				if( myBoard.getTile(x, y).getState() == TileState.UNDISCOVERED ) {
					myBoard.revealTilesRecursively(x, y);
					window.drawBoard(myBoard.displayBoard(),false);

					if(myBoard.getTile(x, y).getContent() == TileContent.MINE){
						this.lostGame();
					}

					if(myBoard.isWon()){
						gameState = GameState.STOPPED;

						int time = boardTimer.getTimer();
						int score = myScore.getScore(nbMines, width, height, time, playAgain);
						statistic.addTime(boardTimer.getTimer());
						statistic.addGamePlayed();
						statistic.addGameWon();
						statistic.testBestScore(score);
						boardTimer.stopTimer();
						myBoard.revealsAllMines();
						window.drawBoard(myBoard.displayBoard(), true);
						new WonWindow(this,time,myScore.getScore(nbMines, width, height, time, playAgain));
					}
				}

				break;

			default: break;
			}

		}

		// Debug
		System.out.println("Left click: X:"+x+", Y:"+y);
	}


	/**
	 * This method is called when a right-click happens.
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 */
	public void rightClickOnBoard(int x, int y){
		// Transform pixel coordinate in array coordinate
		x = ((x-1)/window.getSquareSize())-1;
		y = ((y-1)/window.getSquareSize())-1;

		Tile tile = myBoard.getTile(x, y);
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
		window.updateBottom((this.nbMines - myBoard.nbFlags), "flags");
		window.drawBoard(myBoard.displayBoard(), false);
		System.out.println("Right click: X:"+x+", Y:"+y);
	}

	/**
	 * manage the central button click
	 * @param x  X coordinate in px
	 * @param y  Y coordinate in px
	 */
	public void centralClickOnBoard(int x, int y) {
		x = ((x-1)/window.getSquareSize())-1;
		y = ((y-1)/window.getSquareSize())-1;
		if(myBoard.getTile(x, y) != null){
			if(myBoard.getTile(x, y).getState() == TileState.DISCOVERED && myBoard.getTile(x, y).getContent() != TileContent.CLEAR0 && myBoard.getTile(x, y).getContent() != TileContent.MINE)
				myBoard.revealsAllMinesWithFlagCount(x, y,this);
		}
		System.out.println("Wheel click: X:"+x+", Y:"+y);
	}

	/**
	 * Update the preferences
	 */
	public void updatePreferences(){
		// Load the preferences values for the difficulty, the width, height and nb of mines.
		userPreferences = new PreferencesManager();

		String level = userPreferences.getPref("difficulty", "beginner");

		if( level.equals("intermediate") ){
			this.width = this.height = 16;
			this.nbMines = 40;
		} else if( level.equals("advanced") ){
			this.width = 30;
			this.height = 16;
			this.nbMines = 99;
		} else if( level.equals("custom") ){
			this.height = Integer.parseInt(userPreferences.getPref("difficulty_custom_height", "20"));
			this.width = Integer.parseInt(userPreferences.getPref("difficulty_custom_width", "25"));
			this.nbMines = Integer.parseInt(userPreferences.getPref("difficulty_custom_mines", "300"));
		} else { // Beginner
			this.width = this.height = 9;
			this.nbMines = 10;
		}

		System.out.println("Updating preferences:");
		System.out.println(" Height: " + height);
		System.out.println(" Width: " + width);
		System.out.println(" NbMines: " + nbMines);

	}


	/**
	 * Method to start a new the game
	 */
	public void newGame(){
		System.out.println("New game event.");


		statistic.addGamePlayed();
		if(boardTimer != null)
			boardTimer.stopTimer();
		this.updatePreferences();
		this.playAgain = false;
		myBoard = new BoardController(width, height); // Generate a new board
		window.drawBoard(myBoard.displayBoard(), false); // Display the new board
		gameState = GameState.PAUSED;
	}

	/**
	 * Method to restart the same game
	 */
	public void restartGame(){
		System.out.println("Restart game event.");

		boardTimer = new Timer(window);
		(new Thread(boardTimer)).start();
		this.updatePreferences();
		this.playAgain = true;
		myBoard.hideMines(); // Hide all mine
		window.drawBoard(myBoard.displayBoard(), false); // Display the new board
		gameState = GameState.RUNNING;
	}

	/**
	 * Method call if you lose the game
	 */
	public void lostGame(){
		System.out.println("Game lost event.");

		statistic.addTime(boardTimer.getTimer());
		myBoard.revealsAllMines();
		window.drawBoard(myBoard.displayBoard(), true);

		boardTimer.stopTimer();
		int time = boardTimer.getTimer();
		new LostWindow(this, time);
	}

	/**
	 * Method call when you quit the game
	 */
	public void exit() {
		if( boardTimer != null ){
			statistic.addTime(boardTimer.getTimer());
		}
	}


}
