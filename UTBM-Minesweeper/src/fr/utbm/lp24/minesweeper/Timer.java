package fr.utbm.lp24.minesweeper;

public class Timer implements Runnable {
	
	private int timer;
	private boolean runnable;
	private MinesweeperWindow window;
	
	public Timer(MinesweeperWindow window){
		this.timer = -1;
		this.runnable = true;
		this.window = window;
	}
	
	
	public void run() {
		
		while (this.runnable) {
			
			timer++;
			//System.out.println("Timer: "+timer);
			window.updateBottom(timer,"time");
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public int getTimer(){
		return this.timer;	
	}
	
	
	public void stopTimer(){
		window.updateBottom(0,"time");
		this.runnable = false;
	}


}