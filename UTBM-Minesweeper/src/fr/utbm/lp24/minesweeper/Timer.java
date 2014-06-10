package fr.utbm.lp24.minesweeper;

/**
 * This class handles the timer
 * @author Christophe
 */
public class Timer implements Runnable {

	private int timer;
	private boolean runnable;
	private MinesweeperWindow window;

	/**
	 * Constructor class to initialize the timer
	 * @param window
	 */
	public Timer(MinesweeperWindow window){
		this.timer = -1;
		this.runnable = true;
		this.window = window;
	}

	/**
	 * Method to run the timer and update the bar
	 */
	public void run(){

		while (this.runnable) {

			timer++;
			window.updateBottom(timer, "time");

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}


	/**
	 * This method gets the timer value
	 * @return The timer
	 */
	public int getTimer(){
		return this.timer;	
	}


	/**
	 * This method stops the timer
	 */
	public void stopTimer(){
		window.updateBottom(0, "time");
		this.runnable = false;
	}


}