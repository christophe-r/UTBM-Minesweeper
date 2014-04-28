package fr.utbm.lp24.minesweeper;

/**
 * The controller class of the board.
 * @author Christophe
 *
 */
public class BoardController extends Board {

	public BoardController(int width, int height) {
		super(width, height);
	}
	
	/**
	 * This method could reset the game: New CLEAN tiles.
	 */
	public void resetBoard(){
		super.setDimension(super.width, super.height);
	}
	
	/**
	 * This method places the mines randomly on the board, with excluding a 3*3 area (to avoid losing on first click).
	 * It is also secured to do not enter in an infinite loop (You cannot place more than WIDTH*HEIGHT-3*3 mines)
	 * @param nbMines Number of mines to place
	 * @param excludeX The X coordinate where to do not place mines around
	 * @param excludeY The Y coordinate where to do not place mines around
	 */
	public void populateMines(int nbMines, int excludeX, int excludeY){
		if( nbMines <= super.width*super.height-9 ){
			while( super.nbMines < nbMines ){
				
				int randomX = (int)(Math.random() * (super.width)) ;
				int randomY = (int)(Math.random() * (super.height));
				
				
				if( !(randomX>=excludeX-1 && randomX<=excludeX+1 &&
					  randomY>=excludeY-1 && randomY<=excludeY+1) &&
					super.getTile(randomX, randomY).getContent() != TileContent.MINE ){
					
					this.getTile(randomX, randomY).setContent(TileContent.MINE);
					this.incrementNbMinesAround(randomX, randomY);
					
					super.nbMines++;
				}
				
				
			}
		} else {
			System.out.println("Error: The amount of mines is too high.");
		}

	}
	
	/**
	 * This method increments the number of mines there is around for the existing adjacent tiles.
	 * @param x The X coordinate 
	 * @param y The Y coordinate
	 */
	public void incrementNbMinesAround(int x, int y){
		for( int i=-1; i<=1 ; i++ ){
			for( int j=-1; j<=1 ; j++ ){
				Tile adjacentTile = super.getAdjacentTile(x, y, i, j);
				if( adjacentTile != null && adjacentTile.getContent() != TileContent.MINE ){
					adjacentTile.setContentIncrement();
				}
			}
			
		}	
		
	}
	
	
	/**
	 * This method tests if the game is finished.
	 * @return True if the player won. False if the game is not finished yet.
	 */
	public boolean isWon(){
		boolean won = true;
		
		for(Tile[] lineTile : super.board ){
			for(Tile tile : lineTile ){
				// If a tile appeared to be undiscovered (undiscovered, flagged or question mark), and not mined, the play is not still won 
				if( tile.getState() != TileState.DISCOVERED && tile.getContent() != TileContent.MINE ){
					won = false;
				}
			}
		}
		
		return won;
	}

	/**
	 * This method reveal the tiles recursively thanks to the coordinates given as X and Y.
	 * The recursion stops when a non-blank (CLEAR0) tile is encountered.
	 * @param x
	 * @param y
	 * @author Vincent
	 */
	public void revealTilesRecursively(int x, int y){
		// TODO
	}
	
	/**
	 * This method is called when a left-click happens
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 * @author Vincent
	 */
	public void leftClickOnTile(int x, int y){
		// TODO
		// In this part, it will by necessary to use the "revealTilesRecursively" method.
	}
	
	
	/**
	 * This method is called when a right-click happens
	 * @param x The X coordinate where the click happens
	 * @param y The Y coordinate where the click happens
	 * @author Vincent
	 */
	public void rightClickOnTile(int x, int y){
		// TODO
	}
	
	


}
