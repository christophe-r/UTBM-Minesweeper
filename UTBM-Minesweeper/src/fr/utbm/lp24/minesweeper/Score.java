package fr.utbm.lp24.minesweeper;


/**
 * Class used to manage the score
 * @author vincent
 *
 */
public class Score {

	/**
	 * Calculates the score
	 * 
	 * @param nbMines Number of mines 
	 * @param width Width
	 * @param height Height
	 * @param time Time
	 * @param playAgain Enable if the player click on play again
	 * @return the computed score
	 */
	public int getScore(float nbMines, float width, float height, float time, boolean playAgain){

		int cheat = 1 ;

		if( playAgain ){
			cheat = 0;
		}
		if( time == 0 ){
			time = 1;
			cheat = 0;
		}

		float score = ( 1000 * cheat )/( (nbMines / (width*height)) + (1-1/time) );
		return Math.round(score);
	}
}
