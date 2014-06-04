package fr.utbm.lp24.minesweeper;

public class StatisticsManager extends PreferencesManager{

	public void addTime(int time){
		int oldtime = Integer.parseInt(getPref("Stat_Time","0"));
		oldtime += time;
		setPref("Stat_Time", oldtime + "");
	}
	public void addGamePlayed(){
		int number = Integer.parseInt(getPref("Stat_Played","0"));
		number ++;
		setPref("Stat_Time",number + "");
	}
	public void addGameWon(){
		int number = Integer.parseInt(getPref("Stat_Won","0"));
		number ++;
		setPref("Stat_Time", number + "");
	}
	public void testBestScore(int score){
		if(Integer.parseInt(getPref("Stat_Score","0")) < score){
			setPref("Stat_Score", score + "");
		}
	}
}
