package fr.utbm.lp24.minesweeper;

import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author Christophe
 */
public class AboutWindow extends JDialog implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	JButton boutonOk;

	/**
	 * Class to manage the "About" window accessible by the menu Help > About
	 * @author Christophe
	 */
	public AboutWindow(){

		this.setTitle("About UTBM Minesweeper");
		this.setSize(360, 286);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		URL iconURL = getClass().getResource("/resources/icon64.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		 
		 
			JPanel panel = new JPanel();
			boutonOk = new JButton("OK");
			boutonOk.setBounds(264, 225, 80, 23);
			boutonOk.addActionListener(this);
			panel.setLayout(null);
			panel.add(boutonOk);
			
			getContentPane().add(panel);
			
			JPanel panel_1 = new JPanel(){

				private static final long serialVersionUID = 1L;

				public void paintComponent(Graphics g) {  
					Image img = Toolkit.getDefaultToolkit().getImage(
					AboutWindow.class.getResource("/resources/icon64.png"));
					g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);  
				}
				
			};
			panel_1.setBounds(20, 20, 64, 64);
			panel.add(panel_1);
			
			JLabel lblUtbmMinesweeper = new JLabel("UTBM Minesweeper");
			lblUtbmMinesweeper.setForeground(new Color(128, 128, 128));
			lblUtbmMinesweeper.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblUtbmMinesweeper.setBounds(94, 18, 250, 29);
			panel.add(lblUtbmMinesweeper);
			
			JLabel lblNewLabel = new JLabel("<html>This Java application \"Minesweeper\" was created in spring 2014 at the University of Technology of Belfort-Montb\u00E9liard, within the course credit LP24.");
			lblNewLabel.setBounds(20, 95, 324, 42);
			panel.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Authors:");
			lblNewLabel_1.setBounds(20, 148, 52, 14);
			panel.add(lblNewLabel_1);
			
			JLabel lblVincentMarseguerra = new JLabel("Vincent MARSEGUERRA");
			lblVincentMarseguerra.setBounds(71, 148, 130, 14);
			panel.add(lblVincentMarseguerra);
			
			JLabel lblChristopheRibotwwwchristopheribotfr = new JLabel("Christophe RIBOT (www.christophe-ribot.fr)");
			lblChristopheRibotwwwchristopheribotfr.setBounds(71, 162, 225, 14);
			panel.add(lblChristopheRibotwwwchristopheribotfr);
			
			JLabel lblGithubUrl = new JLabel("GitHub page:");
			lblGithubUrl.setBounds(20, 196, 64, 14);
			panel.add(lblGithubUrl);
			
			JLabel lblNewLabel_2 = new JLabel("<html><u>https://github.com/christophe-r/UTBM-Minesweeper</u>");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNewLabel_2.setForeground(new Color(0, 0, 255));
			lblNewLabel_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Desktop desktop = Desktop.getDesktop();
					try {
	                    URI uri = new URI("https://github.com/christophe-r/UTBM-Minesweeper");
	                    desktop.browse(uri);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                } catch (URISyntaxException ex) {
	                    ex.printStackTrace();
	                }
				}
			});
			lblNewLabel_2.setBounds(90, 196, 253, 14);
			panel.add(lblNewLabel_2);

		
		this.setVisible(true);
			
	
	}

	
	
	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == boutonOk ){
			this.setVisible(false); // Quit the About window
		}
	}
}
