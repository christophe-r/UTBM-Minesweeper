package fr.utbm.lp24.minesweeper;

/**
 * State of game
 * @author Vincent
 *
 */
public enum GameState {
	
	RUNNING, // There are a game in progress
	PAUSED, // There are a board but no mines
	STOPPED // There are no game
	
}
