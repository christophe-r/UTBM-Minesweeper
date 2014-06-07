package fr.utbm.lp24.minesweeper;

public class Score {

	/**
	 * Class used to manage the score
	 * 
	 * @param nbMines
	 * @param width
	 * @param height
	 * @param time
	 * @param playAgain
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
