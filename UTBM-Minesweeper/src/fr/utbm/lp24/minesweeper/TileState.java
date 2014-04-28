package fr.utbm.lp24.minesweeper;

/**
 * State of the tile
 * @author Christophe
 *
 */
public enum TileState {
	UNDISCOVERED, FLAGGED, QUESTION_MARK, // Tile is undiscovered in these states
	DISCOVERED
}
