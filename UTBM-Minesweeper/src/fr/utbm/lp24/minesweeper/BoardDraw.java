package fr.utbm.lp24.minesweeper;

import java.awt.Graphics;

import javax.swing.JPanel;
/**
 * This class draw the board on the windows
 * @author vincent
 *
 */
public class BoardDraw extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Tile[][] stateboard; // tempory array to save the board
	BoardDraw(){}
	
	public void paintComponent(Graphics g){
		// TODO
		/**
		 * Draw the board on the windows
		 * 
		 * The board start the génération at  21px,11px
		 * and any box is a square of 10px
		 * 
	     * @author Vincent
	     */
		//g.drawRect(19,10, 2+(stateboard.length)*10, 2+(stateboard[0].length)*10);
		for( int i=0; i<stateboard.length ; i++ ){
			for( int j=0; j<stateboard[0].length ; j++ ){
				int cordX =  1+(j+2)*10;
			    int cordY =  1+(i+2)*10;
			    //System.out.println("cord X : " + i + " cord Y : " + j);
				switch( stateboard[i][j].getState() ){
					case UNDISCOVERED: g.fillRect(cordX,cordY-10, 10, 10); break;			
					case FLAGGED:g.drawRect(cordX,cordY-10, 10, 10); g.drawString(" F", cordX, cordY); break;	
					case QUESTION_MARK: g.drawString(" ?", cordX,cordY); break;	
					case DISCOVERED :{
						g.drawRect(cordX,cordY-10, 10, 10);
						switch( stateboard[i][j].getContent() ){
							case CLEAR0: g.drawString(" ", cordX, cordY); break;			
							case MINE: g.drawString("@",   cordX, cordY); break;	
							case CLEAR1: g.drawString(" 1", cordX, cordY); break;	
							case CLEAR2: g.drawString(" 2", cordX, cordY); break;	
							case CLEAR3: g.drawString(" 3", cordX, cordY); break;	
							case CLEAR4: g.drawString(" 4", cordX, cordY); break;	
							case CLEAR5: g.drawString(" 5", cordX, cordY); break;	
							case CLEAR6: g.drawString(" 6" ,cordX, cordY); break;	
							case CLEAR7: g.drawString(" 7", cordX, cordY); break;	
							case CLEAR8: g.drawString(" 8", cordX, cordY); break;	
							default: break;
			        	}	
				}
				default: break;
				}
			}
		}
	}

	
	/**
	 * Update the stateboard
	 * 
	 * @param stateboard
	 */
	public void setNewBoard(Tile[][] stateboard) { //update the stateboard
		this.stateboard = stateboard;
		
	}     
}
