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
	 * This method is used in order to reset the game: New CLEAN tiles.
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

		for( Tile[] lineTile : super.board ){
			for( Tile tile : lineTile ){
				// If a tile appeared to be undiscovered (undiscovered, flagged or question mark), and not mined, the player has not still won 
				if( tile.getState() != TileState.DISCOVERED && tile.getContent() != TileContent.MINE ){
					won = false;
				}
			}
		}

		return won;
	}

	/**
	 * This method reveals the tiles recursively thanks to the coordinates given as X and Y.
	 * The recursion stops when a non-blank (CLEAR0) tile is encountered.
	 * @param x The X coordinate
	 * @param y The Y coordinate
	 * @author Vincent
	 */
	public void revealTilesRecursively(int x, int y){

		if(this.getTile(x,y) != null){
			if(this.getTile(x,y).getState() ==  TileState.UNDISCOVERED){ // Do nothing if state is : FLAGGED, QUESTION_MARK, DISCOVERED
				this.getTile(x,y).setState(TileState.DISCOVERED);
				if(this.getTile(x,y).getContent() == TileContent.CLEAR0 ){
					for( int i=-1 ; i<=1 ; i++ ){
						for( int j=-1 ; j<=1 ; j++ ){
							if( i != 0 || j != 0 ){
								revealTilesRecursively(x+i,y+j);
							}
						}
					}
				}
			}
		}

	}

	/**
	 * This method revales all adjacent mines if the number of flag around is the same that the content in the mine
	 * @param x The X coordinate 
	 * @param y The Y coordinate
	 * @author vincent
	 */
	public void revealsAllMinesWithFlagCount(int x, int y, MinesweeperGame controleur ){
		int nbmines = 0;
		if(this.getTile(x,y) != null){
			Tile myTile = this.getTile(x,y);
			for( int i=-1; i<=1 ; i++ ){
				for( int j=-1; j<=1 ; j++ ){
					System.out.println("x : " + (x+i));
					System.out.println("y : " + (y+j));
					if(this.getTile(x+i,y-j) != null && this.getTile(x+i,y+j).getState() == TileState.FLAGGED){
						nbmines++;
					}
				}
			}
			if (myTile.toContent() == nbmines){
				for( int i=-1; i<=1 ; i++ ){
					for( int j=-1; j<=1 ; j++ ){
						if(this.getTile(x+i,y-j) != null && this.getTile(x+i,y+j).getState() != TileState.FLAGGED){
							this.getTile(x+i,y+j).setState(TileState.DISCOVERED);
							if(this.getTile(x+i,y+j).getContent() == TileContent.MINE ){
								controleur.lostGame();
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * Get the tile board
	 * @return the tile board
	 * @author vincent
	 */
	public Tile[][] displayBoard(){
		return super.board;
	}


	/**
	 * Hide all mines
	 * @author vincent
	 */
	public void hideMines(){
		for( int i=0 ; i<super.width ; i++ ){
			for( int j=0 ; j<super.height ; j++ ){
				this.setTile(i, j, this.getTile(i, j).getContent(), TileState.UNDISCOVERED);
			}
		}
	}

	/**
	 * Reveals all mines in the board
	 * @author vincent
	 */
	public void revealsAllMines(){
		for( int i=0 ; i<super.width ; i++ ){
			for( int j=0 ; j<super.height ; j++ ){
				if( this.getTile(i, j).getContent() == TileContent.MINE ){
					this.setTile(i, j, this.getTile(i, j).getContent(), TileState.DISCOVERED);
				}
			}
		}
	}


	public String toString(){

		String charBoard = "";

		for(Tile[] lineTile : this.board ){
			for(Tile tile : lineTile ){
				charBoard += ""+tile;
			}
			charBoard += "\r\n";
		}

		return charBoard;
	}






}
