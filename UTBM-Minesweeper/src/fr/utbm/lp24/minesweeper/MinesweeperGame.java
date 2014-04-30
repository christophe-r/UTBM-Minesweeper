package fr.utbm.lp24.minesweeper;

import javax.swing.UIManager;


/**
 * Main Controller of the game 
 * @author Vincent
 *
 */

public class MinesweeperGame {
	
	boolean gameInProgress = false;
	int nbMines;
	BoardController myBoard;
	MinesweeperWindow window;
	
	/**
	 * Main constructor
	 * Initialize all variables
	 *  load the initial view
	 * 
	 */
	MinesweeperGame(int width, int height, int nbMines){
		
		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		
		
		this.nbMines = nbMines;
		
		
		// Launch the windows
		myBoard = new BoardController(width, height);	
		
		window = new MinesweeperWindow(this);
		window.drawBoard(myBoard.displayBoard());
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
		x = ((x-21)/10);
		y = ((y-11)/10);
		if(myBoard.getTile(x,y) != null){
			if(gameInProgress == false){ // if the game does not start we gendered a new board
				myBoard.populateMines(nbMines, x, y);
				System.out.println("Generated board:\r\n"+myBoard);
				gameInProgress = true;
			}	
			window.updateMsgBottom("");
			myBoard.revealTilesRecursively(y,x);
			System.out.println("content : "+ myBoard.getTile(x,y).getContent());
			if(myBoard.getTile(x,y).getContent() == TileContent.MINE){
				window.updateMsgBottom("Sorry but you lose");
				System.out.println("Lose : "+ myBoard.getTile(y,x).getContent());
				gameInProgress = false;
			}
			if(myBoard.isWon()){
				window.updateMsgBottom("Great you have won the game");
				gameInProgress = false;
			}
			window.drawBoard(myBoard.displayBoard());
		}
		// use to debug
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
		// transform pixel coordinate in array coordinate
		x = ((x-21)/10);
		y = ((y-11)/10);
		
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
	public BoardController getBoardController(){
		return myBoard;
	}

}
