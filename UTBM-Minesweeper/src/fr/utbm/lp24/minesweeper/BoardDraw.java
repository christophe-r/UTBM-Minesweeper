package fr.utbm.lp24.minesweeper;

import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardDraw extends JPanel {
	
	private TileContent[][] stateboard;
	
	// private TileState[][] stateboard;
	BoardDraw(){}
	
	public void paintComponent(Graphics g){
		// TODO
		/* 
		 * Draw the board 
		 * 
		 * x1, y1, width, height
		 * or
		 * "", x1, y1,
		 * 
	     * for the time being this algorithm display the content ,not the state
	     * @author Vincent
	     */
		for( int i=0; i<stateboard[0].length ; i++ ){
			for( int j=0; j<stateboard.length ; j++ ){
				switch( stateboard[j][i] ){
				case CLEAR0: g.drawString(" ", 1+i*10, 1+j*10); break;			
				case MINE: g.drawString("@", 1+i*10, 1+j*10); break;	
				case CLEAR1: g.drawString("1", 1+i*10, 1+j*10); break;	
				case CLEAR2: g.drawString("2", 1+i*10, 1+j*10); break;	
				case CLEAR3: g.drawString("3", 1+i*10, 1+j*10); break;	
				case CLEAR4: g.drawString("4", 1+i*10, 1+j*10); break;	
				case CLEAR5: g.drawString("5", 1+i*10, 1+j*10); break;	
				case CLEAR6: g.drawString("6", 1+i*10, 1+j*10); break;	
				case CLEAR7: g.drawString("7", 1+i*10, 1+j*10); break;	
				case CLEAR8: g.drawString("8", 1+i*10, 1+j*10); break;	
				default: break;
		        	}	
				 /*
				switch( stateboard[i][j] ){
				case UNDISCOVERED: g.fillRect(1+i*5, 1+j*5, 5, 5); break;			
				case FLAGGED: g.drawString("F", 1+i*5, 1+j*5); break;	
				case QUESTION_MARK: g.drawString("?", 1+i*5, 1+j*5); break;	
				case DISCOVERED:{
					g.drawRect(1+i*5, 1+j*5, 5, 5); break;	
				}
				default: break;
		        	}	
				  */
				}
			}
		
		
		  }

	public void setNewBoard(TileContent[][] stateboard) { //update the stateboard
		this.stateboard  = new TileContent[stateboard.length][stateboard[0].length];
		this.stateboard = stateboard;
		
	}     
	/*
	public void setNewBoard(TileState[][] stateboard) { //update the stateboard
		this.stateboard  = new TileState[stateboard.length][stateboard[0].length];
		this.stateboard = stateboard;
		
	}    
	 * 
	 */
}
