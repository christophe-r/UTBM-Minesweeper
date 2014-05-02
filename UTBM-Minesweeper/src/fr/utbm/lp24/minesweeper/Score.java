package fr.utbm.lp24.minesweeper;

public class Score {
	
	/**
	 * class used to manage score
	 * 
	 * @param nbmines
	 * @param width
	 * @param height
	 * @param time
	 * @param playagain
	 * @return the calculate score
	 */
	public int getscore(float nbmines, float width, float height, float time, boolean playagain){
		
		int triche = 1 ;
		if(playagain){
			triche = 0;
		}
		if(time == 0 ){
			time = 1;
			triche = 0;
		}
		float score = ( 1000 * triche )/( (nbmines / (width*height)) + 1/time );
		
		return Math.round(score) ;
		
	}
}
