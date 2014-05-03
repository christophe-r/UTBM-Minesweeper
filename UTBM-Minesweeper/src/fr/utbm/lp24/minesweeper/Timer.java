package fr.utbm.lp24.minesweeper;

public class Timer implements Runnable {
	
	private int timer;
	private boolean runnable;
	private MinesweeperWindow window;

	/*public static void main(String[] args) {
		
		Timer myTimer = new Timer();
		
		(new Thread(myTimer)).start();
		
		
	}*/
	
	public Timer(MinesweeperWindow window){
		this.timer = 0;
		this.runnable = true;
		this.window = window;
	}
	

	public void run() {
		
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if( this.runnable ){
				timer++;
				//System.out.println("Timer: "+timer);
				window.updateSouth(timer,"time");
			}
		}
		
	}

	public int getTimer(){
		return this.timer;
	}
	
	
	public void stopTimer(){
		this.runnable = false;
	}


}