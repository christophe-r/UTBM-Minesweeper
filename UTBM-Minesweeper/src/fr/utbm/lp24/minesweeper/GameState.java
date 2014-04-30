package fr.utbm.lp24.minesweeper;


/**
 * State of game
 * @author vincent
 *
 */
public enum GameState {
		RUN, // There are a game in progress
		PAUSED, // There are a board but no mines
		STOPED // There are no game
}
