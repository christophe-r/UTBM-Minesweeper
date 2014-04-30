package fr.utbm.lp24.minesweeper;

import javax.swing.UIManager;


/**
 * Main Controller of the game 
 * @author vincent
 *
 */

public class MinesweeperGame {
	
	boolean gameInProgress = false;
	int nbmines = 20;
	BoardController myBoard;
	MinesweeperWindow windows;
	
	/**
	 * Main constructor
	 * Initialise all variables
	 *  load the initial view
	 * 
	 */
	MinesweeperGame(){
		// TODO
		// Set the Windows UI theme
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e){
			System.out.println("Unable to load Windows look and feel");
		}
		// Launch the windows
		myBoard = new BoardController(20, 20);	
		windows = new MinesweeperWindow(this);
		windows.drawBoard(myBoard.displayBoard());
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
		x = ((x-21)/windows.getsquaresize())-1;
		y = ((y-21)/windows.getsquaresize())-1;
		
		if(myBoard.getTile(x,y) != null){
			if(gameInProgress == false){ // if the game does not start we gendered a new board
				myBoard.populateMines(nbmines, x, y);
				System.out.println("Generated board:\r\n"+myBoard);
				gameInProgress = true;
			}	
			windows.updateMsgPanel("");
			myBoard.revealTilesRecursively(y,x);
			System.out.println("content : "+ myBoard.getTile(x,y).getContent());
			if(myBoard.getTile(x,y).getContent() == TileContent.MINE){
				windows.updateMsgPanel("Sorry but you lose");
				System.out.println("Lose : "+ myBoard.getTile(y,x).getContent());
				gameInProgress = false;
			}
			if(myBoard.isWon()){
				windows.updateMsgPanel("Great you have won the game");
				gameInProgress = false;
			}
			windows.drawBoard(myBoard.displayBoard());
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
		x = ((x-21)/windows.getsquaresize())-1;
		y = ((y-21)/windows.getsquaresize())-1;
		
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
		windows.drawBoard(myBoard.displayBoard());
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
