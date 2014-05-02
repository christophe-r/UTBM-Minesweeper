package fr.utbm.lp24.minesweeper;

public class Timer implements Runnable {
	
	private int timer;
	private boolean runnable;

	/*public static void main(String[] args) {
		
		Timer myTimer = new Timer();
		
		(new Thread(myTimer)).start();
		
		
	}*/
	
	public Timer(){
		this.timer = 0;
		this.runnable = true;
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
				System.out.println("Timer: "+timer);
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