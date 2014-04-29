package fr.utbm.lp24.minesweeper;


import javax.swing.UIManager;


/**
 * Main Controller of the game 
 * @author vincent
 *
 */

public class MinesweeperGame {
	
	boolean gameinprogress = false;
	int nbmines = 50;
	BoardController myBoard;
	MinesweeperWindow windows;
	
	MinesweeperGame(){
		// TODO
		/* Initialise all variables
		 * 
		 *  load the initial view
		 *  */
		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		// Launch the windows
		windows = new MinesweeperWindow(this);
	}

	
	/**
	 * This method is called when a left-click happens.
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 * @author Vincent
	 */
	public void leftClickOnBoard(int x, int y){
		// TODO
		
		if(gameinprogress == false){ // if the game does not start
			myBoard = new BoardController(30, 16);	
			myBoard.populateMines(nbmines, x/5, y/5);
			System.out.println("Generated board:\r\n"+myBoard);
			windows.drawBoard(myBoard.displayBoard());
			windows.boardView();
			gameinprogress = true;
		}
		
		// In this part, it will by necessary to use the "revealTilesRecursively" method.
		
		System.out.println("Click gauche");
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
		System.out.println("Click droit");
		System.out.println("Corrd X : " + x);
		System.out.println("Corrd Y : " + y);
		System.out.println("");
	}
	public BoardController getBoardController(){
		return myBoard;
	}

}
