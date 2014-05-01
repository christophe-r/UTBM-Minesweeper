package fr.utbm.lp24.minesweeper;

/**
 * The board : the grid of tiles
 * @author Christophe
 *
 */
public class Board {
	protected int width;
	protected int height;
	protected int nbMines;
	protected int nbFlags;
	
	protected Tile board[][];
	
	/**
	 * Initialize the board with the given dimension.
	 * @param width Width of the board
	 * @param height Height of the board
	 */
	public Board(int width, int height){
		this.setDimension(width, height);
	}
	
	/**
	 * Modify the dimension of the board.
	 * @param width Width of the board
	 * @param height Height of the board
	 */
	public void setDimension(int width, int height){
		this.width = width;
		this.height = height;
		this.nbMines = 0;
		this.nbFlags = 0;
		
		// Setting the bounds
		this.board = new Tile[height][width];
		
		// Creating all tiles of the board
		for( int j=0 ; j<height ; j++ ){
			for( int i=0 ; i<width ; i++ ){
				this.board[j][i] = new Tile();
			}
		}
	}
	
	/**
	 * Get the tile from the given coordinates
	 * @param x Coordinate
	 * @param y Coordinate
	 * @return Tile
	 */
	public Tile getTile(int x, int y){
		if( this.tileInBounds(x, y) == true ){
			return this.board[y][x];
		} else {
			return null;
		}
	}
	
	/**
	 * Set a tile to the given position with the given parameters.
	 * @param x Coordinate
	 * @param y Coordinate
	 * @param tileContent (c.f. enum TileContent)
	 * @param tileState  (c.f. enum TileState)
	 */
	public void setTile(int x, int y, TileContent tileContent, TileState tileState){
		if( this.tileInBounds(x, y) ){
			this.board[y][x] = new Tile(tileContent, tileState);
		}
	}
	
	
	/**
	 * Checks if a tile is in the bounds of the board by the given coordinates.
	 * @param x Coordinate
	 * @param y Coordinate
	 * @return True or False
	 */
	public boolean tileInBounds(int x, int y){
		return (x >= 0 && x < width) && (y >= 0 && y < height);
	}
	
	/**
	 * Get the adjacent tile.
	 * (x,y) : coordinates of the tile in the board.
	 * (i, j) : the offset.
	 * @param x The x coordinate of the tile in the board
	 * @param y The y coordinate of the tile in the board
	 * @param i The x offset, with -1 <= i <= 1
	 * @param j The y offset, with -1 <= j <= 1
	 * @return Tile
	 */
	public Tile getAdjacentTile(int x, int y, int i, int j){
		if( (i>=-1 && i<=1) && (j>=-1 && j<=1) ){ // getTile tests if the tile is in bounds
			return this.getTile(x+i, y+j);
		} else {
			return null;
		}

	}
	
	/**
	 * Getter for the width
	 * @return the width of the board
	 */
	public int getWidth(){
		return this.width;
	}
	/**
	 * Getter for the height
	 * @return the width of the board
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * Increments the number of flags within the board
	 */
	public void incrementNbFlags(){
		this.nbFlags++;
	}
	
	/**
	 * Decrements the number of flags within the board
	 */
	public void decrementNbFlags(){
		this.nbFlags--;
	}
	
	/**
	 * Gets the number of flags within the board
	 * @return The number of flags placed
	 */
	public int getNbFlags(){
		return this.nbFlags;
	}
	
	/**
	 * Return the entire game board, in characters.
	 */
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
