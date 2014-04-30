package fr.utbm.lp24.minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * This class draw the board on the windows
 * @author vincent
 *
 */
public class BoardDraw extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Tile[][] stateboard; // temporary array to save the board
	private int square_size = 20;
	
	
	private Image image_tile = null;
	private Image image_tile_clear = null;
	private Image image_mine = null;
	private Image image_flag = null;
	private Image question_mark = null;
	private Image image_clear1 = null;
	private Image image_clear2 = null;
	private Image image_clear3 = null;
	private Image image_clear4 = null;
	private Image image_clear5 = null;
	private Image image_clear6 = null;
	private Image image_clear7 = null;
	private Image image_clear8 = null;
	
	
	
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
		
		super.paintComponent(g);
		for( int i=0; i<stateboard.length ; i++ ){
			for( int j=0; j<stateboard[0].length ; j++ ){
				int cordX =  1+(j+2)*square_size;
			    int cordY =  1+(i+2)*square_size;
			   // g.drawImage(image_clear1, 0, 0,10,10, null) ; 
			    //    g.drawImage(loadImage("mine.png"), 10, 10, this);
			    //System.out.println("cord X : " + i + " cord Y : " + j);
			    g.setColor(Color.GRAY);
				switch( stateboard[i][j].getState() ){
					case UNDISCOVERED: 
						//g.fillRect(cordX,cordY-10, 9, 9);
						g.drawImage(image_tile, cordX, cordY,square_size,square_size, this); 
						 break;	
						
					case FLAGGED: 
						g.setColor(Color.BLACK);
						//g.drawRect(cordX,cordY-10, 10, 10); 
						//g.drawString(" F", cordX, cordY); 
						g.drawImage(image_flag, cordX, cordY,square_size,square_size, this); 
						
						break;	
					case QUESTION_MARK:
						g.setColor(Color.BLACK); 
						//g.drawString(" ?", cordX-10,cordY); 
						g.drawImage(question_mark, cordX, cordY,square_size,square_size, this); 
						break;	
						
					case DISCOVERED :{
						//g.drawRect(cordX,cordY, 10, 10);
						g.setColor(Color.blue);
						
						switch( stateboard[i][j].getContent() ){
							case CLEAR0: 
								//g.drawString(" ",  cordX, cordY+10);
								g.drawImage(image_tile_clear, cordX, cordY,square_size,square_size, this); 
								break;			
							case MINE:  
								g.setColor(Color.red); 
								//g.drawString("@",  cordX, cordY+10);
								g.drawImage(image_mine, cordX, cordY,square_size,square_size, this); 
								break;	
							case CLEAR1: 
								//g.drawString(" 1", cordX, cordY+10);
								g.drawImage(image_clear1, cordX, cordY,square_size,square_size, this); 
								break;	
							case CLEAR2:
								//g.drawString(" 2", cordX, cordY+10);
								g.drawImage(image_clear2, cordX, cordY,square_size,square_size, this); 
								break;	
							case CLEAR3:
								//g.drawString(" 3", cordX, cordY+10);
								g.drawImage(image_clear3, cordX, cordY,square_size,square_size, this); 
								break;	
							case CLEAR4:
								//g.drawString(" 4", cordX, cordY+10); 
								g.drawImage(image_clear4, cordX, cordY,square_size,square_size, this); 
								break;	
							case CLEAR5:
								//g.drawString(" 5", cordX, cordY+10); 
								g.drawImage(image_clear5, cordX, cordY,square_size,square_size, this);
								break;	
							case CLEAR6:
								//g.drawString(" 6", cordX, cordY+10);
								g.drawImage(image_clear6, cordX, cordY,square_size,square_size, this);
								break;	
							case CLEAR7:
								//g.drawString(" 7", cordX, cordY+10);
								g.drawImage(image_clear7, cordX, cordY,square_size,square_size, this);
								break;	
							case CLEAR8:
								//g.drawString(" 8", cordX, cordY+10);
								g.drawImage(image_clear8, cordX, cordY,square_size,square_size, this);
								break;	
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
	public void setNewBoard(Tile[][] stateboard,int square_size) { //update the stateboard
		this.square_size = square_size;
		this.stateboard = stateboard;
		
	}     
	
/**
 *  load a image
 *  
 * @param String filename
 * @return the object image
 */
	public void  loadImages() { //update the stateboard
			try {
				System.out.println("load : /resources/tile.png");
				image_tile = ImageIO.read(getClass().getResourceAsStream("/resources/tile.png"));
				
				System.out.println("load : /resources/tile_clear.png");
				image_tile_clear = ImageIO.read(getClass().getResourceAsStream("/resources/tile_clear.png"));
				
				System.out.println("load : /resources/nine.png");
				image_mine = ImageIO.read(getClass().getResourceAsStream("/resources/mine.png"));
				
				System.out.println("load : /resources/image_flag.png");
				image_flag = ImageIO.read(getClass().getResourceAsStream("/resources/flag.png"));
				
				System.out.println("load : /resources/question_mark.png");
				question_mark = ImageIO.read(getClass().getResourceAsStream("/resources/question_mark.png"));
				
				System.out.println("load : /resources/clear1.png");
				image_clear1 = ImageIO.read(getClass().getResourceAsStream("/resources/clear1.png"));
				
				System.out.println("load : /resources/clear2.png");
				image_clear2 = ImageIO.read(getClass().getResourceAsStream("/resources/clear2.png"));
				
				System.out.println("load : /resources/clear3.png");
				image_clear3 = ImageIO.read(getClass().getResourceAsStream("/resources/clear3.png"));
				
				System.out.println("load : /resources/clear4.png");
				image_clear4 = ImageIO.read(getClass().getResourceAsStream("/resources/clear4.png"));
				
				System.out.println("load : /resources/clear5.png");
				image_clear5 = ImageIO.read(getClass().getResourceAsStream("/resources/clear5.png"));
				
				System.out.println("load : /resources/clear6.png");
				image_clear6 = ImageIO.read(getClass().getResourceAsStream("/resources/clear6.png"));
				
				System.out.println("load : /resources/clear7.png");
				image_clear7 = ImageIO.read(getClass().getResourceAsStream("/resources/clear7.png"));
				
				System.out.println("load : /resources/clear8.png");
				image_clear8 = ImageIO.read(getClass().getResourceAsStream("/resources/clear8.png"));
			}
			catch(IOException exc) {
				exc.printStackTrace();
			
			}
		
	}     
}
