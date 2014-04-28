package fr.utbm.lp24.minesweeper;

/**
 * A tile, a single square
 * @author Christophe
 *
 */
public class Tile {

	private TileContent content;
	private TileState state;
	
	/**
	 * Initialize a clean undiscovered tile.
	 */
	public Tile(){
		this.setContent(TileContent.CLEAR0);
		this.setState(TileState.UNDISCOVERED);
	}
	
	/**
	 * This method allows us to change the content and/or the state of a tile.
	 * @param content (c.f. enum TileContent)
	 * @param state (c.f. enum TileState)
	 */
	public Tile(TileContent content, TileState state){
		this.setContent(content);
		this.setState(state);
	}

	/**
	 * Get the Content (c.f. enum TileContent) of a tile.
	 * @return TileContent
	 */
	public TileContent getContent() {
		return content;
	}
	
	/**
	 * Set the Content (c.f. enum TileContent) of a tile.
	 * @param content (c.f. enum TileContent)
	 */
	public void setContent(TileContent content) {
		this.content = content;
	}

	/**
	 * Get the State (c.f. enum TileState) of a tile.
	 * @return TileState
	 */
	public TileState getState() {
		return state;
	}

	/**
	 * Set the State (c.f. enum TileState) of a tile.
	 * @param state (c.f. enum TileState)
	 */
	public void setState(TileState state) {
		this.state = state;
	}
	
	/**
	 * This method increment the content (number of mines around) of a tile only if it does not contain a mine.
	 */
	public void setContentIncrement() {
		TileContent currentContent = this.getContent();
		
		if( currentContent == TileContent.CLEAR0 ){
			this.setContent(TileContent.CLEAR1);
		} else if( currentContent == TileContent.CLEAR1 ){
			this.setContent(TileContent.CLEAR2);
		} else if( currentContent == TileContent.CLEAR2 ){
			this.setContent(TileContent.CLEAR3);
		} else if( currentContent == TileContent.CLEAR3 ){
			this.setContent(TileContent.CLEAR4);
		} else if( currentContent == TileContent.CLEAR4 ){
			this.setContent(TileContent.CLEAR5);
		} else if( currentContent == TileContent.CLEAR5 ){
			this.setContent(TileContent.CLEAR6);
		} else if( currentContent == TileContent.CLEAR6 ){
			this.setContent(TileContent.CLEAR7);
		} else if( currentContent == TileContent.CLEAR7 ){
			this.setContent(TileContent.CLEAR8);
		}
		
	}
	
	/**
	 * This method returns the appropriate character according to the state and the content of the tile.
	 */
	public String toString(){
		
		String character = "";
		
		//if( this.getState() == TileState.DISCOVERED ){
			
			switch( this.getContent() ){
				case CLEAR0: character = " "; break;
				case CLEAR1: character = "1"; break;
				case CLEAR2: character = "2"; break;
				case CLEAR3: character = "3"; break;
				case CLEAR4: character = "4"; break;
				case CLEAR5: character = "5"; break;
				case CLEAR6: character = "6"; break;
				case CLEAR7: character = "7"; break;
				case CLEAR8: character = "8"; break;
				case MINE: character = "@"; break;
				default: break;
			}
		/*
		} else {
			if( this.getState() == TileState.UNDISCOVERED ){
				character = "#";
			} else if( this.getState() == TileState.FLAGGED ){
				character = "F";
			} else if( this.getState() == TileState.QUESTION_MARK ){
				character = "?";
			}
		}
		*/
		return character;
		
	}
	
	
}
