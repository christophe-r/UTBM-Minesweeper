package fr.utbm.lp24.minesweeper;

/**
 * Content of the tile: clear/x mines around/a mine
 * @author Christophe
 *
 */
public enum TileContent {
	CLEAR0, // Tile is empty
	CLEAR1, CLEAR2, CLEAR3, CLEAR4, CLEAR5, CLEAR6, CLEAR7, CLEAR8, // There are X mines around
	MINE // There is a mine
}
