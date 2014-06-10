package fr.utbm.lp24.minesweeper;

/**
 * A tile, a single square
 * @author Christophe
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
	 * This method increments the content (number of mines around) of a tile only if it does not contain a mine.
	 */
	public void setContentIncrement() {

		switch( this.getContent() ){
		case CLEAR0: this.setContent(TileContent.CLEAR1); break;
		case CLEAR1: this.setContent(TileContent.CLEAR2); break;
		case CLEAR2: this.setContent(TileContent.CLEAR3); break;
		case CLEAR3: this.setContent(TileContent.CLEAR4); break;
		case CLEAR4: this.setContent(TileContent.CLEAR5); break;
		case CLEAR5: this.setContent(TileContent.CLEAR6); break;
		case CLEAR6: this.setContent(TileContent.CLEAR7); break;
		case CLEAR7: this.setContent(TileContent.CLEAR8); break;

		default: break;
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

	/**
	 * Transform tile state to number
	 * @author vincent
	 */
	public int toContent(){
		switch( this.getContent()){
		case CLEAR0: return 0; 
		case CLEAR1: return 1; 
		case CLEAR2: return 2; 
		case CLEAR3: return 3; 
		case CLEAR4: return 4; 
		case CLEAR5: return 5; 
		case CLEAR6: return 6; 
		case CLEAR7: return 7; 
		case CLEAR8: return 8; 
		case MINE: return 10; 
		default: return 11;
		}
	}

}
