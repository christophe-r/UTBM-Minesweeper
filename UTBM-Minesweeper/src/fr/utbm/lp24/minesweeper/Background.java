package fr.utbm.lp24.minesweeper;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * This class draw the background on the window
 * @author Vincent
 */
public class Background  extends JPanel{
	private Image background = null;
	private String theme = "";
	private PreferencesManager userPreferences = new PreferencesManager();

	private static final long serialVersionUID = 1L;

	@Override
	public void paintComponent(Graphics g) {

		if( !theme.equals(userPreferences.getPref("theme", "win7_classic")) ){ // Check the theme
			this.theme = userPreferences.getPref("theme", "win7_classic");
			try {
				String s = "/resources/themes/" + theme + "/background.jpg";
				System.out.println("Loading: " + s);
				background = ImageIO.read(getClass().getResourceAsStream(s));
			}
			catch(IOException exc) {
				exc.printStackTrace();
			}

		}

		super.paintComponent(g);
		g.drawImage(background, 0, 0, 690, 580, this);
	}

}
