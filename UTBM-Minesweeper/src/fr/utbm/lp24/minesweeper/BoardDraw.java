package fr.utbm.lp24.minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * This class draw the board on the window
 * @author Vincent
 */
public class BoardDraw extends JPanel {

	private static final long serialVersionUID = 1L;
	private PreferencesManager userPreferences; 
	private Tile[][] stateboard; // temporary array to save the board
	private int square_size = 20;
	private String theme;
	private boolean endGame;
	private boolean activeShadow;
	private int shadowX;
	private int shadowY;

	private boolean cheatPixelState;

	private String[] images = {"tile.png",
			"tile_clear.png",
			"mine.png",
			"flag.png",
			"question_mark.png",
			"clear1.png",
			"clear2.png",
			"clear3.png",
			"clear4.png",
			"clear5.png",
			"clear6.png",
			"clear7.png",
			"clear8.png",
			"cross.png",
			"background.jpg",
	};
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
	private Image cross = null;
	private Image mask = null;
	private Image background = null;

	private Image[] varImages = {
			image_tile,
			image_tile_clear,
			image_mine,
			image_flag,
			question_mark,
			image_clear1,
			image_clear2,
			image_clear3,
			image_clear4,
			image_clear5,
			image_clear6,
			image_clear7,
			image_clear8,
			cross,
			background,
	};

	public BoardDraw(){
		userPreferences = new PreferencesManager();
		this.theme = userPreferences.getPref("theme", "win7_classic");
	}

	/**
	 * Draw the board on the windows
	 * 
	 * The board starts the generation at 21px,11px
	 * and each box is a square of 10px
	 * 
	 */
	public void paintComponent(Graphics g){

		userPreferences = new PreferencesManager();
		if(!theme.equals(userPreferences.getPref("theme", "win7_classic"))){ // Check the theme
			this.theme = userPreferences.getPref("theme", "win7_classic");
			loadImages();
		}
		super.paintComponent(g);

		for( int i=0; i<stateboard.length ; i++ ){
			for( int j=0; j<stateboard[0].length ; j++ ){
				int cordX =  1+(j+1)*square_size;
				int cordY =  1+(i+1)*square_size;
				g.setColor(Color.GRAY);

				switch( stateboard[i][j].getState() ){

				case UNDISCOVERED: 
					g.drawImage(varImages[0], cordX, cordY,square_size,square_size, this);
					break;	

				case FLAGGED: 
					g.setColor(Color.BLACK);
					g.drawImage(varImages[0], cordX, cordY,square_size,square_size, this);
					g.drawImage(varImages[3], cordX, cordY,square_size,square_size, this);
					if(endGame){
						g.drawImage(varImages[13], cordX, cordY,square_size,square_size, this);	
					}
					break;	

				case QUESTION_MARK:
					g.setColor(Color.BLACK);
					g.drawImage(varImages[0], cordX, cordY,square_size,square_size, this);
					g.drawImage(varImages[4], cordX, cordY,square_size,square_size, this);
					break;	

				case DISCOVERED: {
					g.setColor(Color.blue);
					g.drawImage(varImages[1], cordX, cordY,square_size,square_size, this);

					switch( stateboard[i][j].getContent() ){

					case MINE:  
						g.setColor(Color.red);
						g.drawImage(varImages[2], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR1:
						g.drawImage(varImages[5], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR2:
						g.drawImage(varImages[6], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR3:
						g.drawImage(varImages[7], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR4:
						g.drawImage(varImages[8], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR5:
						g.drawImage(varImages[9], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR6:
						g.drawImage(varImages[10], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR7:
						g.drawImage(varImages[11], cordX, cordY,square_size,square_size, this);
						break;	
					case CLEAR8:
						g.drawImage(varImages[12], cordX, cordY,square_size,square_size, this);
						break;	
					default: break;
					}
				}
				default: break;
				}
			}
		}

		String shadow = userPreferences.getPref("heplshadow", "true");
		if(activeShadow){
			for( int i=0 ; i<=2 ; i++ ){
				for( int j=0 ; j<=2 ; j++ ){
					if( i != 1 || j != 1 ){
						if	((shadowX+i) > 0 && (shadowY+j) > 0){
							if((shadowX+i) < stateboard[0].length+1 && (shadowY+j) <stateboard.length+1)
								if(shadow.equals("true")){
									g.drawImage(mask, (shadowX+i)*square_size, (shadowY+j)*square_size, square_size, square_size, this);
								}
						}
					}
				}
			}
		}

		if(this.cheatPixelState){
			g.fillRect(0, 0, 2, 2);
		}
	}

	/**
	 * Update the stateboard
	 * 
	 * @param stateboard
	 */
	public void setNewBoard(Tile[][] stateboard,int square_size, boolean endGame) {
		this.square_size = square_size;
		this.stateboard = stateboard;
		this.endGame = endGame;
	}     

	/**
	 * Loads an image
	 *  
	 * @param String filename
	 * @return the object image
	 */
	public void loadImages() {

		try {
			for( int i=0; i<images.length; i++ ){
				String s = "/resources/themes/" + theme + "/" + images[i];
				System.out.println("load : " + s);
				varImages[i] = ImageIO.read(getClass().getResourceAsStream(s));
			}
			mask = ImageIO.read(getClass().getResourceAsStream("/resources/mask.png"));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * Update the coordinate to draw the shadows
	 * @param x coordinate of the center of the shadow
	 * @param y coordinate of the center of the shadow
	 * @param shadow true if the shadow is active
	 */
	public void shadow(int x, int y, boolean shadow) {
		this.activeShadow = shadow;
		this.shadowX = x;
		this.shadowY = y;
	}

	/**
	 * Set the pixel state
	 * @param pixelState pixel state
	 */
	public void cheatPixel(boolean pixelState) {
		this.cheatPixelState = pixelState;
	}
}
