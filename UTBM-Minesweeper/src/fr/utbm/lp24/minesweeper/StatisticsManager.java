package fr.utbm.lp24.minesweeper;


/**
 * Class to manage the statistics
 * @author Christophe and Vincent
 *
 */
public class StatisticsManager extends PreferencesManager {
	
	/**
	 * Add time to the total time counter
	 * @param time
	 */
	public void addTime(int time){
		int oldTime = Integer.parseInt(getPref("stats_total_time", "0"));
		oldTime += time;
		setPref("stats_total_time", oldTime + "");
	}

	/**
	 * Add game to the total counter
	 */
	public void addGamePlayed(){
		int gamesPlayed = Integer.parseInt(getPref("stats_games_played", "0"));
		gamesPlayed++;
		setPref("stats_games_played", gamesPlayed + "");
	}

	/**
	 * Add won game to the total counter
	 */
	public void addGameWon(){
		int gamesWon = Integer.parseInt(getPref("stats_games_won", "0"));
		gamesWon++;
		setPref("stats_games_won", gamesWon + "");
	}

	/**
	 * Save the score if it's the best
	 * @param score
	 */
	public void testBestScore(int score){
		if( Integer.parseInt(getPref("stats_best_score", "0")) < score ){
			setPref("stats_best_score", score + "");
		}
	}

	/**
	 * Get the total time played
	 * @return HH:MM:SS
	 */
	public String getTotalTimePlayed(){

		int seconds = Integer.parseInt(getPref("stats_total_time", "0"));
		int hours = (int) seconds / 3600;
		int remainder = (int) seconds - hours * 3600;
		int mins = remainder / 60;
		remainder = remainder - mins * 60;
		int secs = remainder;

		return ((hours<10)?"0"+hours:hours)+":"+((mins<10)?"0"+mins:mins)+":"+((secs<10)?"0"+secs:secs);
	}

	/**
	 * Get the total games played
	 * @return total games played
	 */
	public int getGamesPlayed(){
		return Integer.parseInt(getPref("stats_games_played", "0"));
	}

	/**
	 * Get the total games won
	 * @return total games won
	 */
	public int getGamesWon(){
		return Integer.parseInt(getPref("stats_games_won", "0"));
	}

	/**
	 * Get the best score
	 * @return best score
	 */
	public int getBestScore(){
		return Integer.parseInt(getPref("stats_best_score", "0"));
	}

	/**
	 * Reset the statistics
	 */
	public void reset(){
		setPref("stats_total_time", "0");
		setPref("stats_games_played", "0");
		setPref("stats_games_won", "0");
		setPref("stats_best_score", "0");
	}


}
