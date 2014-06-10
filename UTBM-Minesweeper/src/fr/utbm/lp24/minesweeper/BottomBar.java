package fr.utbm.lp24.minesweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * Draw the southBar
 * @author Vincent
 */
public class BottomBar extends JPanel{

	private static final long serialVersionUID = 1L;
	PreferencesManager userPreferences;
	private String theme;
	private Image outlineCircle = null;
	private Image outlineRect = null;
	private Image mine = null;
	private Image clock = null;
	private int time = 0;
	private int flags = 10;
	private int size = 200;


	/**
	 * Main constructor
	 * Get the preferences and load images for the first time
	 */
	public BottomBar(){
		userPreferences = new PreferencesManager();
		this.theme = userPreferences.getPref("theme", "win7_classic");
		loadImages();
	}


	/**
	 * Draw the SouthBar
	 */
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		userPreferences = new PreferencesManager();

		if( !theme.equals(userPreferences.getPref("theme", "win7_classic")) ){ //check the theme
			this.theme = userPreferences.getPref("theme", "win7_classic");
			loadImages();
		}
		int margin = (size /2)-100;	
		if( this.theme.equals("win98_classic")){
			g.setColor(Color.BLACK);
		}
		else{
			g.setColor(Color.WHITE);
		}

		g.drawImage(outlineCircle,margin + 10,0, 30,30, this);
		g.drawImage(clock, margin + 14, 4, 23,23, this);
		g.drawImage(outlineRect, margin + 45, 5, 40,20, this);
		g.drawString(time + "", margin + 55,20);

		g.drawImage(outlineCircle, margin + 164, 0, 30,30, this);
		g.drawImage(mine, margin + 168, 4, 23,23, this);
		g.drawImage(outlineRect, margin + 120, 5, 40,20, this);
		g.drawString( flags +"", margin + 130, 20);

	}


	/**
	 * Update parameters used to draw the SouthBar
	 */
	public void updateParameter(int value, String type){
		if(type.equals("time"))
			this.time = value;

		if(type.equals("flags"))
			this.flags = value;

		if(type.equals("size"))
			this.size = value;
	}


	/**
	 * Method used to load images
	 */
	public void loadImages() {
		try {
			String s = "/resources/themes/" + theme + "/outline_rect.png";
			System.out.println("load : " + s);
			outlineRect = ImageIO.read(getClass().getResourceAsStream(s));

			s = "/resources/themes/" + theme + "/mine.png";
			System.out.println("load : " + s);
			mine = ImageIO.read(getClass().getResourceAsStream(s));			

			s = "/resources/themes/" + theme + "/clock.png";
			System.out.println("load : " + s);
			clock = ImageIO.read(getClass().getResourceAsStream(s));

			s = "/resources/outlinecircle.png";
			System.out.println("load : " + s);
			outlineCircle = ImageIO.read(getClass().getResourceAsStream(s));
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}

	}  

}
