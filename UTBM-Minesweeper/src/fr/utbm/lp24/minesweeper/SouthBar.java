package fr.utbm.lp24.minesweeper;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * 
 * Draw the southBar
 * @author vincent
 *
 */
public class SouthBar extends JPanel{

	private static final long serialVersionUID = 1L;
	PreferencesManager userPreferences;
	private String theme;
	private Image image_Flag = null;
	private Image image_clock = null;
	private int time = 0;
	private int flags = 10;
	private int size = 200;
	
	
	/**
	 * main constructor
	 * get the preference and load images for the first time
	 */
	SouthBar(){
		userPreferences = new PreferencesManager();
		this.theme = userPreferences.getPref("theme", "win7_classic");
		loadImages();
	}
	
	
	/**
	 * draw the SouthBar
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		userPreferences = new PreferencesManager();
		if(!theme.equals(userPreferences.getPref("theme", "win7_classic"))){ //check the theme
			this.theme = userPreferences.getPref("theme", "win7_classic");
			loadImages();
		}
		int margin = (size /2)-100;
	    g.drawImage(image_clock,margin + 10, 0,20,20, this);
	    if (time > 0 ){
	    	g.drawString(time + " seconds", margin + 30, 15);
	    }else{
	    	g.drawString(time + " second", margin + 30, 15);
	    }
	    
	    g.drawImage(image_Flag,margin + 130, 0,20,20, this);
	    if (flags > 0 ){
	    	 g.drawString(flags +" restants", margin + 150, 15); 
	    }else{
	    	 g.drawString( flags +" restant", margin + 150, 15); 
	    }
	   
	    
	}
	
	
	/**
	  * Update parameters used to draw the SouthBar
	  * 
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
	 * 
	 * Method used to load images
	 */
	public void loadImages() {
		try {
				String s = "/resources/themes/" + theme + "/flag.png";
				System.out.println("load : " + s);
				image_Flag = ImageIO.read(getClass().getResourceAsStream(s));
				
				s = "/resources/Clock.png";
				System.out.println("load : " + s);
				image_clock = ImageIO.read(getClass().getResourceAsStream(s));
			}
		catch(IOException exc) {
			exc.printStackTrace();
		}

	}  

}
