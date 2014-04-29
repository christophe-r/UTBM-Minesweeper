package fr.utbm.lp24.minesweeper;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

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
		this.setSize(300, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		
		URL iconURL = getClass().getResource("/resources/icon64.png");
		ImageIcon icon = new ImageIcon(iconURL);
		this.setIconImage(icon.getImage());
		 
		 
			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());
			boutonOk = new JButton("OK");
			boutonOk.addActionListener(this);
			panel.add(boutonOk);
			
			this.add(panel);

		
		this.setVisible(true);
			
	
	}

	
	
	public void actionPerformed(ActionEvent event) {
		if( event.getSource() == boutonOk ){
			this.setVisible(false); // Quit the About window
		}
	}
	

}
